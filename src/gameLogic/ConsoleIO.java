package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import carteAvventura.Carta;
import carteAvventura.Mazzetto;
import eccezioni.NumeroNonValidoException;
import tessere.Tessera;

public class ConsoleIO {
	//stringhe costanti
	private static final String INPUT_NON_VALIDO = "Input non valido. Per favore, inserisci un numero.";
	private static final String NUMERO_NON_VALIDO = "Numero non valido. Reinseriscilo";
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
	
	
	
	
	//------- SETUP PARTITA
	
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
	            // TODO - cambiare quest'eccezione e metterne una controllata
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        } catch (NumeroNonValidoException e) {
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
		boolean inputValido;
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
		List<Integer> scelteDisponibili = new ArrayList<>();
		
		
		boolean inputValido = false;
		int scelta = 0;
		
		while(!inputValido) {
			System.out.println("Giocatore " + colore + " quale azione vuoi compiere? - PREMI:");
			System.out.println("1 - PESCARE UNA TESSERA");
			scelteDisponibili.add(1);
	        if(haAgganciatoComponente) {
	        	System.out.println("2 - TERMINARE ASSEMBLAGGIO");
	        	System.out.println("3 - GUARDARE MAZZI DI CARTE");
	        	scelteDisponibili.addAll(Arrays.asList(2, 3));
	        } if(haPrenotatoComponente) {
	        	System.out.println("4 - PRENDI TESSERA PRENOTATA");
	        	scelteDisponibili.add(4);
	        } if(esistonoTessereScoperte) {
	        	System.out.println("5 - PRENDI TESSERA SCOPERTA");
	        	scelteDisponibili.add(5);
	        }
	        System.out.print("La tua scelta: ");
	        
	        try {
	            scelta = Integer.parseInt(sc.nextLine());
	            
	            //verifico se la scelta inserita dall'utente è tra le opzioni stampate
	            for(Integer opzione : scelteDisponibili) {
	            	if(scelta == opzione) {
	            		azioneScelta = AzioneAssemblaggio.fromNumero(scelta);
	            		inputValido = true;
	            		break;
	            	}
	            }
	            if(!inputValido)
	            	throw new NumeroNonValidoException(NUMERO_NON_VALIDO);
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        } catch (NumeroNonValidoException e) {
	            System.err.println(e.getMessage());
	        }
	        scelteDisponibili.clear();
		}
		
		//TODO - valutare se tenere la riga di codice seguente
		System.out.println("Hai scelto: " + scelta + ": " + azioneScelta.name());
		
		return azioneScelta;
	}
	
	public AzioneAssemblaggio chiediAzioneSulleTessere(Colore colore, boolean tesseraPrenotata, Tessera tesseraPescata, boolean spazioTesserePrenotatePieno) {
		AzioneAssemblaggio azioneScelta = null;
		boolean inputValido = false;
		int scelta = 0;
		List<Integer> scelteDisponibili = new ArrayList<>(Arrays.asList(1, 2));
		
		do {
			System.out.println("TESSERA PESCATA:\n" + tesseraPescata);
			System.out.println("Giocatore " + colore + " cosa vuoi fare con la tessera che hai in mano - PREMI:");
			System.out.println("1 - RUOTARLA (senso antiorario)");
	        System.out.println("2 - AGGANCIARLA");
	        
	        //Se ho preso la tessera dai miei due slot di tessere prenotate, non ha senso lasciare
	        //disponibili queste due opzioni seguenti.
	        //Questo controllo verifica che la tessera considerata non sia una tessera prenotata.
	        if(!tesseraPrenotata) {
	        	System.out.println("3 - RIMETTERLA SUL TAVOLO");
	        	scelteDisponibili.add(3);
	        } 
	        if(!spazioTesserePrenotatePieno) {
	        	System.out.println("4 - PRENOTARLA PER DOPO");
	        	scelteDisponibili.add(4);
	        }
	        System.out.print("La tua scelta: ");
	        
	        try {
	            scelta = Integer.parseInt(sc.nextLine());
	            
	            //verifico se la scelta inserita dall'utente è tra le opzioni stampate
	            for(Integer opzione : scelteDisponibili) {
	            	if(scelta == opzione) {
	            		azioneScelta = AzioneAssemblaggio.fromNumero(scelta + 5);
	            		inputValido = true;
	            		break;
	            	}
	            }
	            if(!inputValido)
	            	throw new NumeroNonValidoException(NUMERO_NON_VALIDO);
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        } catch (NumeroNonValidoException e) {
	            System.err.println(e.getMessage());
	        }
	        scelteDisponibili.clear();
		}while(!inputValido);
		
		//TODO - valutare se tenere la riga di codice seguente
		System.out.println("Hai scelto: " + scelta + ": " + azioneScelta.name());
		
		return azioneScelta;
	}
	
	public void ruotaTessera(Tessera tesseraPescata) {
		boolean ruotaAncora = true;
		String scelta;
		while(ruotaAncora) {
			tesseraPescata.ruota();
			System.out.println("Tessera ruotata:");
			System.out.println(tesseraPescata);
			System.out.println("Vuoi ruotarla ancora? premi si/no");
			try {
				scelta = sc.nextLine().trim();
				if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && 
					!scelta.equalsIgnoreCase("s") && !scelta.equalsIgnoreCase("n")) {
					throw new IllegalArgumentException("scelta non valida, reinseriscila.");
				}
				if(scelta.equalsIgnoreCase("no") || scelta.equalsIgnoreCase("n"))
					ruotaAncora = false;
			} catch (IllegalArgumentException e){
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	public void guardaMazzettoScelto(Mazzetto[] mazzettiDiCarte) {
		boolean inputValido = false;
		int scelta = 0;
		while(!inputValido)
		{
			System.out.println("QUALE MAZZETTO DI CARTE VUOI GUARDARE (da 1 a 3)?");
			try {
				 scelta = Integer.parseInt(sc.nextLine());
				 if(scelta < 1 || scelta > 3) {
					 throw new NumeroNonValidoException(NUMERO_NON_VALIDO);
				 }
				 inputValido = true;
			}catch (NumberFormatException e) {
		            System.err.println(INPUT_NON_VALIDO);
	        }catch (NumeroNonValidoException e) {
		            System.err.println(e.getMessage());
		    	}
		
		}
		for(Carta carta : mazzettiDiCarte[scelta].getCarte()) {
			System.out.println(carta);
		}
	}
	
	

}
