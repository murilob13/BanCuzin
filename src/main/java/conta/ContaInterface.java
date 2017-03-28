package conta;

public interface ContaInterface {
	
	void depositar (double valor);
	void sacar (double valor);
	void manutencao (double valor);
	double getSaldo ();

}
