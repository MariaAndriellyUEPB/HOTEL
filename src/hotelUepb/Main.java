package hotelUepb;

import java.util.Scanner;

public class Main {

	static Scanner leitor = new Scanner (System.in);
	
	static Hotel meuHotel;
	
	static int capacidadeMaxima;

	static String lerTexto(String mensagem) {
		System.out.print(mensagem);
        return leitor.nextLine();
    }

    static int lerInteiro(String mensagem) {
    	System.out.print(mensagem);
        int valor = leitor.nextInt();
        leitor.nextLine();
        return valor;
    }
    
    static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        double valorDouble = leitor.nextDouble();
        leitor.nextLine();
        return valorDouble;
    }
    
    static void opcaoAnotarNovaReserva() {
    	
    	if(meuHotel.estaCheio(capacidadeMaxima)){
			System.out.println("\nSem quartos disponíveis, impossível cadastrar novo hóspede.");
			return;
    	}
    	
    	System.out.println("\n-- Cadastros --");
    	String codigo = lerTexto("Digite o código da reserva: ");
    	String tipoQuarto = lerTexto("Digite o tipo do quarto: ");
    	String numeroQuarto = lerTexto("Digite o numero do quarto: ");
    	String nomeHospede = lerTexto("Digite o nome do hóspede: ");
    	String formaDePagamento = lerTexto("Digite a forma de pagamento: ");
    	int quantidadeDias = lerInteiro("Digite a quantidade de dias: ");;
    	double valorDiaria = lerDouble("Digite o valor da diária: ");
    	Reserva novaReserva = new Reserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaDePagamento, quantidadeDias, valorDiaria);
    	meuHotel.cadastrarReserva(novaReserva);
    	System.out.println("\nPronto! Reserva agendada com sucesso!!");
    }
    
	static void opcaoVerLivroDeRegistro() {
		if(meuHotel.estaVazio()){
			System.out.println("\nNenhum hóspede cadastrado!!");
			return;
		}
		System.out.print("\n=== Caderno de Reservas ===");
		meuHotel.exibirRelatorioDeReservas(); 	
	}
	
	static void opcaoApagarReserva() {

		if(meuHotel.estaVazio()){
			System.out.println("\nNenhum hóspede cadastrado!!");
			return;
		}
		
		String codigo = lerTexto("Digite o código para remover: ");

		if(meuHotel.removerReservaPorCodigo(codigo)) {
			System.out.println("\nReserva removida com sucesso!!");
		} else {
            System.out.println("\nReserva não encontrada!!");
        }
	}
	
	static void opcaoBuscarPorHospesde() {
		if(meuHotel.estaVazio()){
			System.out.println("\nNenhum hóspede cadastrado!!!");
			return;
		}
		System.out.println("\n-- Procurar por Hóspede --");
	    String nomeHospede = lerTexto("Digite o nome do hóspede: ");
	    meuHotel.buscarReservasPorHospede(nomeHospede);
	    
    }
	
	static void opcaoPatrimonioHotel() {
		System.out.println("\n-- Valor do patrimônio do Hotel --");
	    System.out.println("R$ " + meuHotel.calcularPatrimonioTotal());
    }
	
	static void mostrarMenu() {
		System.out.println("\n=== Menu do Hotel ===");
		System.out.println("1 - Cadastrar reserva: ");
		System.out.println("2 - Exibir reservas: ");
		System.out.println("3 - Remover reserva: ");
		System.out.println("4 - Buscar reserva por hóspede: ");
		System.out.println("5 - Valor do patrimônio do hotel: ");
		System.out.println("0 - Sair");
		System.out.print("Escolha uma opcao: ");
	}

	public static void main(String[] args) {	
		
		int opcao;


		System.out.println("Bem-vindo ao Sistema de Reservas do Hotel!");
		String nomeHotel = lerTexto("Nome do hotel: ");
		
		capacidadeMaxima = lerInteiro("Qual a capacidade maxima de hóspedes? ");

		System.out.println("Digite a opção: ");


		meuHotel = new Hotel(nomeHotel, capacidadeMaxima);


		do {
			mostrarMenu();
			opcao = leitor.nextInt();
			leitor.nextLine();

			switch(opcao) {
				case 1:
					opcaoAnotarNovaReserva();
	                break;
				case 2:
	
					opcaoVerLivroDeRegistro();
	                break;
	
	            case 3:
	
	            	opcaoApagarReserva();
	                break;
	
	            case 4:
	
	            	opcaoBuscarPorHospesde();
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
			
		} while (opcao != 0);
		
		leitor.close();
	}

}
