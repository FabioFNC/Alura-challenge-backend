package br.com.fabiofnc.challenge.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Receita;
import br.com.fabiofnc.challenge.exception.ReceitaJaExistenteNesseMes;
import br.com.fabiofnc.challenge.repository.ReceitaRepository;

@Service
public class ReceitaService {
    
    @Autowired
    private ReceitaRepository repository;

    public Receita findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Receita de id " + id + " nao encotrada!", "Receita"));
    }

    public List<Receita> findAll() {
        return repository.findAll();
    }
    
    public Page<Receita> findAllByDescricao(String descricao, Pageable paginacao) {
    	return repository.findAllByDescricao(descricao, paginacao);
    }
    
    public Page<Receita> findAllByAnoAndMes(Integer ano, Integer mes, Pageable paginacao) {
    	LocalDate dataDespesaEventual = LocalDate.of(ano, mes, 1);
    	LocalDate dataInicio = dataDespesaEventual.withDayOfMonth(1);
    	LocalDate dataFim = dataDespesaEventual.withDayOfMonth(dataDespesaEventual.lengthOfMonth());
    	return repository.findAllByAnoAndMes(dataInicio, dataFim, paginacao);
    }

    public List<Receita> findByAnoAndMes(Integer ano, Integer mes) {
    	return repository.findByAnoAndMes(ano, mes);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public void create(Receita receita) {
        if (!receitaRepetidaNoMes(receita)) repository.save(receita);
        else throw new ReceitaJaExistenteNesseMes("Ja tem essa receita cadastrada nesse mes");
    }

    public void update(Receita receita, Long id) {
        findById(id);
        receita.setId(id);
        if (!receitaRepetidaNoMes(receita)) repository.save(receita);
        else throw new ReceitaJaExistenteNesseMes("Ja tem essa receita cadastrada nesse mes");
    }

    public boolean receitaRepetidaNoMes(Receita entity) {
        Receita receitaBase = repository.findByDescricao(entity.getDescricao()).orElse(new Receita());
        if (entity.getMes().equals(receitaBase.getMes())) return true;
        return false;
    }

}