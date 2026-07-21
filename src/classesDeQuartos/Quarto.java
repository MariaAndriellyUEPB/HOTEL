package classesDeQuartos;

public abstract class Quarto {
	private String nomeDoQuarto;

	public Quarto(String nomeDoQuarto) {
		this.nomeDoQuarto = nomeDoQuarto;
	}

	public String getNomedoquarto() {
		return nomeDoQuarto;
	}

	public abstract double calcularValorBase(double valor);


}
