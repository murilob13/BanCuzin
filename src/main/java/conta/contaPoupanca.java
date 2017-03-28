package conta;

import cliente.Cliente;

public class contaPoupanca extends Conta {
	
	public contaPoupanca (Cliente client, double saldo) {
		super(client, saldo);
		this.cliente = client;
		this.saldo = saldo;
	}

}
