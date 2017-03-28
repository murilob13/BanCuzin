package automovel;

public class Caminhao extends Automovel {
	
	protected double peso;
	protected double capacidadeTotal;
	
		
	public Caminhao(double peso, double capacidadeTotal) {
		super();
		this.peso = peso;
		this.capacidadeTotal = capacidadeTotal;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getCapacidadeTotal() {
		return capacidadeTotal;
	}
	public void setCapacidadeTotal(double capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}
	
	

}
