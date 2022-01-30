package br.com.fabiofnc.challenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabiofnc.challenge.data.DTO.CategoriaDTO;
import br.com.fabiofnc.challenge.data.DTO.ResumoDTO;
import br.com.fabiofnc.challenge.data.model.Despesa;
import br.com.fabiofnc.challenge.data.model.Receita;

@Service
public class ResumoService{

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private DespesasService despesasService;

    @Autowired
    private CategoriaService categoriaService;

    public ResumoDTO find(Integer mes, Integer ano) {
        List<Receita> receitasPorMesEhAno = receitaService.findByAnoAndMes(ano, mes);
        BigDecimal totalReceitas = receitasPorMesEhAno.stream()
                        .map(Receita::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Despesa> despesasPorMesEhAno = despesasService.findByAnoAndMes(ano, mes);
        BigDecimal totalDespesas = despesasPorMesEhAno.stream()
                        .map(Despesa::getValor)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoFinalNoMes = totalReceitas.subtract(totalDespesas);

        List<String> categorias = categoriaService.findAll();

        List<CategoriaDTO> categoriaDTOs = categorias.stream()
                        .map(c -> new CategoriaDTO(c, despesasService.resumoPorCategoria(ano, mes, c)))
                        .collect(Collectors.toList());

        ResumoDTO resumoDTO = new ResumoDTO();
        resumoDTO.setTotalDeDespesa(totalDespesas);
        resumoDTO.setTotalDeReceita(totalReceitas);
        resumoDTO.setSaldoRestante(saldoFinalNoMes);

        for (CategoriaDTO categoriaDTO : categoriaDTOs) resumoDTO.addCategoriaDTO(categoriaDTO);

        return resumoDTO;
    }
    
}
