package hotelUepb;

import java.util.Objects;

import classesDeFormasPagamento.FormaDePagamento;
import classesDeQuartos.Quartos;

public class Reserva {
	private String codigo;
	private Quartos tipoQuarto; // mudar para classe
	private String numeroQuarto;
	private String nomeHospede;
	private FormaDePagamento formaDePagamento; // muda para classe
	private int quantidadeDias;
	private double valorDiaria;

	public Reserva(String codigo, Quartos tipoQuarto, String numeroQuarto, String nomeHospede,
			FormaDePagamento formaDePagamento, int quantidadeDias, double valorDiaria) {
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

	public Quartos getTipoQuarto() {
		return tipoQuarto;
	}

	public String getNumeroQuarto() {
		return numeroQuarto;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	private double validarDados(double valor) {
		if (valor > 0) {
			return valor;
		}
		return 0.0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(codigo, other.codigo);
	}

	// verificar dias da semana ou n????
	public double calcularDiariaTotal() {
		double total = 0;

		for (int i = 0; i < quantidadeDias; i++) {
			double valorBase = tipoQuarto.calcularValorBase(valorDiaria);
			total+= valorBase;
		}
		return formaDePagamento.aplicarTaxa(total);
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