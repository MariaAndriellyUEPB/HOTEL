package classesDeFormasPagamento;

public class Boleto extends FormaDePagamento {
	private static double taxaFixa = 2.00;
	
	public Boleto(String nome) {
		super(nome);
	}
	
	@Override
	public double aplicarTaxa(double valor) {
		return valor + taxaFixa;
	}

}
