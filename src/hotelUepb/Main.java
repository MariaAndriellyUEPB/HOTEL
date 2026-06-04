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

	public static void main(String[] args) {	

		int valor;
		
		int opcao;

		

		
		leitor.close();
	}

}
