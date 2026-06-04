package hotelUepb;

public class Hotel 
{
	String nomeHotel;
	double valorDiaria;
	Reserva reservasAtivas[];
	int quantidadeAtual;
	
	Hotel(String nomeHotel, double valorDiaria, int capacidadeMaxima)
	{
		this.valorDiaria = valorDiaria;
		this.nomeHotel = nomeHotel;
		this.reservasAtivas = new Reserva[capacidadeMaxima];
		this.quantidadeAtual = 0;
	}
	
	boolean cadastrarReserva(Reserva novaReserva)
	{

		if(quantidadeAtual >= reservasAtivas.length)
		{
			System.out.println("Sem quartos disponíveis, impossível cadastrar novo hóspede.");
			return false;
		}
		
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
	
}