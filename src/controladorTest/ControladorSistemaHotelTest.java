package controladorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import classesDeQuartos.Quarto;
import classesDeQuartos.QuartoComum;
import classesDeQuartos.QuartoLuxo;
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
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
		assertEquals(1, controlador.contarReservas());
	}
	
	@Test
	public void naoDeveCadastrarDuasReservasComMesmoCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
		
		Quarto quarto2 = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento2 = new PagamentoViaCartao();
		assertFalse(controlador.cadastrarReserva("100", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 120.0));
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
		assertTrue(reserva.getFormaDePagamento() instanceof PagamentoViaCartao);
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
		
		// Standard sem acréscimo (100) + taxa de SEGUNDA (0) + 5% do cartão = 105.0
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
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		
		assertEquals(0, controlador.contarReservas());
	}
	
	@Test
	public void retornaFalsoQuandoNomeVazio() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertFalse(controlador.cadastrarReserva("100", quarto, "1", "", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
	}
	
	@Test
	public void retornaFalsoQuandoValorDiariaInvalido() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertFalse(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, -1));
	}
	
	@Test
	public void deveRemoverReservaPorCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		
		boolean reserva = controlador.removerReservaPorCodigo("100");
		
		assertTrue(reserva);
		assertEquals(0, controlador.contarReservas());
	}
	
	@Test
	public void deveRetornarFalseSeCodigoNaoEncontrado() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		
		boolean reserva = controlador.removerReservaPorCodigo("999");
		
		assertFalse(reserva);
		assertEquals(1, controlador.contarReservas());
	}
	
	@Test
	public void deveCalcularPatrimonioTotal() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		
		Quarto quarto2 = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 100.0);
		
		double esperado = controlador.buscarReservasPorCodigo("100").calcularDiariaTotal()
				+ controlador.buscarReservasPorCodigo("101").calcularDiariaTotal();
		assertEquals(esperado, controlador.calcularPatrimonioTotal(), 0.001);
	}
	
	@Test
	public void deveContarReservas() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0);
		
		Quarto quarto2 = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento2 = new PagamentoViaCartao();
		controlador.cadastrarReserva("101", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 100.0);
		
		assertEquals(2, controlador.contarReservas());
	}
	
	@Test
	public void deveAplicarDescontoNoPix() {
		FormaDePagamento formaDePagamento = new PagamentoViaPix();
		assertEquals(95.0, formaDePagamento.aplicarTaxa(100), 0.001);
	}
	
	@Test
	public void deveAplicarTaxaNoCartao() {
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		assertEquals(105.0, formaDePagamento.aplicarTaxa(100), 0.001);
	}
	
	@Test
	public void deveAplicarTaxaNoBoleto() {
		FormaDePagamento formaDePagamento = new PagamentoViaBoleto();
		
		assertEquals(102.0, formaDePagamento.aplicarTaxa(100.0), 0.001);
	}
	
	@Test
	public void deveCalcularTaxaDeSexta() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento,
				DiaSemana.SEXTA, 1, 100.0);
		
		Reserva reserva = controlador.buscarReservasPorCodigo("100");
		
		assertEquals(131.25, reserva.calcularDiariaTotal(), 0.001);
	}
	
	@Test
	public void deveCalcularTaxaDeSabado() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento,
				DiaSemana.SABADO, 1, 100.0);
		
		Reserva reserva = controlador.buscarReservasPorCodigo("100");
		
		assertEquals(157.50, reserva.calcularDiariaTotal(), 0.001);
	}
	
	@Test
	public void deveCalcularTaxaDeDomingo() {
		Quarto quarto = new QuartoComum("Quarto Comum");
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento,
				DiaSemana.DOMINGO, 1, 100.0);
		
		Reserva reserva = controlador.buscarReservasPorCodigo("100");
		
		assertEquals(157.50, reserva.calcularDiariaTotal(), 0.001);
	}
	
	@Test
	public void deveCalcularValorDoQuartoLuxo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		
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
		FormaDePagamento formaDePagamento = new PagamentoViaCartao();
		
		controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento,
				DiaSemana.SEGUNDA, 1, 100.0);
		 
		String esperada = controlador.exibirRelatorioDeReservas();
		
		assertTrue(esperada.contains("100"));
		assertTrue(esperada.contains("Maria"));
		assertTrue(esperada.contains("Quarto Comum"));
	}
}
