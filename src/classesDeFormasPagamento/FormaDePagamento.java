package classesDeFormasPagamento;

public class FormaDePagamento { // classe abstract???
	private String nome;
	
	public FormaDePagamento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public double aplicarTaxa(double valor) {
		return valor;
	}
}
