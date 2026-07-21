package hotelUepb;

import java.util.ArrayList;

public class SistemaHotel {
	private String nomeHotel;
	private ArrayList<Reserva> reservasAtivas;

	SistemaHotel() {
		this.reservasAtivas = new ArrayList<Reserva>();
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public boolean cadastrarReserva(Reserva novaReserva) {
		return reservasAtivas.add(novaReserva);
	}

	public boolean removerReservaPorCodigo(String codigo) {
		for (Reserva minhaReserva : reservasAtivas) {
			if (minhaReserva.getCodigo().equals(codigo)) {
				return reservasAtivas.remove(codigo);
			}
		}
		return false;

	}

	public String buscarReservasPorHospede(String nomeDoHospede) {
		for (Reserva minhaReserva : reservasAtivas) {
			if (minhaReserva.getCodigo().equals(nomeDoHospede)) {
				return minhaReserva.toString();
			}
		}
		return "Nenhuma reserva com o nome de '" + nomeDoHospede + "' foi encontrada.";
	}

	public String exibirRelatorioDeReservas() {
		String info = "";
		for (Reserva minhaReserva : reservasAtivas) {
			info += minhaReserva.toString();
		}
		return info;
	}

	// =============================
	// alterar o calculor pois ele ira ser da classe quarto
	public double calcularPatrimonioTotal() {
		double soma = 0;
		for (Reserva minhaReserva : reservasAtivas) {
			soma += minhaReserva.calcularDiariaTotal();
		}
		return soma;
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
