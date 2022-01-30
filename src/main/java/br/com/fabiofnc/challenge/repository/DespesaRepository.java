package br.com.fabiofnc.challenge.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.model.Despesa;

@Service
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
	@Query("SELECT d FROM Despesa d WHERE (:descricao IS NULL OR d.descricao = :descricao)")
	Page<Despesa> findAllByDescricao(@Param("descricao") String descricao, Pageable paginacao);

	@Query("SELECT d FROM Despesa d WHERE d.data BETWEEN :dataInicio AND :dataFim")
	Page<Despesa> findAllByAnoAndMes(LocalDate dataInicio, LocalDate dataFim, Pageable paginacao);

	@Query(nativeQuery = true, value = "SELECT * FROM tb_despesas "
			+ "WHERE EXTRACT(YEAR FROM data) = ?1 "
			+ "AND EXTRACT( MONTH FROM data) = ?2")
	List<Despesa> findByAnoAndMes(Integer ano, Integer mes);

	@Query(nativeQuery = true, value = "SELECT SUM(valor) as valor FROM tb_despesas "
			+ "WHERE EXTRACT(YEAR FROM data) = ?1 "
			+ "AND EXTRACT(MONTH FROM data) = ?2 "
			+ "AND categoria = ?3 "
			+ "GROUP BY categoria LIMIT 1")
	BigDecimal resumoPorCategoria(Integer ano, Integer mes, String categoria);
	
}