package classesDeFormasPagamento;

public class Pix extends FormaDePagamento {
	private static double DESCONTO = 0.05; // 

	public Pix(String nome) {
		super(nome);
	}

	@Override
	public double aplicarTaxa(double valor) {
		return valor - (valor * DESCONTO);
	}

}
