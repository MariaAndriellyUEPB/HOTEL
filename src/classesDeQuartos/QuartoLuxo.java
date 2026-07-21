package classesDeQuartos;

public class QuartoLuxo extends Quarto {

	private static double ACRESCIMO = 0.30; // atributo estatico

	public QuartoLuxo(String nomeDoQuarto) {
		super(nomeDoQuarto);
	}

	@Override
	public double calcularValorBase(double valorDiaria) {
		return valorDiaria + (valorDiaria * ACRESCIMO);
	}

}
