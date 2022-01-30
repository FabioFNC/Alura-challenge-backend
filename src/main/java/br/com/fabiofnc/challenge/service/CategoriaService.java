package br.com.fabiofnc.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.CategoriaDespesa;

@Service
public class CategoriaService {
    
    private CategoriaDespesa[] categoriasEnum = CategoriaDespesa.values();

    public List<String> findAll() {
        List<String> categorias = new ArrayList<>();
        for (CategoriaDespesa c : categoriasEnum) categorias.add(c.toString());
        return categorias;
    }

}
