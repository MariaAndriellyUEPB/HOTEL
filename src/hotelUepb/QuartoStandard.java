package hotelUepb;

public class QuartoStandard extends Quartos{

	public QuartoStandard(String nomeDoQuarto) {
		super(nomeDoQuarto); // pode colocar o nome direto
	}

	@Override
    public double calcularValorBase(double valorDiaria) {
        return valorDiaria; // sem acréscimo
    }
}
