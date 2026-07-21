package classesDeFormasPagamento;

public class Cartao extends FormaDePagamento {
	private static final double Taxa = 0.03; // 3%
	
	public Cartao(String nome) {
		super(nome);
	}
	
	@Override
	public double aplicarTaxa(double valor) {
		return valor + (valor * Taxa);
	}
}
 