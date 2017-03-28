package cliente;

import endereco.Endereco;

public class Cliente {
	
		protected int idClient;
		protected String nome;
		protected Endereco endereco;
		protected String dataCadastro;
		protected String cpf;
		protected String telefone;
		
		
		public int getIdClient() {
			return idClient;
		}
		public void setIdClient(int idClient) {
			this.idClient = idClient;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
		public String getDataCadastro() {
			return dataCadastro;
		}
		public void setDataCadastro(String dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getTelefone() {
			return telefone;
		}
		public void SetTelefone(String telefone) throws Exception {
	        if(telefone.length() <8) 
	        	throw new Exception("Telefone não pode ser menor que 8 digitos");
	        this.telefone = telefone;
	    }	    
	    
	   
}
