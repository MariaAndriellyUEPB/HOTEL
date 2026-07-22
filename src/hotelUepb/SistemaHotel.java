package hotelUepb;

import java.util.ArrayList;

import classesDeFormasPagamento.FormaDePagamento;
import classesDeQuartos.Quarto;

public class SistemaHotel {
	private String nomeHotel;
	private ArrayList<Reserva> reservasAtivas;

	public SistemaHotel() { //CONTRUTOR SEM PARAMETRO POIS DAVA ERRO NO CONTROLADOR, MAS COMO PODEMOS IMPLEMENTAR A CAPACIDADE MÁXIMA E O NOME DO HOTEL?
		this.reservasAtivas = new ArrayList<Reserva>();
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public boolean cadastrarReserva(String codigo, Quarto tipoQuarto, String numeroQuarto, String nomeHospede, FormaDePagamento formaDePagamento, DiaSemana diaEntrada, int quantidadeDias, double valorDiaria) {
		for(Reserva reserva: reservasAtivas) {
			if(reserva.getCodigo().equals(codigo)) {
				return false;
			}
		}
		
		try {
			Reserva novaReserva = new Reserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaDePagamento, diaEntrada, quantidadeDias, valorDiaria);
			reservasAtivas.add(novaReserva);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean removerReservaPorCodigo(String codigo) {
		for(int i = 0; i < reservasAtivas.size(); i++) {
			if(reservasAtivas.get(i).getCodigo().equals(codigo)) {
				reservasAtivas.remove(i);
				return true;
			}
		}
		return false;
	}

	public Reserva buscarReservasPorCodigo(String codigo) {
		for (Reserva minhaReserva : reservasAtivas) {
			if (minhaReserva.getCodigo().equals(codigo)) {
			    return minhaReserva;
			}
		}
		return null;
	}

	public String exibirRelatorioDeReservas() {
		String info = "";
		for (Reserva minhaReserva : reservasAtivas) {
			info += minhaReserva.toString();
		}
		return info;
	}

	public double calcularPatrimonioTotal() {
		double soma = 0;
		for (Reserva minhaReserva : reservasAtivas) {
			soma += minhaReserva.calcularDiariaTotal();
		}
		return soma;
	}
	
	public int contarReservas() {
		return reservasAtivas.size();
	}
	
	public boolean estaCheio(int capacidadeMaxima) {
		if (reservasAtivas.size() >= capacidadeMaxima) {
			return true;
		} else {
			return false;
		}
	}

	public boolean estaVazio() {
		if (reservasAtivas.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
