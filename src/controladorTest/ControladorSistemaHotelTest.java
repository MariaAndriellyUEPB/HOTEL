package controladorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
 
import classesDeFormasPagamento.Cartao;
import classesDeFormasPagamento.FormaDePagamento;
import classesDeQuartos.Quarto;
import classesDeQuartos.QuartoLuxo;
import controlador.ControladorSistemaHotel;
import hotelUepb.DiaSemana;
import hotelUepb.Reserva;

import org.junit.Test;

public class ControladorSistemaHotelTest {
	
	private ControladorSistemaHotel controlador = new ControladorSistemaHotel();
	
	@Test
	public void deveCadastrarReserva() {
		
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new Cartao("Cartão");
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
	}
	
	@Test
	public void naoDeveCadastrarDuasReservasComMesmoCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new Cartao("Cartão");
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
		
		Quarto quarto2 = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento2 = new Cartao("Cartão");
		assertFalse(controlador.cadastrarReserva("100", quarto2, "12", "Amalia", formaDePagamento2, DiaSemana.TERCA, 1, 120.0));
	}
	
	@Test
	public void deveBuscarReservaPorCodigo() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new Cartao("Cartão");
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
		
		Reserva reserva = controlador.buscarReservasPorCodigo("100");
		
		assertNotNull(reserva);
		assertEquals("100", reserva.getCodigo());
		assertEquals("Luxo", reserva.getTipoQuarto().getNomedoquarto());
		assertEquals(1, reserva.getQuantidadeDias());
		assertEquals("Maria", reserva.getNomeHospede());
		assertEquals("Cartão", reserva.getFormaDePagamento().getNome());
		assertEquals(DiaSemana.SEGUNDA, reserva.getDiaEntrada());
		assertEquals("1", reserva.getNumeroQuarto());
		assertEquals(100.0, reserva.getValorDiaria(), 0.001);
	}
}
