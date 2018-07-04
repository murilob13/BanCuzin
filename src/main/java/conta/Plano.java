package conta;

public enum Plano {

	S("Plano Silver", "S"),
	G("Plano Gold", "G"),
	D("Plano Diamond", "D");

	private String descricao;
	private String tpPlano;

	Plano(String descricao){
		this.descricao = descricao;
	}
	
	Plano (String descricao, String tpPlano){
		this.descricao = descricao;
		this.tpPlano = tpPlano;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTpPlano() {
		return tpPlano;
	}
	
	
}
