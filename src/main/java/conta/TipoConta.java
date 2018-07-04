package conta;

public enum TipoConta {

	CP ("Conta Poupança", "CP"),
	CC ("Conta Corrente", "CC"),
	CS ("Conta Salário", "CS");

	private String descricao;
	private String tpConta;

	TipoConta(String descricao) {
		this.descricao = descricao;
	}
	
	TipoConta (String descricao, String tpConta){
		this.descricao = descricao;
		this.tpConta = tpConta;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTpConta() {
		return tpConta;
	}
}
