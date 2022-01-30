package br.com.fabiofnc.challenge.data.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.fabiofnc.challenge.data.model.CategoriaDespesa;
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
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;
    private String tipoDespesa;
    private String categoria;
    
    public void setCategoria(String categoria) {
    	this.categoria = categoria.toUpperCase().strip();
    }
    
    public void setTipoDespesa(String tipoDespesa) {
    	this.tipoDespesa = tipoDespesa.toUpperCase().strip();
    }
    
}