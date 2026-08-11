package controladorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import classesDeQuartos.Quarto;
import classesDeQuartos.QuartoComum;
import classesDeQuartos.QuartoLuxo;
import classesPagaveis.EstrategiaPagavel;
import classesPagaveis.FormaDePagamento;
import classesPagaveis.PagamentoViaBoleto;
import classesPagaveis.PagamentoViaCartao;
import classesPagaveis.PagamentoViaPix;
import controlador.ControladorSistemaHotel;
import hotelUepb.DiaSemana;
import hotelUepb.Reserva;

import org.junit.Test;

public class ControladorSistemaHotelTest {

	private ControladorSistemaHotel controlador = new ControladorSistemaHotel();

	@Test
	public void deveCadastrarReserva() {

		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1,
				100.0));
		assertEquals(1, controlador.contarReservas());
	}

	@Test
	public void naoDeveCadastrarDuasReservasComMesmoCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1,
				100.0));

		Quarto quarto2 = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento2 = new PagamentoViaCartao();
		assertFalse(controlador.cadastrarReserva("100", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1,
				120.0));
	}

	@Test
	public void deveBuscarReservaPorCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		Reserva reserva = controlador.buscarReservasPorCodigo("100");
		
		assertNotNull(reserva);
		assertEquals("100", reserva.getCodigo());
		assertEquals("Luxo", reserva.getTipoQuarto().getNomedoquarto());
		assertEquals(1, reserva.getQuantidadeDias());
		assertEquals("Maria", reserva.getNomeHospede());
		assertEquals("Cartao", reserva.getFormaDePagamento().toString());
		assertEquals(DiaSemana.SEGUNDA, reserva.getDiaEntrada());
		assertEquals("1", reserva.getNumeroQuarto());
		assertEquals(100.0, reserva.getValorDiaria(), 0.001);
	}

	@Test
	public void deveRetornarNullAoBuscarCodigoInexistente() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		assertNull(controlador.buscarReservasPorCodigo("999"));
	}

	@Test
	public void deveRetornarCalculoDiariaTotal() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		assertEquals(105.0, reserva.calcularDiariaTotal(), 0.001);
	}

	@Test
	public void naoDeveCadastrarQuandoValorDiariaInvalida() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, -1);

		assertEquals(0, controlador.contarReservas());
	}

	@Test
	public void naoDeveCadastrarQuandoNomeVazio() {
		Quarto quarto = new QuartoLuxo("Luxo");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		assertEquals(0, controlador.contarReservas());
	}

	@Test
	public void retornaFalsoQuandoNomeVazio() {
		Quarto quarto = new QuartoLuxo("Luxo", "1", 100.0);
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		assertFalse(
				controlador.cadastrarReserva("100", quarto, "1", "", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
	}

	@Test
	public void retornaFalsoQuandoValorDiariaInvalido() {
		Quarto quarto = new QuartoLuxo("Luxo", "10", -1);
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		assertFalse(
				controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, -1));
	}

	@Test
	public void deveRemoverReservaPorCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo", "1", 100.0);
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		boolean reserva = controlador.removerReservaPorCodigo("100");

		assertTrue(reserva);
		assertEquals(0, controlador.contarReservas());
	}

	@Test
	public void deveRetornarFalseSeCodigoNaoEncontrado() {
		Quarto quarto = new QuartoLuxo("Luxo", "1" , 0);
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		boolean reserva = controlador.removerReservaPorCodigo("999");

		assertFalse(reserva);
		assertEquals(1, controlador.contarReservas());
	}

	@Test
	public void deveCalcularPatrimonioTotal() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		Quarto quarto2 = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 100.0);

		double esperado = controlador.buscarReservasPorCodigo("100").calcularDiariaTotal()
				+ controlador.buscarReservasPorCodigo("101").calcularDiariaTotal();
		assertEquals(esperado, controlador.calcularPatrimonioTotal(), 0.001);
	}

	@Test
	public void deveContarReservas() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);

		Quarto quarto2 = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 100.0);

		assertEquals(2, controlador.contarReservas());
	}

	@Test
	public void deveAplicarDescontoNoPix() {
		EstrategiaPagavel formaDePagamento = new PagamentoViaPix();
		assertEquals(95.0, formaDePagamento.aplicarTaxa(100), 0.001);
	}

	@Test
	public void deveAplicarTaxaNoCartao() {
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		assertEquals(105.0, formaDePagamento.aplicarTaxa(100), 0.001);
	}

	@Test
	public void deveAplicarTaxaNoBoleto() {
		EstrategiaPagavel formaDePagamento = new PagamentoViaBoleto();

		assertEquals(102.0, formaDePagamento.aplicarTaxa(100.0), 0.001);
	}

	@Test
	public void deveCalcularTaxaDeSexta() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();

		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEXTA, 1, 100.0);

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		assertEquals(131.25, reserva.calcularDiariaTotal(), 0.001);
	}

	@Test
	public void deveCalcularTaxaDeSabado() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();

		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SABADO, 1, 100.0);

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		assertEquals(157.50, reserva.calcularDiariaTotal(), 0.001);
	}

	@Test
	public void deveCalcularTaxaDeDomingo() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();

		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.DOMINGO, 1, 100.0);

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		assertEquals(157.50, reserva.calcularDiariaTotal(), 0.001);
	}

	@Test
	public void deveCalcularValorDoQuartoLuxo() {
		Quarto quarto = new QuartoLuxo("Luxo", "105", 100);

		assertEquals(130.0, quarto.calcularValorBase(100.0), 0.001);
	}

	@Test
	public void deveCalcularValorDoQuartoComum() {
		Quarto quarto = new QuartoComum("Quarto Comum");

		assertEquals(100.0, quarto.calcularValorBase(100.0), 0.001);
	}

	@Test
	public void deveIniciarComNenhumaReserva() {
		assertEquals(0, controlador.contarReservas());
	}

	@Test
	public void deveEstarVazioQuandoNaoPossuiReservas() {
		assertTrue(controlador.estaVazio());
	}

	@Test
	public void deveExibirRelatorioComDadosDaReserva() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		String esperada = "\n================================================================\n"
				+ "--- Dados do Hóspedes ---" + "\nCódigo: 100" + "\nNome do hóspede: Maria"
				+ "\nForma de pagamento: Cartao" + "\nQuantidades de dias: 1" + "\n\n---Dados do Quarto---"
				+ "\nTipo do Quarto: Quarto Comum" + "\nNúmero do quarto: 1" + "\nValor da diária: R$ 100.0"
				+ "\n Total a pagar: R$ 105.0" + "\n================================================================\n";
		assertEquals(esperada, controlador.exibirRelatorioDeReservas());
	}
}
