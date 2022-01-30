package br.com.fabiofnc.challenge.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Despesa;
import br.com.fabiofnc.challenge.repository.DespesaRepository;

@Service
public class DespesasService {

    @Autowired
    private DespesaRepository repository;

    public Despesa findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Despesa de id " + id + " não encontrada", "Despesa"));
    }

    public List<Despesa> findAll() {
        return repository.findAll();
    }

    public Page<Despesa> findAllByDescricao(String descricao, Pageable paginacao) {
        return repository.findAllByDescricao(descricao, paginacao);
    }

    public Page<Despesa> findAllByAnoAndMes(Integer ano, Integer mes, Pageable paginacao) {
        LocalDate dataDespesaEventual = LocalDate.of(ano, mes, 1);
        LocalDate dataInicio = dataDespesaEventual.withDayOfMonth(1);
        LocalDate dataFim = dataDespesaEventual.withDayOfMonth(dataDespesaEventual.lengthOfMonth());
        return repository.findAllByAnoAndMes(dataInicio, dataFim, paginacao);
    }

    public void create(Despesa despesa) {
        if (despesa.getData() == null)
            despesa.setData(LocalDate.now());
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

    public List<Despesa> findByAnoAndMes(Integer ano, Integer mes) {
        return repository.findByAnoAndMes(ano, mes);
    }

    public BigDecimal resumoPorCategoria(Integer ano, Integer mes, String categoria) {
        return repository.resumoPorCategoria(ano, mes, categoria);
    }

}