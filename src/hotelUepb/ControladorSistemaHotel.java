package hotelUepb;

public class ControladorSistemaHotel {
	private SistemaHotel sistema;

	public ControladorSistemaHotel() {
		this.sistema = new SistemaHotel();
	}
	
	public boolean cadastrarReserva(Reserva novaReserva) {
		return sistema.cadastrarReserva(novaReserva);
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
