package Conta;

import java.util.Scanner;

import Cliente.Cliente;

public abstract class Conta implements ContaInterface{

	protected int idConta;
    protected int numeroConta;
    protected int numeroAgencia;
    protected Cliente cliente;
    protected double saldo;
    protected double limite;
    
    Scanner input = new Scanner(System.in);
    
    public Conta (Cliente client, double saldo){
    	this.cliente = client;
    	this.saldo = saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return this.saldo;
    }
    
    public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public void sacar(double valorSaque) {
           while (this.saldo-valorSaque< 0) {
            System.out.println("Saldo invalido! Digite o valor de saque novamente");
            valorSaque = input.nextDouble();
        }
            this.saldo-=valorSaque;
          }
    
    public void depositar(double valorDeposito) {
         this.saldo += valorDeposito;
    }

	public void manutencao(double valor) {
		
	}
	
}
