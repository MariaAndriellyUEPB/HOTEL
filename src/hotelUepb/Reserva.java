package hotelUepb;

import java.util.Objects;

import classesDeFormasPagamento.FormaDePagamento;
import classesDeQuartos.Quarto;

public class Reserva {
	private String codigo;
	private Quarto tipoQuarto; // mudar para classe
	private String numeroQuarto;
	private String nomeHospede;
	private FormaDePagamento formaDePagamento; // muda para classe
	private DiaSemana diaEntrada;
	private int quantidadeDias;
	private double valorDiaria;

	public Reserva(String codigo, Quarto tipoQuarto, String numeroQuarto, String nomeHospede,
			FormaDePagamento formaDePagamento, DiaSemana diaEntrada, int quantidadeDias, double valorDiaria) throws Exception {
		this.codigo = codigo;
		this.tipoQuarto = tipoQuarto;
		this.numeroQuarto = numeroQuarto;
		validaNomeHospede(nomeHospede, "Nenhum nome foi digitado.");
		this.nomeHospede = nomeHospede;
		this.formaDePagamento = formaDePagamento;
		this.diaEntrada = diaEntrada;
		this.quantidadeDias = quantidadeDias;
		validaValorDiaria(valorDiaria, "Valor da diária inválido");
		this.valorDiaria = valorDiaria;

	}

	public String getCodigo() {
		return codigo;
	}

	public Quarto getTipoQuarto() {
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
	
	public DiaSemana getDiaEntrada() {
		return diaEntrada;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}
	
	private void validaValorDiaria(double valor, String mensagem) throws Exception {
		if(valor < 0) {
			throw new Exception(mensagem);
		}
	}
	
	private void validaNomeHospede(String nome, String mensagem) throws Exception {
		if(nome.equals("")) {
			throw new Exception(mensagem);
		}
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
		DiaSemana [] dias = DiaSemana.values(); //
		
		int indice = diaEntrada.ordinal();

		for (int i = 0; i < quantidadeDias; i++) {
			DiaSemana diaAtual = dias[(indice + i) % 7];
			// um objeto do tipo diasemana, que vai receber a quantidade de dias que o vetor esta!
			double diaria = tipoQuarto.calcularValorBase(valorDiaria);
			total += diaria;
			
			if(diaAtual.getTaxa() > 0) {
				total += diaAtual.getTaxa();
			}
			total += diaria;
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