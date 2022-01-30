package br.com.fabiofnc.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabiofnc.challenge.data.DTO.ResumoDTO;
import br.com.fabiofnc.challenge.service.ResumoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController()
@RequestMapping(value = "/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoDTO> pegarResumo(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok().body(resumoService.find(mes, ano));
    }
    
}