package hotelUepb;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import classesDeQuartos.Quarto;
import classesPagaveis.EstrategiaPagavel;

public class Reserva {
	private String codigo;
	private String nomeHospede;
	private EstrategiaPagavel estrategiaPagamento;
	private DiaSemana diaEntrada;
	private int quantidadeDias;
	private Map<String, Quarto> quartos;

	public Reserva(String codigo, String nomeHospede, EstrategiaPagavel estrategiaPagamento, DiaSemana diaEntrada, int quantidadeDias) throws Exception {
		validaNomeHospede(nomeHospede, "Nenhum nome foi digitado.");
		validaValorMenorQueZero(quantidadeDias, "Valor de quantidade de dias.");

		this.codigo = codigo;
		this.nomeHospede = nomeHospede;
		this.estrategiaPagamento = estrategiaPagamento;
		this.diaEntrada = diaEntrada;
		this.quantidadeDias = quantidadeDias;
		this.quartos = new HashMap<String, Quarto>();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public EstrategiaPagavel getEstrategiaPagamento() {
		return estrategiaPagamento;
	}

	public DiaSemana getDiaEntrada() {
		return diaEntrada;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public Map<String, Quarto> getQuartos() {
		return quartos;
	}

	private void validaValorMenorQueZero(double valor, String mensagem) throws Exception {
		if (valor < 0) {
			throw new Exception(mensagem);
		}
	}

	private void validaNomeHospede(String nome, String mensagem) throws Exception {
		if (nome.isBlank()) {
			throw new Exception(mensagem);
		}
	}

	public boolean adicionarQuarto(Quarto quarto) {
		if (quartos.containsKey(quarto.getNumeroQuarto())) {
			return false;
		}
		quartos.put(quarto.getNumeroQuarto(), quarto);
		return true;
	}

	public boolean removerQuarto(String numeroQuarto) {
		return quartos.remove(numeroQuarto) != null;
	}

	public boolean alterarEstrategiaPagamento(EstrategiaPagavel novaEstrategia) {
		if (novaEstrategia == null) {
			return false;
		}
		this.estrategiaPagamento = novaEstrategia;
		return true;
	}

	public double calcularDiariaTotal() {
		double total = 0;
		int entradaReserva = diaEntrada.ordinal();

		for (Quarto quarto : quartos.values()) {
			double diariaQuarto = quarto.calcularValorBase(quarto.getValorDiaria());

			for (int i = 0; i < quantidadeDias; i++) {
				DiaSemana diaAtual = DiaSemana.values()[(entradaReserva + i) % 7];
				total += diariaQuarto + diaAtual.getTaxa();
			}
		}

		return estrategiaPagamento.aplicarTaxa(total);
	}

	public String toString() {
		String infoQuartos = quartos.values().stream()
				.map(quarto -> "\n  - " + quarto.getNomeDoQuarto() + " (nº " + quarto.getNumeroQuarto() + ")")
				.collect(Collectors.joining());

		return "\n================================================================\n--- Dados do Hóspede ---"
				+ "\nCódigo: " + codigo + "\nNome do hóspede: " + nomeHospede + "\nForma de pagamento: "
				+ estrategiaPagamento.getInfo() + "\nQuantidade de dias: " + quantidadeDias
				+ "\n\n--- Quartos ---" + infoQuartos
				+ "\n\nTotal a pagar: R$ " + calcularDiariaTotal()
				+ "\n================================================================\n";
	}
}
