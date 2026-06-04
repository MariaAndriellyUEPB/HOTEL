package hotelUepb;

public class Reserva {
	String codigo;
	String tipoQuarto;
	String numeroQuarto;
	String nomeHospede;
	String formaDePagamento;
	int quantidadeDias;
	double valorDiaria;
	
	
	Reserva(String codigo, String tipoQuarto, String numeroQuarto, String nomeHospede, 	String formaDePagamento, int quantidadeDias, double valorDiaria){
		this.codigo = codigo;
		this.tipoQuarto = tipoQuarto;
		this.numeroQuarto = numeroQuarto;
		this.nomeHospede = nomeHospede;
		this.formaDePagamento = formaDePagamento;
		this.quantidadeDias = quantidadeDias;
		this.valorDiaria = valorDiaria;
		
	}
	double calcularDiariaTotal() {
		return valorDiaria*quantidadeDias;
	}
	public String toString() {
		return "---Reserva---" + "\n---Dados do Hóspedes---" + 
	"\nCódigo:  " + codigo + 
	"\nNome do hóspede:  " + nomeHospede + 
	"\nForma de pagamento: " + formaDePagamento + 
	"\nQuantidades de dias: " + quantidadeDias + 
	"\n\n---Dados do Quarto---" + "\nTipo e Quarto: " + tipoQuarto + 
	"\nNúmero do quarto: " + numeroQuarto + 
	"\nValor da diária: " + calcularDiariaTotal()   ; 
	}

}
