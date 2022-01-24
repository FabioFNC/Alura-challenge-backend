package br.com.fabiofnc.service;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Despesa;
import br.com.fabiofnc.repository.DespesaRepository;

@Service
public class DespesasService {
    
    @Autowired
    private DespesaRepository repository;

    public Despesa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Despesa de id " + id + " não encontrada", "Despesa"));
    }

    public List<Despesa> findAll() {
        return repository.findAll();
    }

    public void create(Despesa despesa) {
        repository.save(despesa);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public void update(Despesa despesa, Long id) {
        findById(id);
        despesa.setId(id);
        repository.save(despesa);
    }

}