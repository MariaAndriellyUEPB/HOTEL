package hotelUepb;

import classesDeQuartos.Quarto;
import classesPagaveis.FormaDePagamento;

public class Reserva {
	private String codigo;
	private Quarto tipoQuarto;
	private String numeroQuarto;
	private String nomeHospede;
	private FormaDePagamento formaDePagamento;
	private DiaSemana diaEntrada;
	private int quantidadeDias;
	private double valorDiaria;

	public Reserva(String codigo, Quarto tipoQuarto, String numeroQuarto, String nomeHospede,
			FormaDePagamento formaDePagamento, DiaSemana diaEntrada, int quantidadeDias, double valorDiaria) throws Exception {
		validaNomeHospede(nomeHospede, "Nenhum nome foi digitado.");
		validaValorMenorQueZero(valorDiaria, "Valor da diária inválido");
        validaValorMenorQueZero(quantidadeDias, "Valor de quantidade de dias.");

		this.codigo = codigo;
		this.tipoQuarto = tipoQuarto;
		this.numeroQuarto = numeroQuarto;
		this.nomeHospede = nomeHospede;
		this.formaDePagamento = formaDePagamento;
		this.diaEntrada = diaEntrada;
		this.quantidadeDias = quantidadeDias;
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
	
	private void validaValorMenorQueZero(double valor, String mensagem) throws Exception {
		if(valor < 0) {
			throw new Exception(mensagem);
		}
	}
	
	private void validaNomeHospede(String nome, String mensagem) throws Exception {
		if(nome.isBlank()) {
			throw new Exception(mensagem);
		}
	}
		
	public double calcularDiariaTotal() {
	    double total = 0;
	    int indice = diaEntrada.ordinal(); 

	    for (int i = 0; i < quantidadeDias; i++) {
	        DiaSemana diaAtual = DiaSemana.values()[(indice + i) % 7];

	        double diaria = tipoQuarto.calcularValorBase(valorDiaria);
	        total += (diaria + diaAtual.getTaxa());
	    }

	    return formaDePagamento.aplicarTaxa(total);
	    
	}

	public String toString() {
		return "\n================================================================\n--- Dados do Hóspedes ---"
				+ "\nCódigo: " + codigo + "\nNome do hóspede: " + nomeHospede + "\n Forma de pagamento: "
				+ formaDePagamento.toString() + "\n Quantidades de dias: " + quantidadeDias + "\n\n---Dados do Quarto---"
				+ "\n Tipo do Quarto: " + tipoQuarto.getNomedoquarto() + "\n Número do quarto: " + numeroQuarto
				+ "\n Valor da diária: R$ " + valorDiaria + "\n Total a pagar: R$ " + calcularDiariaTotal()
				+ "\n================================================================\n";
	}
}