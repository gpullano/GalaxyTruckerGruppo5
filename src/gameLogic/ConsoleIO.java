package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import eccezioni.InputNonValidoException;

public class ConsoleIO {
	//stringhe costanti
	private static final String INPUT_NON_VALIDO = "Input non valido. Per favore, inserisci un numero.";
	// attributi
	private final Scanner sc;
	private boolean inputValido;
	
	// costruttore
	public ConsoleIO() {
		this.sc = new Scanner(System.in);
	}
	
	//metodi
	public void chiudiScanner() {
		sc.close();	
	}
	
	
	
	
	//------- SETUP PARTITA
	
	public LivelloPartita chiediLivelloGioco() {
	    int scelta = -1;
	    LivelloPartita livelloScelto = null;
	    inputValido = false;

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
	            // TODO - cambiare quest'eccezione e metterne una controllata
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        } catch (InputNonValidoException e) {
	            // Se l'input è un intero ma non valido
	            System.err.println(e.getMessage());
	        }
	    }

	    return livelloScelto;
	}

	public int chiediNumGiocatori() {
	    int numGiocatori = 0;
	    inputValido = false;
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
	            System.err.println(INPUT_NON_VALIDO);
	        } 
	    }
	    return numGiocatori;
	}

	//TODO - verifica il corretto funzionamento di questo metodo per
	//colori diversi da quelli esatti. Ad es: "roSSo" anziché "ROSSO".
	public Colore[] chiediColoreGiocatori(int numGiocatori) {
		Colore coloreGiocatori[] = new Colore[numGiocatori];
		List<Colore> coloriSceltiTemp = new ArrayList<>();
		System.out.println("SCELTA COLORE GIOCATORI: ");
		System.out.println("G/g -> GIALLO");
		System.out.println("B/b -> BLU");
		System.out.println("V/v -> VERDE");
		System.out.println("R/r -> ROSSO");
		
		for (int i = 0; i < numGiocatori; i++) {
            inputValido = false;

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
	
	//---- FASI DI GIOCO 
	
	
	//Fase di assemblaggio
	
	public void inizioAssemblaggio() {
		System.out.println("-----FASE DI ASSEMBLAGGIO DELLE NAVI-----");
	}
	
	public AzioneAssemblaggio chiediAzioneAssemblaggio(Colore colore, boolean haAgganciatoComponente, boolean haPrenotatoComponente, boolean esistonoTessereScoperte) {
		AzioneAssemblaggio azioneScelta = null;
		//lista di scelte che si aggiorna in base alle scelte disponibili
		List<Integer> scelteDisponibili = new ArrayList<>(Arrays.asList(1, 2));
		inputValido = false;
		int scelta = 0;
		
		do {
			System.out.println("Giocatore " + colore + "Quale azione vuoi compiere? - PREMI:");
			System.out.println("1 - PESCARE UNA TESSERA");
	        System.out.println("2 - PRENOTARE UNA TESSERA");
	        if(haAgganciatoComponente) {
	        	System.out.println("3 - TERMINARE ASSEMBLAGGIO");
	        	System.out.println("4 - GUARDARE MAZZI DI CARTE");
	        	scelteDisponibili.addAll(Arrays.asList(3, 4));
	        } else if(haPrenotatoComponente) {
	        	System.out.println("5 - PRENDI TESSERA PRENOTATA");
	        	scelteDisponibili.add(5);
	        } else if(esistonoTessereScoperte) {
	        	System.out.println("6 - PRENDI TESSERA SCOPERTA");
	        	scelteDisponibili.add(6);
	        }
	        System.out.print("La tua scelta: ");
	        
	        try {
	            scelta = Integer.parseInt(sc.nextLine());
	            
	            //verifico se la scelta inserita dall'utente è tra le opzioni stampate
	            for(Integer opzione : scelteDisponibili) {
	            	if(scelta == opzione) {
	            		azioneScelta = AzioneAssemblaggio.fromNumero(scelta);
	            		inputValido = true;
	            	}
	            }
	            throw new InputNonValidoException("Numero non valido.");
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        } catch (InputNonValidoException e) {
	            System.err.println(e.getMessage());
	        }
		}while(!inputValido);
		
		return azioneScelta;
	}
	
	

}
