package financiamento;

import cliente.Cliente;

public class Emprestimo {

	protected int idEmprestimo;
	protected Cliente cliente;
	protected TipoEmprestimo tipoBem;
	protected double valor;

	public int getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoEmprestimo getTipoBem() {
		return tipoBem;
	}

	public void setTipoBem(TipoEmprestimo tipoBem) {
		this.tipoBem = tipoBem;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Emprestimo(int idEmprestimo, Cliente cliente, TipoEmprestimo tipoBem, double valor) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.cliente = cliente;
		this.tipoBem = tipoBem;
		this.valor = valor;
	}

	public Emprestimo() {
		super();
	}

	@Override
	public String toString() {
		return "Cliente:" + cliente.getNome() + "\n"+
			   "Tipo do Bem:" + tipoBem.getDescricao() + "\n"+
			   "Valor: "+ valor + "\n";
	}
	
	
}
