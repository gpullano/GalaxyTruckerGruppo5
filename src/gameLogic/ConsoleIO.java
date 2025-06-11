package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import carteAvventura.Carta;
import carteAvventura.Mazzetto;
import carteAvventura.Pianeta;
import collezionabili.Merci;
import eccezioni.NumeroNonValidoException;
import plance.Casella;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;
import tessere.Cabina;
import tessere.Connettore;
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
	
	
	
	
	
	
	
	//------------------------------------------------------------------
	// STAMPA NAVE E PLANCIA DI VOLO
	/**
	 * Stampa una rappresentazione grafica completa della plancia di una nave.
	 * Semplificata grazie alla lunghezza fissa dei nomi delle tessere.
	 */
	public void stampaNave(PlanceNaveLivello1 planceNave) {
	    // Costanti per le celle vuote, per una maggiore leggibilità
	    final String RIGA_VUOTA_1_E_3 = "             "; // 13 spazi
	    final String RIGA_VUOTA_2      = "      .      "; // 13 caratteri con un punto al centro
	    
	    // Stampo l'intestazione delle COLONNE (da 4 a 10)
	    StringBuilder intestazioneColonne = new StringBuilder();
	    // Aggiungi uno spazio iniziale per allineare con i numeri delle righe che aggiungeremo dopo
	    intestazioneColonne.append("    "); // Spazio per l'etichetta della riga (es. "5: ")

	    for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
	        int numeroColonna = c + 4;
	        System.out.print("       " + numeroColonna + "        ");
	    }
	    System.out.println(); // Riga vuota per separazione

	    // Per ogni riga della griglia...
	    for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
	    	int numeroRiga = r + 5;
	        StringBuilder rigaTesto1 = new StringBuilder(); // Connettori NORD
	        StringBuilder rigaTesto2 = new StringBuilder(); // Lati OVEST - NOME - EST
	        StringBuilder rigaTesto3 = new StringBuilder(); // Connettori SUD
	        
	        // ...itera su ogni colonna...
	        for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
	        	
	            Casella casella = planceNave.getCaselle()[r][c];
	            
	            if (casella.getTessera() != null) {
	                Tessera t = casella.getTessera();
	                
	                // Formatta i connettori per avere una lunghezza fissa (es. " S", "D ", "--")
	                String sup = t.getLatoSup().toString();
	                String inf = t.getLatoDown().toString();
	                String sx  = t.getLatoSx().toString();
	                String dx  = t.getLatoDx().toString();

	                // Costruisci le tre righe della tessera
	                rigaTesto1.append("      ").append(sup).append("     ");
	                rigaTesto2.append(" ").append(sx).append(t.getNomeBreve()).append(dx).append(" ");
	                rigaTesto3.append("      ").append(inf).append("     ");

	            } else if (casella.isUtilizzabile()) {
	                // Se la casella è vuota ma utilizzabile, usa il placeholder
	                rigaTesto1.append(RIGA_VUOTA_1_E_3);
	                rigaTesto2.append(RIGA_VUOTA_2);
	                rigaTesto3.append(RIGA_VUOTA_1_E_3);
	            } else {
	                // Se la casella non è nemmeno utilizzabile, stampiamo solo spazi
	                rigaTesto1.append(RIGA_VUOTA_1_E_3);
	                rigaTesto2.append(RIGA_VUOTA_1_E_3);
	                rigaTesto3.append(RIGA_VUOTA_1_E_3);
	            }
	            
	            // Aggiungi un separatore tra le celle per distanziarle
	            rigaTesto1.append("  ");
	            rigaTesto2.append("  ");
	            rigaTesto3.append("  ");
	        }

	        // Stampa la prima riga di testo (connettori NORD)
	        System.out.println("    " + rigaTesto1); // Spazio vuoto per allineare con il numero di riga
	        // Stampa la seconda riga di testo (corpo della tessera) con il numero di riga
	        System.out.println(numeroRiga + ":  " + rigaTesto2);
	        // Stampa la terza riga di testo (connettori SUD)
	        System.out.println("    " + rigaTesto3);
	        System.out.println(); // Riga vuota per separazione verticale tra le righe della griglia
	    }
	}
	
	//------------------------------------------------------------------

