package classesPagaveis;

public class PagamentoViaPix implements FormaDePagamento{

	@Override
	public double aplicarTaxa(double valor) {
		return valor - (valor * 0.05);
	}

}
