package br.com.fabiofnc.challenge.data.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DespesaDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String tipoDespesa;
    
}