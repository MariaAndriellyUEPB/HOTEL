package hotelUepb;

public class Reserva {
	String codigo;
	String tipoQuarto;
	String numeroQuarto;
	String nomeHospede;
	String formaDePagamento;
	int quantidadeDias;
	double valorDiaria;
	
	
	Reserva(String codigo, String tipoQuarto, String numeroQuarto, String nomeHospede, int quantidadeDias, double valorDiaria){
		this.codigo = codigo;
		this.tipoQuarto = tipoQuarto;
		this.numeroQuarto = numeroQuarto;
		this.nomeHospede = nomeHospede;
		this.quantidadeDias = quantidadeDias;
		this.valorDiaria = valorDiaria;
		
	}
	double calcularDiariaTotal() {
		return valorDiaria*quantidadeDias;
	}
	public static String toString() {
		return
	}

}
