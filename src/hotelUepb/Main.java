package hotelUepb;

import java.util.Scanner;

public class Main {

	static Scanner leitor = new Scanner (System.in);
	
	static Hotel meuHotel;

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
    	String codigo = lerTexto("Digite o código do hotel: ");
    	String tipoQuarto = lerTexto("Digite o tipo do quarto: ");
    	String numeroQuarto = lerTexto("Digite o numero do quarto: ");
    	String nomeHospede = lerTexto("Digite o nome do hóspede: ");
    	String formaDePagamento = lerTexto("Digite a forma de pagamento: ");
    	int quantidadeDias = lerInteiro("Digite a quantidade de dias: ");;
    	double valorDiaria = lerDouble("Digite o valor da diária: ");
    	Reserva novaReserva = new Reserva(codigo, tipoQuarto, numeroQuarto, nomeHospede, formaDePagamento, quantidadeDias, valorDiaria);
    	meuHotel.cadastrarReserva(novaReserva);
    }
    
	static void opcaoVerLivroDeRegistro() {
		System.out.print("\n=== Caderno de Registros ===");
		meuHotel.exibirRelatorioDeReservas(); 	
	}
	
	static void opcaoApagarReserva() {
		String codigo = lerTexto("Digite o codigo do para remover: ");
		if(meuHotel.removerReservaPorCodigo(codigo)) {
			System.out.println("Reserva removida com sucesso.");
		} else {

            System.out.println("Reserva não encontrada.");
        }
	}
	
	static void opcaoProcurarPorHospesde() {
		System.out.println("\n-- Procurar por Convidado --");
	    String nomeHospede = lerTexto("Digite o nome do hóspede: ");
	    meuHotel.buscarReservasPorHospede(nomeHospede);
    }
	
	static void mostrarMenu() {
		System.out.println("\n=== Menu do Hotel ===");
		System.out.println("1 - Cadastrar reserva: ");
		System.out.println("2 - Exibir reservas: ");
		System.out.println("3 - Remover reserva: ");
		System.out.println("4 - Buscar reserva por hóspede: ");
		System.out.println("0 - Sair");
		System.out.print("Escolha uma opcao: ");
	}

	public static void main(String[] args) {	

		System.out.println("Bem-vindo ao Sistema de Reservas do Hotel!");


		
		int opcao;

	
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
	
	            	opcaoProcurarPorHospesde();
	                break;
	
	            case 0:
	
	                System.out.println("Encerrando sistema...");
	                break;
	
	            default:
	
	                System.out.println("Opcao invalida.");
			}
			
		} while (opcao != 0);
		
		leitor.close();
	}

}
