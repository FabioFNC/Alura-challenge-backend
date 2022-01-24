package br.com.fabiofnc.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fabiofnc.challenge.data.DTO.DespesaDTO;
import br.com.fabiofnc.challenge.data.model.Despesa;
import br.com.fabiofnc.service.DespesasService;

@RestController
@RequestMapping("v1/despesas")
public class DespesaController {

    @Autowired
    private DespesasService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<DespesaDTO> findById(@PathVariable Long id) {
        Despesa despesa = service.findById(id);
        return ResponseEntity.ok().body(toDespesaDTO(despesa));
    }

    @GetMapping()
    public ResponseEntity<List<DespesaDTO>> findAll() {
        List<Despesa> despesas = service.findAll();
        return ResponseEntity.ok().body(toListDespesasDTO(despesas));
    }

    @PostMapping()
    public ResponseEntity<DespesaDTO> create(@RequestBody DespesaDTO dto, UriComponentsBuilder uriBuilder) {
        Despesa despesa = toDespesa(dto);
        service.create(despesa);
        URI uri = uriBuilder.path("/v1/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(toDespesaDTO(despesa));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesaDTO> update(@RequestBody DespesaDTO dto, @PathVariable Long id) {
        service.update(toDespesa(dto), id);
        return ResponseEntity.ok().body(dto);
    } 


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public DespesaDTO toDespesaDTO(Despesa despesa) {
        return modelMapper.map(despesa, DespesaDTO.class);
    }

    public List<DespesaDTO> toListDespesasDTO(List<Despesa> despesas) {
        return despesas.stream().map(this::toDespesaDTO).toList();
    }

    public Despesa toDespesa(DespesaDTO dto) {
        return modelMapper.map(dto, Despesa.class);
    }
    
}