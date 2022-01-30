package br.com.fabiofnc.challenge.data.model;

public enum CategoriaDespesa {
    
    ALIMENTACAO,
    SAUDE,
    MORADIA,
    TRANSPORTE,
    EDUCACAO,
    LAZER,
    IMPREVISTO,
    OUTROS;
	
	private static final String[] TODAS_CATEGORIAS = {"ALIMENTACAO", "SAUDE", "MORADIA", "TRANSPORTE", 
			"EDUCACAO", "LAZER", "IMPREVISTO", "OUTROS"};

	public static String[] getTodasCategorias() {
		return TODAS_CATEGORIAS;
	}

}