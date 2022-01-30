package br.com.fabiofnc.challenge.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Receita;

@Service
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByDescricao(String descricao);

    @Query("SELECT r FROM Receita r WHERE (:descricao IS NULL OR r.descricao = :descricao)")
    Page<Receita> findAllByDescricao(@Param("descricao") String descricao, Pageable paginacao);
    
    @Query("SELECT r FROM Receita r WHERE r.data BETWEEN :dataInicio AND :dataFim")
    Page<Receita> findAllByAnoAndMes(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim")LocalDate dataFim, Pageable paginacao);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_receitas "
            + "WHERE EXTRACT(YEAR FROM data) = ?1 "
            + "AND EXTRACT( MONTH FROM data) = ?2")
	List<Receita> findByAnoAndMes(Integer ano, Integer mes);

}