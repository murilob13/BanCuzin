package conta;

import agencia.Agencia;
import cliente.Cliente;

public class Conta {

	protected int idConta;
	protected Agencia agencia = new Agencia();
	protected String numConta;
	protected Cliente cliente = new Cliente();
	protected TipoConta tipoConta;
	protected Plano plano;
	protected double saldo;
	protected double limite;
	
	
	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public Conta(int idConta, Agencia agencia, String numConta, Cliente cliente, TipoConta tipoConta, Plano plano,
			double saldo, double limite) {
		super();
		this.idConta = idConta;
		this.agencia = agencia;
		this.numConta = numConta;
		this.cliente = cliente;
		this.tipoConta = tipoConta;
		this.plano = plano;
		this.saldo = saldo;
		this.limite = limite;
	}

	public Conta() {
		super();
	}

	@Override
	public String toString() {
		return  "Numero da Agencia: "+agencia.getCodigo()+"\n"+
				"Numero da Conta: "+numConta+"\n"+
				"Nome do Cliente: "+cliente.getNome()+"\n"+
				"Tipo da Conta: "+tipoConta.getDescricao()+"\n"+
				"Tipo do Plano: "+plano.getDescricao()+"\n"+
				"Saldo: "+saldo+"\n"+
				"Limite: "+limite+"\n";
	}

}
