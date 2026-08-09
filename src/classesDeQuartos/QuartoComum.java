package classesDeQuartos;

public class QuartoComum extends Quarto{

	public QuartoComum(String nomeDoQuarto) {
		super(nomeDoQuarto); // pode colocar o nome direto
	}

	@Override
    public double calcularValorBase(double valorDiaria) {
        return valorDiaria; // sem acréscimo
    }
}
