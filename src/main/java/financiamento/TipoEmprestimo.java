package financiamento;

public enum TipoEmprestimo {

	V ("Veiculo", "V"),
	I("Imovel", "I"),
	P ("Pessoal", "P");
	
	private String descricao;
	private String tipoBem;
	
	TipoEmprestimo(String descricao){
		this.descricao = descricao;
	}
	
	TipoEmprestimo (String descricao, String tipoBem){
		this.descricao = descricao;
		this.tipoBem = tipoBem;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTipoBem() {
		return tipoBem;
	}
	
}