/**
 * Stampa a schermo la plancia di volo lineare.
 * La plancia mostra le caselle del percorso e la posizione attuale di ogni giocatore.
 *
 * @param planceVolo L'oggetto PlanceVolo da stampare.
 */
public void stampaVolo(PlanceVolo planceVolo) {
    int lunghezzaPercorso = planceVolo.getLunghezzaPercorso();
    List<PosizioneGiocatore> posizioni = planceVolo.getPosizioneGiocatori();

    // 1. Crea una rappresentazione testuale della plancia vuota.
    // Usiamo String[] per poterci scrivere sopra le iniziali dei giocatori.
    String[] visualizzazionePlancia = new String[lunghezzaPercorso];
    for (int i = 0; i < lunghezzaPercorso; i++) {
        // Inizializza ogni casella con il suo numero (es. "[ 1 ]", "[ 2 ]", ...)
        // Il +1 serve perché gli indici dell'array sono 0-based, ma noi vogliamo mostrare 1-18.
        visualizzazionePlancia[i] = String.format("[%2d]", i + 1);
    }
    
    // 2. "Disegna" i giocatori sulla rappresentazione testuale.
    for (PosizioneGiocatore posGiocatore : posizioni) {
        // Ottieni la posizione (1-18) e il colore del giocatore
        int posizioneAttuale = posGiocatore.getPosizione();
        char inizialeColore = posGiocatore.getColore().toString().charAt(0);

        // La posizione 1 corrisponde all'indice 0 dell'array, quindi dobbiamo fare -1.
        int indiceArray = posizioneAttuale;

        // Assicurati che la posizione sia valida per non causare errori
        if (indiceArray >= 0 && indiceArray < lunghezzaPercorso) {
            // Se la casella è ancora il suo numero (es. "[ 5 ]"), la svuotiamo.
            if (visualizzazionePlancia[indiceArray].startsWith("[")) {
                visualizzazionePlancia[indiceArray] = " ";
            }
            // Aggiungi l'iniziale del giocatore a quella casella.
            // Se più giocatori sono sulla stessa casella, le loro iniziali appariranno una dopo l'altra.
            visualizzazionePlancia[indiceArray] += inizialeColore + " ";
        }
    }

    // 3. Stampa la plancia finale.
    System.out.println("\n--- PLANCIA DI VOLO ---");
    StringBuilder lineaSuperiore = new StringBuilder();
    StringBuilder lineaMezzo = new StringBuilder();
    StringBuilder lineaInferiore = new StringBuilder();

    for (String casella : visualizzazionePlancia) {
        lineaSuperiore.append("+------");
        // Formatta la stringa della casella per essere lunga 6 caratteri, centrata
        lineaMezzo.append(String.format("|%-6s", casella));
        lineaInferiore.append("+------");
    }
    lineaSuperiore.append("+");
    lineaMezzo.append("|");
    lineaInferiore.append("+");

    System.out.println(lineaSuperiore);
    System.out.println(lineaMezzo);
    System.out.println(lineaInferiore);
    
    // Stampa anche le informazioni dettagliate dei giri
    System.out.println("Dettaglio Giri:");
    for (PosizioneGiocatore posGiocatore : posizioni) {
        System.out.println("  - Giocatore " + posGiocatore.getColore() + ": Giro " + posGiocatore.getGiro());
    }
    System.out.println();
}
	
	
	
	
	//------------------------------------------------------------------
	//------- SETUP PARTITA
	
	public LivelloPartita chiediLivelloGioco() {
	    int scelta = -1;
	    LivelloPartita livelloScelto = null;
	    boolean inputValido = false;

	    while (!inputValido) {
	    	System.out.println("--------------------------------------------------------------------");
	    	System.out.println("..........................GALAXY TRUCKER............................");
	    	System.out.println("--------------------------------------------------------------------\n");
	        System.out.println("\n--- SCEGLI LA MODALITA' - PREMI: ---");
	        System.out.println("\n\nNOTA BENE: ATTUALMENTE, A QUESTO STADIO DI SVILUPPO L'UNICA MODALITA'\n" +
	        				"DISPONIBILE E' IL LIVELLO 1.\n\n");
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
	    System.out.println("\n--- SCELTA GIOCATORI: ---");
	    while (!inputValido) {
	        System.out.println("In quanti siete, camionisti spaziali (da 2 a 4)?: ");
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
		System.out.println("\nSCELTA COLORE GIOCATORI: ");
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
	
	
	
	
	
	
	//------------------------------------------------------------------
	//---- FASI DI GIOCO 
	
	
	
	
	
	//-------------
	//Fase di assemblaggio
	//-------------
	
	public void stampaSetupAssemblaggio(Colore coloreGiocatore, PlanceNaveLivello1 planceNave, List<Tessera> tesserePrenotate,
			List<Tessera> tessereScoperte) {
		System.out.println("\nSETUP DEL GIOCATORE " + coloreGiocatore);
		stampaNave(planceNave);
		System.out.println("\nTESSERE PRENOTATE:\n");
		stampaTessere(tesserePrenotate);
		System.out.println("\nTESSERE SCOPERTE:\n");
		stampaTessere(tessereScoperte);
		
	}
	
	public void inizioAssemblaggio() {
		System.out.println("-----FASE DI ASSEMBLAGGIO DELLE NAVI-----");
	}
	
	
	public AzioneAssemblaggio chiediAzioneAssemblaggio(Colore colore, boolean haAgganciatoComponente, 
			boolean haPrenotatoComponente, boolean esistonoTessereScoperte, boolean esistonoTessereMucchio) {
		AzioneAssemblaggio azioneScelta = null;
		//lista di scelte che si aggiorna in base alle scelte disponibili
		List<Integer> scelteDisponibili = new ArrayList<>();
		
		
		boolean inputValido = false;
		int scelta = 0;
		
		while(!inputValido) {
			System.out.println("\nGiocatore " + colore + " quale azione vuoi compiere? - PREMI:");
			if(esistonoTessereMucchio) {
				System.out.println("1 - PESCARE UNA TESSERA");
				scelteDisponibili.add(1);
			}
			if(!esistonoTessereMucchio) {
				System.out.println("TESSERE FINITE, NON puoi pescarne altre");
			}
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
			System.out.println("\nTESSERA PESCATA:\n\n");
			StringBuilder rigaTesto1 = new StringBuilder(); // Connettori NORD
	        StringBuilder rigaTesto2 = new StringBuilder(); // Lati OVEST - NOME - EST
	        StringBuilder rigaTesto3 = new StringBuilder(); // Connettori SUD

	        String sup = tesseraPescata.getLatoSup().toString();
            String inf = tesseraPescata.getLatoDown().toString();
            String sx  = tesseraPescata.getLatoSx().toString();
            String dx  = tesseraPescata.getLatoDx().toString();

            rigaTesto1.append("      ").append(sup).append("     ");
            rigaTesto2.append(" ").append(sx).append(tesseraPescata.getNomeBreve()).append(dx).append(" ");
            rigaTesto3.append("      ").append(inf).append("     ");
            
            System.out.println(rigaTesto1);
	        System.out.println(rigaTesto2);
	        System.out.println(rigaTesto3);
	        System.out.println();
            
			System.out.println("\nGiocatore " + colore + " cosa vuoi fare con la tessera che hai in mano - PREMI:");
			System.out.println("1 - RUOTARLA (senso antiorario)");
	        System.out.println("2 - AGGANCIARLA");
	        
	        //Se ho preso la tessera dai miei due slot di tessere prenotate, non ha senso lasciare
	        //disponibili queste due opzioni seguenti.
	        //Questo controllo verifica che la tessera considerata non sia una tessera prenotata.
	        if(!tesseraPrenotata) {
	        	System.out.println("3 - RIMETTERLA A POSTO");
	        	scelteDisponibili.add(3);
	        } 
	        if(!spazioTesserePrenotatePieno && !tesseraPrenotata) {
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
	

	//TODO - testare le quattro funzioni seguenti
	public void ruotaTessera(Tessera tesseraPescata) {
		boolean ruotaAncora = true;
		String scelta;
		while(ruotaAncora) {
			tesseraPescata.ruota();
			System.out.println("\nTessera ruotata:\n");
			StringBuilder rigaTesto1 = new StringBuilder(); // Connettori NORD
	        StringBuilder rigaTesto2 = new StringBuilder(); // Lati OVEST - NOME - EST
	        StringBuilder rigaTesto3 = new StringBuilder(); // Connettori SUD

	        String sup = tesseraPescata.getLatoSup().toString();
            String inf = tesseraPescata.getLatoDown().toString();
            String sx  = tesseraPescata.getLatoSx().toString();
            String dx  = tesseraPescata.getLatoDx().toString();

            rigaTesto1.append("      ").append(sup).append("     ");
            rigaTesto2.append(" ").append(sx).append(tesseraPescata.getNomeBreve()).append(dx).append(" ");
            rigaTesto3.append("      ").append(inf).append("     ");
            
            System.out.println(rigaTesto1);
	        System.out.println(rigaTesto2);
	        System.out.println(rigaTesto3);
	        System.out.println();
	        
			System.out.println("\nVuoi ruotarla ancora? premi si/no: ");
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
			System.out.println("\nQUALE MAZZETTO DI CARTE VUOI GUARDARE (da 1 a 3)?");
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
	
	public Tessera chiediTesseraScopertaDaPescare(List<Tessera> tessereScoperte) {
		boolean indiceNonValido = true;
		int sceltaIndice = -1;
		System.out.println("\n--- TESSERE SCOPERTE ---");
		stampaTessere(tessereScoperte);
		while(indiceNonValido){
			System.out.println("INDICA LA POSIZIONE DELLA TESSERA CHE VUOI PESCARE " +
					"Da 1 a " + tessereScoperte.size());
			try {
				sceltaIndice = Integer.parseInt(sc.nextLine());
				if(sceltaIndice < 1 || sceltaIndice > tessereScoperte.size()) {
					System.err.println("Scelta non valida, indice non esistente.");
				} else {
					//tolgo 1 a sceltaIndice di modo da ottenere l'indice corretto
					sceltaIndice--;
					System.out.println(tessereScoperte.get(sceltaIndice));
					return tessereScoperte.remove(sceltaIndice);
				}
					
			} catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        }
			
		}
			return null;
	}
	
	public void stampaTessere(List<Tessera> tessereDaStampare) {
		//TODO - da sistemare con la nuova toString()
		for(Tessera tessera : tessereDaStampare) {
			System.out.println("\n");
			System.out.println(tessera);
		}
	}
	
	public Tessera chiediTesseraPrenotata(List<Tessera> tesserePrenotate) {
		boolean indiceNonValido = true;
		int sceltaIndice = -1;
		System.out.println("\n--- TESSERE PRENOTATE ---");
		stampaTessere(tesserePrenotate);
		while(indiceNonValido){
			System.out.println("INDICA LA POSIZIONE DELLA TESSERA CHE VUOI PESCARE " +
					"indice fino a ->" + tesserePrenotate.size());
			try {
				sceltaIndice = Integer.parseInt(sc.nextLine());
				if(sceltaIndice < 1 || sceltaIndice > tesserePrenotate.size()) {
					System.err.println("Scelta non valida, indice non esistente.");
				} else {
					//tolgo 1 a sceltaIndice di modo da ottenere l'indice corretto
					sceltaIndice--;
					System.out.println(tesserePrenotate.get(sceltaIndice));
					return tesserePrenotate.remove(sceltaIndice);
				}
					
			} catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        }
			
		}
			return null;
	}

	
	public void agganciaTessera(Giocatore giocatore, Tessera tesseraDaAgganciare) {
		boolean tesseraAgganciata = false;
		
		
		while(!tesseraAgganciata) {
			 int riga = -1, colonna = -1;
			    boolean rigaValida = false;
			    boolean colonnaValida = false;

			    // Stampa la plancia per aiutare l'utente (metodo helper)
			    stampaNave(giocatore.getPlanceNave());
			    // Chiedi la riga
			    while (!rigaValida) {
			        System.out.print("Inserisci la RIGA dove agganciare (es. 5, 6, ...): ");
			        String inputRiga = sc.nextLine().trim();
			        try {
			            riga = Integer.parseInt(inputRiga);
			            //aggiusto la riga sulla base degli indici stampati
			             //verifica se la riga è dentro i limiti
			             if (riga - 5 >= 0 && riga - 5 <= PlanceNaveLivello1.getNumRighe()) {
			                 rigaValida = true;
			             } else {
			                 System.err.println("Riga fuori dai limiti della plancia.");
			             }
			            
			        } catch (NumberFormatException e) {
			            System.err.println("Formato riga non valido. Inserisci un numero.");
			        }
			    }

			    // Chiedi la colonna
			    while (!colonnaValida) {
			        System.out.print("Inserisci la COLONNA dove agganciare (es. 4, 5, ...): ");
			        String inputColonna = sc.nextLine().trim();
			        try {
			            colonna = Integer.parseInt(inputColonna);
			             
			             if (colonna - 4 >= 1 && colonna - 4 <= PlanceNaveLivello1.getNumColonne()) {
			                 colonnaValida = true;
			             } else {
			                 System.err.println("Colonna fuori dai limiti della plancia.");
			             }
			        } catch (NumberFormatException e) {
			            System.err.println("Formato colonna non valido. Inserisci un numero.");
			        }
			        
			    }
			    
			    if(GestorePlanceNave.agganciaTessera(giocatore.getPlanceNave(), tesseraDaAgganciare, riga - 5, colonna - 4)) {
			    	System.out.println("Tessera agganciata con successo alla posizione (" + riga + "," + colonna + ").");
			    	tesseraAgganciata = true;
			    }
			
		}
	}
	
	
	
	//-------------
	// fase di preparazione al decollo
	//-------------
	
	
	
	
	//TODO - crea un unico metodo inizioFase e poi fagli passare una stringa
	public void inizioPreparazioneAlDecollo() {
		System.out.println("----- FASE DI PREPARAZIONE AL DECOLLO -----");
	}
	
	public void posizionamentoAlieni(Colore coloreGiocatore) {
		System.out.println("GIOCATORE " + coloreGiocatore);
		System.out.println("\nPOSIZIONAMENTO ALIENI e/o EQUIPAGGIO.");
	}
	
	
	public boolean chiediSePosizionareAlieno(String domanda) {
		//inputValido non verra' mai modificata in quanto appena si inserisce un input
		//valido la funzione ritorna un valore e torna al chiamante.
		boolean inputValido = false;
		String scelta;
		while(!inputValido) {
			System.out.println(domanda);
			System.out.println("PREMI:");
			System.out.println("si - posiziona l'alieno");
			System.out.println("no - riempi la cabina con equipaggio");
			try {
				scelta = sc.nextLine().trim();
				if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && 
					!scelta.equalsIgnoreCase("s") && !scelta.equalsIgnoreCase("n")) {
					throw new IllegalArgumentException("scelta non valida, reinseriscila.");
				}
				
				if(scelta.equalsIgnoreCase("si") || scelta.equalsIgnoreCase("s")) {
					return true;
				} else {
					return false;
				}
			} catch (IllegalArgumentException e){
				System.err.println(e.getMessage());
			}
			
		}
		//punto di codice irraggiungibile
		return false;
	}
		
	
	
	
	//-------------
	// fase di volo
	//-------------
	
	
	//TODO - vedi come chiamare con lo stesso nome anche il metodo per gli alieni
	public boolean chiediSeEseguireAzione(String domanda) {
		String scelta;
		while(true) {
			System.out.println(domanda);
			System.out.println("PREMI si/no:");
			try {
				scelta = sc.nextLine().trim();
				if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && 
					!scelta.equalsIgnoreCase("s") && !scelta.equalsIgnoreCase("n")) {
					throw new IllegalArgumentException("scelta non valida, reinseriscila.");
				}
				
				if(scelta.equalsIgnoreCase("si") || scelta.equalsIgnoreCase("s")) {
					return true;
				} else {
					return false;
				}
			} catch (IllegalArgumentException e){
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	//TODO - da rinominare chiediSeAttivare, togli parametro giocatore
	public boolean chiediAttivare(Giocatore giocatore) {
		System.out.println("\n--- TURNO DEL GIOCATORE " + giocatore.getColore() + " ---");
		String scelta="";
		boolean inputValido=false;
		while(!inputValido) {
			System.out.println("Vuoi attivare la carta? premi si/no: ");
			try {
				scelta = sc.nextLine().trim();
				if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && 
					!scelta.equalsIgnoreCase("s") && !scelta.equalsIgnoreCase("n")) {
					throw new IllegalArgumentException("scelta non valida, reinseriscila.");
				}
				if(scelta.equalsIgnoreCase("no") || scelta.equalsIgnoreCase("n")) {
					return false;
				}else if(scelta.equalsIgnoreCase("si")||scelta.equalsIgnoreCase("s")) { // messo per chiarezza
					return true;
				}
				// messo per chiarezza
				inputValido=true;
					
			} catch (IllegalArgumentException e){
				System.err.println(e.getMessage());
			}
			
		}
		return false; // messo per chiarezza 
	}
	
	/**
	 * Gestisce la situazione in cui un giocatore non ha abbastanza spazio per
	 * tutte le merci disponibili e deve scegliere quali prendere.
	 *
	 * @param merciDisponibili La lista completa delle merci che il giocatore potrebbe prendere.
	 * @param spazioDisponibile Il numero di scomparti liberi sulla nave del giocatore.
	 * @return Una nuova lista contenente solo le merci che il giocatore ha scelto di caricare.
	 */
	public List<Merci> chiediMerciDaPrendere(List<Merci> merciDisponibili, int spazioDisponibile) {
	    System.out.println("\nATTENZIONE: Spazio di carico insufficiente!");
	    System.out.println("Hai " + spazioDisponibile + " scomparti liberi, ma ci sono " + merciDisponibili.size() + " merci disponibili.");
	    System.out.println("Scegli quali merci caricare.");

	    // Creiamo una copia della lista di merci disponibili per poterla modificare (rimuovendo le merci scelte)
	    List<Merci> merciAncoraDaScegliere = new ArrayList<>(merciDisponibili);
	    // Questa lista conterrà le merci che il giocatore decide di prendere
	    List<Merci> merciScelte = new ArrayList<>();

	    // Il giocatore continua a scegliere finché non riempie il suo spazio o finché non ci sono più merci da scegliere
	    while (merciScelte.size() < spazioDisponibile && !merciAncoraDaScegliere.isEmpty()) {
	        
	        // 1. Mostra le opzioni rimanenti
	        System.out.println("\nSpazio rimanente: " + (spazioDisponibile - merciScelte.size()));
	        System.out.println("Merci ancora disponibili:");
	        for (int i = 0; i < merciAncoraDaScegliere.size(); i++) {
	            System.out.println("  " + (i + 1) + ". Merce di colore " + merciAncoraDaScegliere.get(i).getColore());
	        }
	        System.out.println("  0. Ho finito, non voglio caricare altre merci.");

	        // 2. Chiedi l'input
	        int scelta = -1;
	        boolean inputValido = false;
	        while (!inputValido) {
	            System.out.print("Inserisci il numero della merce da caricare: ");
	            try {
	                scelta = Integer.parseInt(sc.nextLine());
	                if (scelta >= 0 && scelta <= merciAncoraDaScegliere.size()) {
	                    inputValido = true;
	                } else {
	                    System.err.println("Scelta non valida. Riprova.");
	                }
	            } catch (NumberFormatException e) {
	                System.err.println(INPUT_NON_VALIDO);
	            }
	        }

	        // 3. Processa la scelta
	        if (scelta == 0) {
	            // Il giocatore ha deciso di fermarsi
	            System.out.println("Scelta terminata.");
	            break; 
	        } else {
	            // Il giocatore ha scelto una merce. La spostiamo dalla lista delle disponibili a quella delle scelte.
	            int indiceScelto = scelta - 1;
	            Merci mercePresa = merciAncoraDaScegliere.remove(indiceScelto);
	            merciScelte.add(mercePresa);
	            System.out.println("Caricata merce di colore " + mercePresa.getColore() + ".");
	        }
	    }

	    if (merciScelte.size() == spazioDisponibile) {
	        System.out.println("Spazio di carico pieno!");
	    }

	    return merciScelte;
	}
	
	
	
	// PIOGGIA DI METEORITI/CANNONATE
	public void lancioDeiDadi(Colore colore, int risultato) {
		System.out.println("\nIL LEADER, IL GIOCATORE " + colore + " TIRA I DADI...");
		System.out.println("RISULTATO: " + risultato);
	}
	public void pericoloScampato() {
		System.out.println("Hai scampato il pericolo!");
	}
	
	
	
	/**
	 * Mostra al giocatore i pianeti disponibili e chiede di inserire un numero.
	 * NON esegue una validazione completa, ma si limita a restituire l'input numerico.
	 * La validazione della scelta (es. pianeta occupato) viene gestita dal chiamante.
	 *
	 * @param giocatoreCorrente Il giocatore che sta facendo la scelta.
	 * @param pianeti L'array dei pianeti disponibili su questa carta.
	 * @param pianetiOccupati Un array booleano che indica quali pianeti sono già stati scelti.
	 * @return L'indice (0-based) del pianeta scelto, o -1 se l'input non è un numero valido.
	 */
	public int scegliPianeta(Giocatore giocatoreCorrente, Pianeta[] pianeti, boolean[] pianetiOccupati) {
	    
	    // --- 1. Mostra le opzioni disponibili ---
	    System.out.println("Pianeti disponibili:");
	    for (int i = 0; i < pianeti.length; i++) {
	        // Stampa il numero del pianeta e se è occupato
	        System.out.print("  " + (i + 1) + ". Pianeta " + (i + 1));
	        if (pianetiOccupati[i]) {
	            System.out.print(" (OCCUPATO)\n");
	        } else {
	            // Se non è occupato, mostra le merci che contiene
	            System.out.print(" - Merci: ");
	            Merci[] merci = pianeti[i].getMerciPianeta();
	                for (int j = 0; j < merci.length; j++) {
	                    // Stampo l'iniziale del colore della merce (es. R, G, B)
	                    System.out.print(merci[j].getColore().toString().charAt(0)); 
	                    if (j < merci.length - 1) {
	                        System.out.print(", ");
	                    }
	                }
	                System.out.println();
	        }
	    }
	     
	    
	    //il ciclo continua finche' non viene inserita una scelta valida
	    while(true) {
		    System.out.print("Inserisci il numero del pianeta che vuoi scegliere (1-" + pianeti.length + "): ");
		    try {
	            int scelta = Integer.parseInt(sc.nextLine());
	            if (scelta >= 1 && scelta <= pianeti.length && !pianetiOccupati[scelta - 1]) {
	            	return scelta - 1;
	            } else {
	                System.err.println("Numero del pianeta scelto non valido, reinserirlo.");
	            }
	        } catch (NumberFormatException e) {
	            System.err.println(INPUT_NON_VALIDO);
	        }
	    }
	}

	
	
	
	
	
	
	//-------------------------
	// Fine del viaggio
	//-------------------------
	
	
	
	
	public void stampaMessaggio(String messaggio) {
		System.out.println(messaggio);
	}
	
	/**
	 * Stampa a schermo il risultato finale della partita, gestendo i casi di
	 * nessun vincitore, vincitore singolo o parità.
	 *
	 * @param vincitori La lista dei giocatori che hanno vinto (può essere vuota).
	 * @param maxCrediti Il punteggio più alto raggiunto.
	 */
	public void annunciaVincitore(List<Giocatore> vincitori, int maxCrediti) {
	    // Caso 1: Nessun vincitore
	    if (vincitori.isEmpty() || maxCrediti < 1) { 
	        System.out.println("\nNessun vincitore! Un viaggio fallimentare per tutti.");
	    } 
	    // Caso 2: C'è un solo vincitore
	    else if (vincitori.size() == 1) {
	        Giocatore vincitore = vincitori.get(0);
	        System.out.println("\nIL VINCITORE È IL GIOCATORE " + vincitore.getColore().toString().toUpperCase() + "!");
	    } 
	    // Caso 3: Ci sono più vincitori in parità
	    else {
	        System.out.print("\nC'È UNA PARITÀ! I VINCITORI SONO: ");
	        for (Giocatore vincitore : vincitori) {
	            System.out.print("GIOCATORE " + vincitore.getColore().toString().toUpperCase() + " ");
	        }
	    }
	}




}
