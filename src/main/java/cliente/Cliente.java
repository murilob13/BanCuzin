package cliente;

import java.sql.Date;

public class Cliente {
	
		private String nome;
		private String endereco;
		private String cpfCnpj;
		private Date birthDay;
		private String sexo;
		
		
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
	

}
