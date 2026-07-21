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

import org.junit.Test;

public class ControladorSistemaHotelTest {
	
	private ControladorSistemaHotel controlador = new ControladorSistemaHotel();
	
	@Test
	public void deveCadastrarReserva() {
		
		Quarto quarto = new QuartoLuxo("Luxo");
		FormaDePagamento formaDePagamento = new Cartao("Cartão");
		assertTrue(controlador.cadastrarReserva("100", quarto, "1", "Maria", formaDePagamento, DiaSemana.SEGUNDA, 1, 100.0));
	}
	
	

}
