package br.com.fabiofnc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Receita;

@Service
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByDescricao(String descricao);
    
}