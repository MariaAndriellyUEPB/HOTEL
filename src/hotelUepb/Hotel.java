package hotelUepb;

import java.util.ArrayList;

public class Hotel {
	private String nomeHotel;
	private ArrayList<Reserva> reservasAtivas;

	Hotel(String nomeHotel, int capacidadeMaxima) {
		this.nomeHotel = nomeHotel;
		this.reservasAtivas = new ArrayList<Reserva>();

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

	//===============================
	boolean estaCheio(int capacidadeMaxima) {
		if (quantidadeAtual >= capacidadeMaxima) {
			return true;
		} else {
			return false;
		}
	}

	boolean estaVazio() {
		if (quantidadeAtual == 0) {
			return true;
		} else {
			return false;
		}
	}

	boolean buscarReservasPorHospede(String nomeDoHospede) {
		boolean encontrou = false;
		for (int i = 0; i < quantidadeAtual; i++) {
			if (reservasAtivas[i].nomeHospede.equalsIgnoreCase(nomeDoHospede)) {
				System.out.println(reservasAtivas[i]);
				encontrou = true;
			}
		}

		if (encontrou == false) {
			System.out.println("Nenhuma reserva com o nome de '" + nomeDoHospede + "' foi encontrada.");
			return encontrou;
		}

		return encontrou;
	}

	void exibirRelatorioDeReservas() {
		for (int i = 0; i < quantidadeAtual; i++) {
			System.out.println(reservasAtivas[i]);
		}

	}

	double calcularPatrimonioTotal() {
		double soma = 0;
		for (int i = 0; i < quantidadeAtual; i++) {
			soma += reservasAtivas[i].calcularDiariaTotal();
		}
		return soma;
	}
}
