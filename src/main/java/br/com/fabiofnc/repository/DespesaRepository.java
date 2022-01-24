package br.com.fabiofnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Despesa;

@Service
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
}