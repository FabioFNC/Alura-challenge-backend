package br.com.fabiofnc.challenge.data.model;

public enum TipoDespesa {

    FIXA,
    VARIAVEL;
	
	private static final String[] TODAS_DESPESAS = {"FIXA", "VARIAVEL"};

	public static String[] getTodasDespesas() {
		return TODAS_DESPESAS;
	}
    
}