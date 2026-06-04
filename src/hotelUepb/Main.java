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
    
    static void opcaoAnotarnovaReserva() {
    	
    }
    
	static void opcaoVerLivroDeRegistro() {
	    	
	}
	
	static void opcaoApagarReserva() {
		
	}
	
	static void opcaoProcurarPorHospesde() {
    	
    }
	
	static void mostrarMenu() {
		System.out.println("\n=== Menu do Hotel ===");
		System.out.println("1 - : ");
		System.out.println("2 - : ");
		System.out.println("3 - : ");
		System.out.println("4 - : ");
		System.out.println("0 - Sair: ");
		System.out.print("Escolha uma opcao: ");
	}

	public static void main(String[] args) {	

		int valor;
		
		int opcao;
		
		do {
			mostrarMenu();
			opcao = leitor.nextInt();
			leitor.nextLine();
			
			switch(opcao) {
				case 1:
					opcaoAnotarnovaReserva();
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
