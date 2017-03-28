package automovel;

public class Carro extends Automovel {
	
	protected int qntPortas;

	public int getQntPortas() {
		return qntPortas;
	}

	public void setQntPortas(int qntPortas) {
		this.qntPortas = qntPortas;
	}

	public Carro(int qntPortas) {
		super();
		this.qntPortas = qntPortas;
	}
	
	

}
