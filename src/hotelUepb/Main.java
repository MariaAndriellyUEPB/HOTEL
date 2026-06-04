package hotelUepb;

import java.util.Scanner;

public class Main {

	static Scanner leitor = new Scanner (System.in);

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

	public static void main(String[] args) {	

		int valor;
		
		int opcao;

		
		System.out.println("Digite a opção: ");

			
		Scanner leitor = new Scanner (System.in);

		
		leitor.close();
	}

}
