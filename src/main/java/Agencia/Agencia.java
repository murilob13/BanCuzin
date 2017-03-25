package Agencia;

import java.util.Scanner;


public class Agencia {
	
	Scanner input = new Scanner(System.in);
	
	protected String nome;
	protected int codigo;
	protected String endereco;
	protected String gerente;
	
	Agencia agencia = new Agencia();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
	public Agencia cadastrarAgencia (String nome, int codigo, String endereco, String gerente){
		
		System.out.println("Digite os dados da agencia:");
		System.out.print("Nome: ");
		nome = input.nextLine();
		
		System.out.print("Codigo: ");
		codigo = input.nextInt();
		
		System.out.print("Endereco: ");
		endereco = input.nextLine();
		
		System.out.print("Gerente: ");
		gerente = input.nextLine();
		
		return agencia;
	}
	
	
}
