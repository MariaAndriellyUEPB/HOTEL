package classesDeQuartos;

public class QuartoComum extends Quarto{
	
	public QuartoComum(String nomeDoQuarto, String numeroQuarto, double valorDiaria) {
		super(nomeDoQuarto, numeroQuarto, valorDiaria);
	}

	@Override
    public double calcularValorBase(double valorDiaria) {
        return valorDiaria; 
    }
}
