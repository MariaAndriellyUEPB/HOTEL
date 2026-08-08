package classesPagaveis;

public class PagamentoViaCartao implements FormaDePagamento{

	@Override
	public double aplicarTaxa(double valor) {
		return valor + (valor * 0.05);
	}
}