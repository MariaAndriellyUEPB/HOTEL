package controladorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import classesDeQuartos.Quarto;
import classesDeQuartos.QuartoComum;
import classesDeQuartos.QuartoLuxo;
import classesPagaveis.EstrategiaPagavel;
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

		Quarto quarto = new QuartoLuxo("Luxo", "1", 100.0);
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		assertTrue(controlador.cadastrarReserva("100", "1", formaDePagamento, DiaSemana.SEGUNDA, 1));
		assertEquals(1, controlador.contarReservas());
	}

	@Test
	public void naoDeveCadastrarDuasReservasComMesmoCodigo() {
		
		Quarto quarto = new QuartoLuxo("Luxo", "3", 100.0);
		EstrategiaPagavel formaDePagamento = new PagamentoViaCartao();
		assertTrue(controlador.cadastrarReserva("100", "Lara", formaDePagamento, DiaSemana.SEGUNDA, 1));

		Quarto quarto2 = new QuartoLuxo("Luxo", "2", 100);
		EstrategiaPagavel formaDePagamento2 = new PagamentoViaCartao();
		assertFalse(controlador.cadastrarReserva("100", "Maria", formaDePagamento2, DiaSemana.TERCA, 1));
	}

	@Test
	public void deveBuscarReservaPorCodigo() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		assertNotNull(reserva);
		assertEquals("100", reserva.getCodigo());
		assertEquals("Maria", reserva.getNomeHospede());
		assertEquals("Cartao", reserva.getEstrategiaPagamento().toString());
		assertEquals(DiaSemana.SEGUNDA, reserva.getDiaEntrada());
		assertEquals(1, reserva.getQuantidadeDias());
	}

	@Test
	public void deveRetornarNullAoBuscarCodigoInexistente() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		assertNull(controlador.buscarReservasPorCodigo("999"));
	}
	
	public void deveAdicionarQuartoNaReserva() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		Quarto quarto = new QuartoComum("Quarto Comum", "1", 100.0);
		assertTrue(controlador.adicionarQuartoNaReserva("100", quarto));

		Map<String, Quarto> quartos = controlador.buscarReservasPorCodigo("100").getQuartos();
		assertEquals(1, quartos.size());
		assertTrue(quartos.containsKey("1"));
	}

	@Test
	public void naoDeveAdicionarQuartoComMesmoNumeroNaMesmaReserva() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		controlador.adicionarQuartoNaReserva("100", new QuartoComum("Quarto Comum", "1", 100.0));
		boolean resultado = controlador.adicionarQuartoNaReserva("100", new QuartoLuxo("Luxo", "1", 150.0));

		assertFalse(resultado);
		assertEquals(1, controlador.buscarReservasPorCodigo("100").getQuartos().size());
	}
	
	@Test
	public void naoDeveAdicionarQuartoEmReservaInexistente() {
		Quarto quarto = new QuartoComum("Quarto Comum", "1", 100.0);
		assertFalse(controlador.adicionarQuartoNaReserva("999", quarto));
	}

	@Test
	public void deveRetornarCalculoDiariaTotalComUmQuarto() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);
		controlador.adicionarQuartoNaReserva("100", new QuartoComum("Quarto Comum", "1", 100.0));

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		// Comum sem acréscimo (100) + taxa de SEGUNDA (0) + 5% do cartão = 105.0
		assertEquals(105.0, reserva.calcularDiariaTotal(), 0.001);
	}


	@Test
	public void deveRetornarCalculoDiariaTotalComDoisQuartos() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);
		controlador.adicionarQuartoNaReserva("100", new QuartoComum("Quarto Comum", "1", 100.0));
		controlador.adicionarQuartoNaReserva("100", new QuartoComum("Quarto Comum", "2", 100.0));

		Reserva reserva = controlador.buscarReservasPorCodigo("100");

		// (100 + 0) + (100 + 0) = 200, + 5% do cartão = 210.0
		assertEquals(210.0, reserva.calcularDiariaTotal(), 0.001);
	}

	@Test
	public void deveRemoverReservaPorCodigo() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		assertTrue(controlador.removerReservaPorCodigo("100"));
		assertEquals(0, controlador.contarReservas());
	}
	
	@Test
	public void deveRetornarFalseSeCodigoNaoEncontrado() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		assertFalse(controlador.removerReservaPorCodigo("999"));
		assertEquals(1, controlador.contarReservas());
	}
	
	@Test
	public void deveCalcularPatrimonioTotal() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);
		controlador.adicionarQuartoNaReserva("100", new QuartoComum("Quarto Comum", "1", 100.0));

		EstrategiaPagavel estrategia2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", "Amalia", estrategia2, DiaSemana.TERCA, 1);
		controlador.adicionarQuartoNaReserva("101", new QuartoComum("Quarto Comum", "12", 100.0));

		double esperado = controlador.buscarReservasPorCodigo("100").calcularDiariaTotal()
				+ controlador.buscarReservasPorCodigo("101").calcularDiariaTotal();
		assertEquals(esperado, controlador.calcularPatrimonioTotal(), 0.001);
	}
	
	@Test
	public void deveContarReservas() {
		EstrategiaPagavel estrategia = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", "Maria", estrategia, DiaSemana.SEGUNDA, 1);

		EstrategiaPagavel estrategia2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", "Amalia", estrategia2, DiaSemana.TERCA, 1);

		assertEquals(2, controlador.contarReservas());
	}

	@Test
	public void deveIniciarComNenhumaReserva() {
		assertEquals(0, controlador.contarReservas());
	}

	@Test
	public void deveEstarVazioQuandoNaoPossuiReservas() {
		assertTrue(controlador.estaVazio());
	}

	
}


	