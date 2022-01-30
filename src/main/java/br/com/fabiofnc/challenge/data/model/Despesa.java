package br.com.fabiofnc.challenge.data.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "tb_despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;
    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoria;
    
    public void setCategoria(CategoriaDespesa categoria) {
    	if (categoria == null) categoria = CategoriaDespesa.OUTROS;
    	this.categoria = categoria;
    }
    
    public void setTipoDespesa(TipoDespesa tipoDespesa) {
    	if (tipoDespesa == null) tipoDespesa = TipoDespesa.FIXA;
    	this.tipoDespesa = tipoDespesa;
    }
    
}