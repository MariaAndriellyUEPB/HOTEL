package hotelUepb;

public class Hotel 
{
	String nomeHotel;
	double valorDiaria;
	Reserva reservasAtivas[];
	int quantidadeAtual;
	
	Hotel(String nomeHotel, double valorDiaria, int capacidadeMaxima){	
		this.valorDiaria = valorDiaria;
		this.nomeHotel = nomeHotel;
		this.reservasAtivas = new Reserva[capacidadeMaxima];
		this.quantidadeAtual = 0;
	}
	
	boolean cadastrarReserva(Reserva novaReserva){
			if(quantidadeAtual >= reservasAtivas.length){
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
	
	Reserva[] buscarReservasPorHospede(String nomeDoHospede)
	{
		if(quantidadeAtual == 0){
			System.out.println("Nenhum hóspede cadastrado.");
			return null;
		}
		int somaReservas = 0;
		for(int i = 0; i < quantidadeAtual; i++){
			if(reservasAtivas[i].nomeHospede.equalsIgnoreCase(nomeDoHospede)){
				somaReservas++;
			}
		}
		if(somaReservas == 0){
			System.out.println("Nenhuma reserva com o nome de '" + nomeDoHospede + "' foi encontrada.");
			return null;
		}
		Reserva auxiliar[] = new Reserva[somaReservas];
		int j = 0;
		for(int i = 0; i < quantidadeAtual; i++){
			if(reservasAtivas[i].nomeHospede.equalsIgnoreCase(nomeDoHospede)){
				auxiliar[j] = reservasAtivas[i];
				
				j++;
			}
		}
		return auxiliar;
	}
	
	void exibirRelatorioDeReservas(){
		if(quantidadeAtual == 0){
			System.out.println("Nenhum hóspede cadastrado.");
			return;
		}
		
		for(int i = 0; i < quantidadeAtual; i++){
			System.out.println(reservasAtivas[i]);
		}

	}
	
	double calcularPatrimonioTotal() {
		double soma = 0;
		for(int i = 0; i < reservasAtivas.length; i++) {
			soma += reservasAtivas[i].calcularDiariaTotal();
		}
		return soma;
	}
	
	}	

