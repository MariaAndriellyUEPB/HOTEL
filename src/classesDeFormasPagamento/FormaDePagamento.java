package classesDeFormasPagamento;

public abstract class FormaDePagamento { // classe abstract???
	private String nome;

	public FormaDePagamento(String nome) {
		this.nome = nome;
	}

	public String getNome() { 
		return nome;
	}

	public abstract double aplicarTaxa(double valor);
}
