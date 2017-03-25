package Conta;

import Cliente.Cliente;

public class contaPoupanca extends Conta {
	
	public contaPoupanca (Cliente client, double saldo) {
		super(client, saldo);
		this.cliente = client;
		this.saldo = saldo;
	}

}
