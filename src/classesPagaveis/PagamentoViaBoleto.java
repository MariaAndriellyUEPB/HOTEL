package classesPagaveis;

public class PagamentoViaBoleto implements FormaDePagamento {

	@Override
	public double aplicarTaxa(double valor) {
		return valor + (valor * 0.02);
	}
}