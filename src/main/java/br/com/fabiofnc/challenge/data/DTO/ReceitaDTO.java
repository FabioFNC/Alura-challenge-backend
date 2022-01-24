package br.com.fabiofnc.challenge.data.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class ReceitaDTO {

    private Long id;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;
    
}