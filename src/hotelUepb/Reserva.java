package hotelUepb;

public class Reserva {
	private String codigo;
	private String tipoQuarto;
	private String numeroQuarto;
	private String nomeHospede;
	private String formaDePagamento;
	private int quantidadeDias;
	private double valorDiaria;

	public Reserva(String codigo, String tipoQuarto, String numeroQuarto, String nomeHospede, String formaDePagamento,
			int quantidadeDias, double valorDiaria) {
		this.codigo = codigo;
		this.tipoQuarto = tipoQuarto;
		this.numeroQuarto = numeroQuarto;
		this.nomeHospede = nomeHospede;
		this.formaDePagamento = formaDePagamento;
		this.quantidadeDias = quantidadeDias;
		this.valorDiaria = validarDados(valorDiaria);

	}

	public String getCodigo() {
		return codigo;
	}

	public String getTipoQuarto() {
		return tipoQuarto;
	}

	public String getNumeroQuarto() {
		return numeroQuarto;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}
	
	private double validarDados(double valor) {
		if(valor > 0) {
			return valor;
		}
		return 0.0;
	}

	public double calcularDiariaTotal() {
		return valorDiaria * quantidadeDias;
	}

	public String toString() {
		return "\n================================================================\n--- Dados do Hóspedes ---"
				+ "\n Código:  " + codigo + "\n Nome do hóspede:  " + nomeHospede + "\n Forma de pagamento: "
				+ formaDePagamento + "\n Quantidades de dias: " + quantidadeDias + "\n\n---Dados do Quarto---"
				+ "\n Tipo do Quarto: " + tipoQuarto + "\n Número do quarto: " + numeroQuarto
				+ "\n Valor da diária: R$ " + valorDiaria + "\n Total a pagar: R$ " + calcularDiariaTotal()
				+ "\n================================================================";
	}
}