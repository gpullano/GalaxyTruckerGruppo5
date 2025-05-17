package gameLogic;

import java.util.Scanner;

public class ConsoleIO {
	private final Scanner sc;
	
	public ConsoleIO() {
		this.sc = new Scanner(System.in);
	}
	
	public LivelloPartita chiediLivelloGioco() {
	    int scelta = -1;
	    LivelloPartita livelloScelto = null;
	    boolean inputValido = false;

	    while (!inputValido) {
	        System.out.println("--- MODALITA' - PREMI: ---");
	        System.out.println("1 - LIVELLO 1");
	        System.out.println("2 - LIVELLO 2");
	        System.out.println("3 - LIVELLO 3");
	        System.out.println("4 - TRASVOLATA INTERGALATTICA"); // Assumiamo che l'utente digiti 4 per la trasvolata
	        System.out.print("La tua scelta: ");

	        try {
	            scelta = Integer.parseInt(sc.nextLine());

	            // Tento di convertire l'intero letto in un valore enum
	            livelloScelto = LivelloPartita.fromNumero(scelta);
	            inputValido = true;

	        } catch (NumberFormatException e) {
	            // Se l'input non è un intero valido
	            System.out.println("Input non valido. Per favore, inserisci un numero.");
	        } catch (IllegalArgumentException e) {
	            // Se l'input è un intero ma non valido
	            System.out.println(e.getMessage());
	        }
	    }

	    return livelloScelto;
	}

	public void chiudiScanner() {
		sc.close();	
	}
	
	
	
	

}
