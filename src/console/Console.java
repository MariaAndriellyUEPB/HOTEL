package console;

import java.util.Scanner;

import classesDeQuartos.Quarto;
import classesDeQuartos.QuartoComum;
import classesDeQuartos.QuartoLuxo;
import classesPagaveis.FormaDePagamento;
import classesPagaveis.PagamentoViaBoleto;
import classesPagaveis.PagamentoViaCartao;
import classesPagaveis.PagamentoViaPix;
import controlador.ControladorSistemaHotel;
import hotelUepb.DiaSemana;
import hotelUepb.Reserva;
import hotelUepb.SistemaHotel;

public class Console {
	private Scanner leitor = new Scanner(System.in);
	private ControladorSistemaHotel controlador = new ControladorSistemaHotel();
	private SistemaHotel sistema = new SistemaHotel();

	public String lerTexto(String mensagem) {
		System.out.print(mensagem);
		return leitor.nextLine();
	}

	public int lerInteiro(String mensagem) {
		System.out.print(mensagem);
		int valor = leitor.nextInt();
		leitor.nextLine();
		return valor;
	}

	public double lerDouble(String mensagem) {
		System.out.print(mensagem);
		double valorDouble = leitor.nextDouble();
		leitor.nextLine();
		return valorDouble;
	}

	public void iniciar() {
		int opcao;

		do {
			exibirMenu();
			opcao = lerInteiro("Opcao: ");

			executarOpcao(opcao);

			if (opcao != 0) {
				System.out.println();
			}

		} while (opcao != 0);

		leitor.close();
	}

	private void exibirMenu() {
		System.out.println("\n=== Menu do Hotel ===");
		System.out.println("1 - Cadastrar reserva: ");
		System.out.println("2 - Exibir reservas: ");
		System.out.println("3 - Remover reserva: ");
		System.out.println("4 - Buscar reserva por hóspede: ");
		System.out.println("5 - Valor do patrimônio do hotel: ");
		System.out.println("0 - Sair");
	}

	private void executarOpcao(int opcao) {
		switch (opcao) {
		case 1:
			opcaoAnotarNovaReserva();
			break;

		case 2:
			opcaoExibirReservas();
			break;

		case 3:
			opcaoApagarReserva();
			break;

		case 4:
			opcaoBuscarReservaPorCodigo();
			break;

		case 5:
			opcaoPatrimonioHotel();
			break;

		case 0:
			System.out.println("Encerrando sistema...");
			break;

		default:
			System.out.println("Opção inválida!! ");
		}

	}

	public void opcaoApagarReserva() { // OK
		if (controlador.estaVazio()) {
			System.out.println("\nNenhum hóspede cadastrado!!");
			return;
		}

		String codigo = lerTexto("Digite o código para remover: ");

		if (controlador.removerReservaPorCodigo(codigo)) {
			System.out.println("Reserva removida com sucesso.");
		} else {
			System.out.println("Reserva não encontrada.");
		}
	}

	public void opcaoExibirReservas() { // OK
		if (controlador.estaVazio()) {
			System.out.println("\nNenhum hóspede cadastrado!!");
			return;
		}

		System.out.print("\n=== Caderno de Reservas do Hotel " + sistema.getNomeHotel() + " ===\n"
				+ "\nQuantidade de Reservas: " + controlador.contarReservas() + controlador.exibirRelatorioDeReservas());

	}

	public void opcaoBuscarReservaPorCodigo() { // Ok
		if (controlador.estaVazio()) {
			System.out.println("\nNenhum hóspede cadastrado!!");
			return;
		}

		System.out.println("\n-- Buscar reserva Por Codigo --");

		String codigo = lerTexto("Digite o código da reserva que deseja buscar: ");
		Reserva reserva = controlador.buscarReservasPorCodigo(codigo);

		if (reserva != null) {
			System.out.println(reserva);
		} else {
			System.out.println("Reserva não encontrada.");
		}
	}

