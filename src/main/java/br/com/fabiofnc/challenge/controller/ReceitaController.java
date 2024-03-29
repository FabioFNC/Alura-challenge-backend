package br.com.fabiofnc.challenge.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fabiofnc.challenge.data.DTO.ReceitaDTO;
import br.com.fabiofnc.challenge.data.model.Receita;
import br.com.fabiofnc.challenge.service.ReceitaService;

@RestController
@RequestMapping(value = "v1/receitas")
public class ReceitaController {
    
    @Autowired
    private ReceitaService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
        Receita receita = service.findById(id);
        return ResponseEntity.ok().body(toReceitaDTO(receita));
    }

    @GetMapping()
    public ResponseEntity<List<ReceitaDTO>> findAll() {
        List<Receita> receitas = service.findAll();
        return ResponseEntity.ok().body(toListReceitaDTO(receitas));
    }
    
    @GetMapping("/paginada")
    public ResponseEntity<Page<ReceitaDTO>> findAllByDescricao(@RequestParam(value = "descricao", required = false) String descricao, 
    		@PageableDefault(direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Receita> receitas = service.findAllByDescricao(descricao, paginacao);
        return ResponseEntity.ok().body(receitas.map(this::toReceitaDTO));
    }
    
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<ReceitaDTO>> findAllByAnoAndMes(@PathVariable Integer ano, @PathVariable Integer mes, @PageableDefault(direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Receita> receitas = service.findAllByAnoAndMes(ano, mes, paginacao);
        return ResponseEntity.ok().body(receitas.map(this::toReceitaDTO));
    }

    @PostMapping()
    public ResponseEntity<ReceitaDTO> create(@RequestBody @Valid ReceitaDTO receitaDTO, UriComponentsBuilder uriBuilder) {
        Receita receita = toReceita(receitaDTO);
        service.create(toReceita(receitaDTO));
        URI uri = uriBuilder.path("v1/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(toReceitaDTO(receita));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReceitaDTO> update(@RequestBody @Valid ReceitaDTO receitaDTO, @PathVariable Long id) {
        Receita receita = toReceita(receitaDTO);
        service.update(receita, id);
        return ResponseEntity.ok().body(toReceitaDTO(receita));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ReceitaDTO toReceitaDTO(Receita receita) {
        return modelMapper.map(receita, ReceitaDTO.class);
    }

    public List<ReceitaDTO> toListReceitaDTO(List<Receita> despesas) {
        return despesas.stream().map(this::toReceitaDTO).toList();
    }

    public Receita toReceita(ReceitaDTO dto) {
        return modelMapper.map(dto, Receita.class);
    }
    

}