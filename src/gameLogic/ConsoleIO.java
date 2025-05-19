package gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO {
	// attributi
	private final Scanner sc;
	
	// costruttore
	public ConsoleIO() {
		this.sc = new Scanner(System.in);
	}
	
	//metodi
	public void chiudiScanner() {
		sc.close();	
	}
	
	
	
	
	//-------
	
	public LivelloPartita chiediLivelloGioco() {
	    int scelta = -1;
	    LivelloPartita livelloScelto = null;
	    boolean inputValido = false;

	    while (!inputValido) {
	        System.out.println("--- MODALITA' - PREMI: ---");
	        System.out.println("1 - LIVELLO 1");
	        System.out.println("2 - LIVELLO 2");
	        System.out.println("3 - LIVELLO 3");
	        System.out.println("4 - TRASVOLATA INTERGALATTICA");
	        System.out.print("La tua scelta: ");

	        try {
	            scelta = Integer.parseInt(sc.nextLine());

	            // Tento di convertire l'intero letto in un valore enum
	            livelloScelto = LivelloPartita.fromNumero(scelta);
	            inputValido = true;

	        } catch (NumberFormatException e) {
	            System.err.println("Input non valido. Per favore, inserisci un numero.");
	        } catch (IllegalArgumentException e) {
	            // Se l'input è un intero ma non valido
	            System.err.println(e.getMessage());
	        }
	    }

	    return livelloScelto;
	}

	public int chiediNumGiocatori() {
	    int numGiocatori = 0;
	    boolean inputValido = false;
	    System.out.println("--- SCELTA GIOCATORI: ---");
	    while (!inputValido) {
	        System.out.println("In quanti siete, camionisti spaziali?: ");
	        try {
	            numGiocatori = Integer.parseInt(sc.nextLine());
	            
	            if (numGiocatori >= 2 && numGiocatori <= 4) {
	                inputValido = true; 
	            } else {
	                System.err.println("Numero di giocatori non valido. Per favore, inserisci un numero tra 2 e 4.");
	            }
	        } catch (NumberFormatException e) {
	            System.err.println("Input non valido. Per favore, inserisci un numero.");
	        } 
	    }
	    return numGiocatori;
	}

	public Colore[] chiediColoreGiocatori(int numGiocatori) {
		Colore coloreGiocatori[] = new Colore[numGiocatori];
		List<Colore> coloriSceltiTemp = new ArrayList<>();
		System.out.println("SCELTA COLORE GIOCATORI: ");
		System.out.println("G/g -> GIALLO");
		System.out.println("B/b -> BLU");
		System.out.println("V/v -> VERDE");
		System.out.println("R/r -> ROSSO");
		
		for (int i = 0; i < numGiocatori; i++) {
            boolean inputValido = false;

            while (!inputValido) { 
                System.out.print("Giocatore " + (i + 1) + ", che colore vuoi scegliere?: ");
                String inputUtente = sc.nextLine();
                Colore coloreScelto = Colore.fromStringSemplice(inputUtente);

                if (coloreScelto == null) {
                    System.err.println("Colore non riconosciuto. Per favore, scrivi uno dei nomi esatti (es: ROSSO).");
                } else if (coloriSceltiTemp.contains(coloreScelto)) {
                    System.err.println("Questo colore è già stato scelto da un altro giocatore. Per favore, scegline uno diverso.");
                } else {
                    coloreGiocatori[i] = coloreScelto;     
                    coloriSceltiTemp.add(coloreScelto);    
                    inputValido = true;       
                    System.out.println("Hai scelto: " + coloreScelto.name()); 
                }
            }
        }

        return coloreGiocatori;
    }
	
	
	
	

}
