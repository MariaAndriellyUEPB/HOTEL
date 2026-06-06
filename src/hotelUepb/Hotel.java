package hotelUepb;

public class Hotel 
{
	String nomeHotel;
	Reserva reservasAtivas[];
	int quantidadeAtual;
	
	Hotel(String nomeHotel, int capacidadeMaxima){	
		this.nomeHotel = nomeHotel;
		this.reservasAtivas = new Reserva[capacidadeMaxima];
		this.quantidadeAtual = 0;
	}
	
	boolean cadastrarReserva(Reserva novaReserva){
		reservasAtivas[quantidadeAtual] = novaReserva;
		quantidadeAtual++;
		return true;
	}
	
	boolean removerReservaPorCodigo (String codigo) {
		for (int i = 0; i < quantidadeAtual; i++) {
			if(reservasAtivas[i].codigo.equalsIgnoreCase(codigo)) {
				for(int j = i; j < quantidadeAtual - 1; j++) {
					reservasAtivas[j] = reservasAtivas[j + 1];	
				}
				reservasAtivas[quantidadeAtual -1] = null;
				quantidadeAtual--;
				return true;
			}
		}
		
		return false;
	}
	
	boolean estaCheio(int capacidadeMaxima) {
		if(quantidadeAtual>=capacidadeMaxima) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean estaVazio() {
		if(quantidadeAtual==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean buscarReservasPorHospede(String nomeDoHospede){
		boolean encontrou = false;
		if(encontrou == false){
			System.out.println("Nenhuma reserva com o nome de '" + nomeDoHospede + "' foi encontrada.");
			return encontrou;
		}
		for(int i = 0; i < quantidadeAtual; i++){
			if(reservasAtivas[i].nomeHospede.equalsIgnoreCase(nomeDoHospede)){
				System.out.println(reservasAtivas[i]);
				encontrou = true;
			}
		}
		return encontrou;
	}
	
	
	
	void exibirRelatorioDeReservas(){
		for(int i = 0; i < quantidadeAtual; i++){
			System.out.println(reservasAtivas[i]);
		}

	}
	
	double calcularPatrimonioTotal() {
		double soma = 0;
		for(int i = 0; i < quantidadeAtual; i++) {
			soma += reservasAtivas[i].calcularDiariaTotal();
		}
		return soma;
	}
}
