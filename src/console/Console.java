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

public class Console {
	private Scanner leitor = new Scanner(System.in);
	private ControladorSistemaHotel controlador = new ControladorSistemaHotel();

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
		
		int capacidadeMaxima = lerInteiro("Qual a capacidade maxima de hóspedes? ");

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
	
	public String cadastrarReserva() {
		String codigo = lerTexto("Codigo: ");
		Quarto tipoQuarto = tipoQuarto();
		String numeroQuarto = lerTexto("Numero do quarto: ");
		String nomeHospede = lerTexto("Nome Hospede: ");
		int quantidadeDias = lerInteiro("Quantidade de dias");
		double valorDiaria = lerDouble("Valor diaria");
	}
	
	
	public Quarto tipoQuarto() {
		System.out.println("Tipo do Quarto");
	    System.out.println("1 - Luxo");
	    System.out.println("2 - Standard");
	    
	    int opcao = lerInteiro("Quarto: ");
	    
	    Quarto tipoQuarto = null;
	    
	    switch (opcao) {
		case 1: 
			tipoQuarto = new QuartoLuxo("Quarto Luxo");
			break;
			
		case 2:
			tipoQuarto = new QuartoComum("Quarto Standard");
			break;	
			
		default:
			System.out.println("tipo invalido");
		}
		return tipoQuarto;
	}
	
	public FormaDePagamento formaPagamento() {
		System.out.println("Tipo do Quarto");
	    System.out.println("1 - Cartao");
	    System.out.println("2 - Boleto");
	    System.out.println("3 - Pix");
	    int opcao = lerInteiro("Pagamento: ");
	    
	    FormaDePagamento formaPagamento = null;
	    switch (opcao) {
		case 1: 
			formaPagamento = new PagamentoViaCartao();
			break;
		
		case 2:
			formaPagamento = new PagamentoViaBoleto();
			break;
		
		case 3:
			formaPagamento = new PagamentoViaPix();
			
		default:
			System.out.println("tipo invalido");
		}
		return formaPagamento;
	    
	}
	
	
}
// REUTILIZEM COM SABEDORIAAAAAAAAAAAAA
/*
 * static int capacidadeMaxima;
 * 
 * static String lerTexto(String mensagem) { System.out.print(mensagem); return
 * leitor.nextLine(); }
 * 
 * static int lerInteiro(String mensagem) { System.out.print(mensagem); int
 * valor = leitor.nextInt(); leitor.nextLine(); return valor; }
 * 
 * static double lerDouble(String mensagem) { System.out.print(mensagem); double
 * valorDouble = leitor.nextDouble(); leitor.nextLine(); return valorDouble; }
 * 
 * static void opcaoAnotarNovaReserva() {
 * 
 * if(meuHotel.estaCheio(capacidadeMaxima)){ System.out.
 * println("\nSem quartos disponíveis, impossível cadastrar novo hóspede.");
 * return; } System.out.println("\n-- Cadastros --"); String codigo =
 * lerTexto("Digite o código da reserva: "); String tipoQuarto =
 * lerTexto("Digite o tipo do quarto: "); String numeroQuarto =
 * lerTexto("Digite o numero do quarto: "); String nomeHospede =
 * lerTexto("Digite o nome do hóspede: "); String formaDePagamento =
 * lerTexto("Digite a forma de pagamento: "); int quantidadeDias =
 * lerInteiro("Digite a quantidade de dias: ");; double valorDiaria =
 * lerDouble("Digite o valor da diária: "); Reserva novaReserva = new
 * Reserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaDePagamento,
 * quantidadeDias, valorDiaria); meuHotel.cadastrarReserva(novaReserva);
 * System.out.println("\nPronto! Reserva agendada com sucesso!!"); }
 * 
 * static void opcaoVerLivroDeRegistro() { if(meuHotel.estaVazio()){
 * System.out.println("\nNenhum hóspede cadastrado!!"); return; }
 * 
 * System.out.print("\n=== Caderno de Reservas do Hotel " + meuHotel.nomeHotel
 * +" ==="); meuHotel.exibirRelatorioDeReservas(); }
 * 
 * static void opcaoApagarReserva() { if(meuHotel.estaVazio()){
 * System.out.println("\nNenhum hóspede cadastrado!!"); return; }
 * 
 * String codigo = lerTexto("Digite o código para remover: ");
 * 
 * if(meuHotel.removerReservaPorCodigo(codigo)) {
 * System.out.println("Reserva removida com sucesso.");
 * 
 * 
 * } else {
 * 
 * System.out.println("Reserva não encontrada."); } }
 * 
 * 
 * static void opcaoBuscarReservaPorHospede() { if(meuHotel.estaVazio()){
 * System.out.println("\nNenhum hóspede cadastrado!!"); return; }
 * 
 * System.out.println("\n-- Buscar reserva por Hóspede --");
 * 
 * String nomeHospede = lerTexto("Digite o nome do hóspede: ");
 * meuHotel.buscarReservasPorHospede(nomeHospede);
 * 
 * }
 * 
 * static void opcaoPatrimonioHotel() {
 * System.out.println("\n-- Valor do patrimônio do Hotel --");
 * System.out.println("R$ " + meuHotel.calcularPatrimonioTotal()); }
 * 
 * static void mostrarMenu() { System.out.println("\n=== Menu do Hotel " +
 * meuHotel.nomeHotel + " ==="); System.out.println("1 - Cadastrar reserva: ");
 * System.out.println("2 - Exibir reservas: ");
 * System.out.println("3 - Remover reserva: ");
 * System.out.println("4 - Buscar reserva por hóspede: ");
 * System.out.println("5 - Valor do patrimônio do hotel: ");
 * System.out.println("0 - Sair"); System.out.print("Escolha uma opcao: "); }
 * 
 * public static void main(String[] args) {
 * 
 * System.out.println("Bem-vindo ao Sistema de Reservas do Hotel!"); String
 * nomeHotel = lerTexto("Nome do hotel: ");
 * 
 * 
 * 
 * meuHotel = new SistemaHotel(nomeHotel, capacidadeMaxima);
 * 
 * int opcao;
 * 
 * do { mostrarMenu(); opcao = leitor.nextInt(); leitor.nextLine();
 * 
 * switch(opcao) { case 1: opcaoAnotarNovaReserva(); break;
 * 
 * case 2: opcaoVerLivroDeRegistro(); break;
 * 
 * case 3: opcaoApagarReserva(); break;
 * 
 * case 4: opcaoBuscarReservaPorHospede(); break;
 * 
 * case 5: opcaoPatrimonioHotel(); break;
 * 
 * case 0: System.out.println("Encerrando sistema..."); break;
 * 
 * default: System.out.println("Opção inválida!! "); }
 * 
 * } while (opcao != 0);
 * 
 * leitor.close(); }
 */
