package cliente;

public class pessoaJuridica extends Cliente {
	
	private String razaoSocial;
	private String cnpj;
	private String dataAbertura;
	
	public pessoaJuridica (String nome, String razaoSocial, String cnpj, String dataAbertura){

		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.dataAbertura = dataAbertura;
		
	}
	
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	
	

}