	public void opcaoPatrimonioHotel() {
		System.out.println("\n-- Valor do patrimônio do Hotel --");
		System.out.println("R$ " + controlador.calcularPatrimonioTotal());
	}

	public void opcaoAnotarNovaReserva() {

		
		System.out.println("======= Cadastrando nova reserava =======");

		String codigo = lerTexto("Codigo: ");

		if (controlador.estaCheio(sistema.getCapacidadeMaxima())) {
			System.out.println( "Nao foi possivel cadastrar: capacidade maxima de reservas atingida.");
		}

		Quarto tipoQuarto = tipoQuarto();
		String numeroQuarto = lerTexto("\nNumero do quarto: ");
		String nomeHospede = lerTexto("\nNome Hospede: ");
		FormaDePagamento formaPagamento = formaPagamento();
		DiaSemana diaSemana = diaEntrada();
		int quantidadeDias = lerInteiro("\nQuantidade de dias: ");
		double valorDiaria = lerDouble("\nValor diaria: ");

		boolean resultado = controlador.cadastrarReserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaPagamento, diaSemana, quantidadeDias, valorDiaria);
		if(resultado) {
			System.out.println("\nReserva cadastrada com sucesso!");;
		} else {
		System.out.println("\nNao foi possivel cadastrar reserva!!!");;
			}
		}

	public Quarto tipoQuarto() {
		
		System.out.println("\nTipo do Quarto");
	    System.out.println("1 - Luxo");
	    System.out.println("2 - Comum");
	        
	    Quarto tipoQuarto = null;
	    
	    while (tipoQuarto == null) {
	    int opcao = lerInteiro("\nQuarto: ");
	    
		switch (opcao) {
		case 1:
			tipoQuarto = new QuartoLuxo("Quarto Luxo");
			break;

		case 2:
			tipoQuarto = new QuartoComum("Quarto Comum");
			break;

		default:
			System.out.println("tipo invalido, tente novamente");
			}
	    }
		return tipoQuarto;
	}

	public FormaDePagamento formaPagamento() {
		System.out.println("\nForma de pagamento");
	    System.out.println("1 - Cartao");
	    System.out.println("2 - Boleto");
	    System.out.println("3 - Pix");
	    
	    FormaDePagamento formaPagamento = null;
	    while (formaPagamento == null) {
	 	int opcao = lerInteiro("\nPagamento: ");
		switch (opcao) {
		case 1:

			formaPagamento = new PagamentoViaCartao();
			break;

		case 2:
			formaPagamento = new PagamentoViaBoleto();
			break;

		case 3:
			formaPagamento = new PagamentoViaPix();
			break;

		default:
			System.out.println("tipo invalido, tente novamente");
			}
	    }
	 
		return formaPagamento;

	}

	public DiaSemana diaEntrada() {
		
		System.out.println("\nDia da Entrada");

	    System.out.println("1 - Domingo");
	    System.out.println("2 - Segunda");
	    System.out.println("3 - Terca");
	    System.out.println("4 - Quarta");
	    System.out.println("5 - Quinta");
	    System.out.println("6 - Sexta");
	    System.out.println("7 - Sabado");
	
	    
	    DiaSemana diaEntrada = null;
	    
	
		int opcao = lerInteiro("Pagamento: ");

		while (diaEntrada == null) {
		switch (opcao) {
		case 1:
			diaEntrada = DiaSemana.DOMINGO;
			break;

		case 2:
			diaEntrada = DiaSemana.SEGUNDA;
			break;

		case 3:
			diaEntrada = DiaSemana.TERCA;
			break;

		case 4:
			diaEntrada = DiaSemana.QUARTA;
			break;

		case 5:
			diaEntrada = DiaSemana.QUINTA;
			break;

		case 6:
			diaEntrada = DiaSemana.SEXTA;
			break;

		case 7:
			diaEntrada = DiaSemana.SABADO;
			break;

		default:
			System.out.println("Dia invalido, tente novamente");
			}
		}
		return diaEntrada;
	}

}