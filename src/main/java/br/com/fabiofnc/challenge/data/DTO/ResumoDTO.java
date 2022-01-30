package br.com.fabiofnc.challenge.data.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResumoDTO {

    private BigDecimal totalDeReceita;
    private BigDecimal totalDeDespesa;
    private BigDecimal saldoRestante;
    private List<CategoriaDTO> categoriaDTOs = new ArrayList<>();
    
    public void addCategoriaDTO(CategoriaDTO categoriaDTO) {
        categoriaDTOs.add(categoriaDTO);
    }

}
