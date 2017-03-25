package Finan;

import Cliente.Cliente;

public class Financiamento {
	
	protected Cliente cliente;
	protected double valor;
	protected double juros;
	protected double parcelas;
	
	public Financiamento(Cliente cliente, double valor, double juros) {
		super();
		this.cliente = cliente;
		this.valor = valor;
		this.juros = juros;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getJuros() {
		return juros;
	}
	public void setJuros(double juros) {
		this.juros = juros;
	}
	
	
	
	

}
