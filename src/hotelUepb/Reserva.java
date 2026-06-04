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
		return "\n---Dados do Hóspedes---" +
	"\n Código:  " + codigo +
	"\n Nome do hóspede:  " + nomeHospede +
	"\n Forma de pagamento: " + formaDePagamento +
	"\n Quantidades de dias: " + quantidadeDias +
	"\n\n---Dados do Quarto---" + "\n Tipo e Quarto: " + tipoQuarto +
	"\n Número do quarto: " + numeroQuarto +
	"\n Valor da diária: " + valorDiaria +
	"\n Total a pagar: " + calcularDiariaTotal();
	}
}
