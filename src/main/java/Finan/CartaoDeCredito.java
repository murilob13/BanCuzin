package Finan;

import java.util.Date;

import Cliente.Cliente;
import Conta.Conta;

public class CartaoDeCredito {
	
	protected int numeroCartao;
	protected Cliente cliente;
	protected Conta conta;
	protected Date dataValidade;
	protected short codigoVerificacao;
	
	
	public CartaoDeCredito(int numeroCartao, Cliente cliente, Conta conta, Date dataValidade, short codigoVerificacao) {
		super();
		this.numeroCartao = numeroCartao;
		this.cliente = cliente;
		this.conta = conta;
		this.dataValidade = dataValidade;
		this.codigoVerificacao = codigoVerificacao;
	}
	
	public int getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public short getCodigoVerificacao() {
		return codigoVerificacao;
	}
	public void setCodigoVerificacao(short codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}
	
		
}
