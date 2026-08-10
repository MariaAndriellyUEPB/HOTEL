package classesDeQuartos;

public class QuartoComum extends Quarto{

	public QuartoComum(String nomeDoQuarto) {
		super(nomeDoQuarto); 
	}

	@Override
    public double calcularValorBase(double valorDiaria) {
        return valorDiaria; 
    }
}
