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
	public void deveBuscarReservaPorHospede() {
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new Cartao("Cartão");
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
		
		controlador.buscarReservasPorHospede("Maria");
		
		
		String esperado = "\n================================================================\n--- Dados do Hóspedes ---"
		+ "\n Código:  " + "100" + "\n Nome do hóspede:  " + "Maria" + "\n Forma de pagamento: "
		+ "Cartão" + "\n Quantidades de dias: " + "1" + "\n\n---Dados do Quarto---"
		+ "\n Tipo do Quarto: " + "Luxo" + "\n Número do quarto: " + "1"
		+ "\n Valor da diária: R$ " + "100.0" + "\n Total a pagar: R$ " + calcularDiariaTotal()
		+ "\n================================================================";
	}

}
