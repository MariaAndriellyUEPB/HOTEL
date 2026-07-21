package controlador;

import classesDeFormasPagamento.FormaDePagamento;
import classesDeQuartos.Quarto;
import hotelUepb.DiaSemana;
import hotelUepb.SistemaHotel;

public class ControladorSistemaHotel {
	private SistemaHotel sistema;

	public ControladorSistemaHotel() {
		this.sistema = new SistemaHotel();
	}
	
	public boolean cadastrarReserva(String codigo, Quarto tipoQuarto, String numeroQuarto, String nomeHospede, FormaDePagamento formaDePagamento, DiaSemana diaEntrada, int quantidadeDias, double valorDiaria) {
		return sistema.cadastrarReserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaDePagamento, diaEntrada, quantidadeDias, valorDiaria);
	}
	
	public boolean removerReservaPorCodigo(String codigo) {
		return sistema.removerReservaPorCodigo(codigo);
	}
	
	public String buscarReservasPorHospede(String nomeDoHospede) {
		return sistema.buscarReservasPorHospede(nomeDoHospede);
	}
	
	public String exibirRelatorioDeReservas() {
		return sistema.exibirRelatorioDeReservas();
	}
	
	public double calcularPatrimonioTotal() {
		return sistema.calcularPatrimonioTotal();
	}
	
	public boolean estaCheio(int capacidadeMaxima) {
		return sistema.estaCheio(capacidadeMaxima);
	}
	
	public boolean estaVazio() {
		return sistema.estaVazio();
	} 
} 
