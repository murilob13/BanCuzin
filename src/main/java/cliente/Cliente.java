package cliente;

import java.util.Date;

public class Cliente {
	
		private int idCliente;
		private String nome;
		private String cpfCnpj;
		private String endereco;
		private Date birthDay;
		private String sexo;
		
		
		public int getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		
		public String getCpfCnpj() {
			return cpfCnpj;
		}
		public void setCpfCnpj(String cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
		}
		
		public Date getBirthDay() {
			return birthDay;
		}
		public void setBirthDay(Date birthDay) {
			this.birthDay = birthDay;
		}
		
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		
		public Cliente(int idCliente, String nome, String cpfCnpj, String endereco, Date birthDay, String sexo) {
			super();
			this.idCliente = idCliente;
			this.nome = nome;
			this.cpfCnpj = cpfCnpj;
			this.endereco = endereco;
			this.birthDay = birthDay;
			this.sexo = sexo;
		}
		public Cliente() {
			super();
		}
		@Override
		public String toString() {
			return "Nome = " + nome + "\n" + "CPF/CNPJ= " + cpfCnpj + "\n" + "Endereco= "
					+ endereco + "\n" + "Data de Nascimento= " + birthDay + "\n" + "Sexo = " + sexo + "";
		}

		

}
