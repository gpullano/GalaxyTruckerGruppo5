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
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import tessere.Cabina;
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
	public void stampaVolo(PlanceVolo planceVolo) {
		for(int r = 0; r < planceVolo.getCella().length; r++) {
			for(int c = 0; c < planceVolo.getCella()[r].length; c++) {
					System.out.print(planceVolo.getCella()[r][c].toString() + '\t');
				
			}
			System.out.println('\n');
		}
	}
	
	public void stampaNave(PlanceNaveLivello1 planceNaveLivello1) {
		System.out.println();
		for(int r = 0; r <= 4; r++) {
			for(int c = 0; c <= 6; c++) {
				if(planceNaveLivello1.getCaselle()[r][c].isUtilizzabile()) {
					if (planceNaveLivello1.getCaselle()[r][c].getTessera() != null) {
						System.out.print(planceNaveLivello1.getCaselle()[r][c].getTessera().toString());
						System.out.print("\t\t");
					} else {
						System.out.print("▢\t\t");	
					}
				}else {
			    	System.out.print("\t\t");
				}
				
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	
	// print all the tile in our game and the connection type for each tile in the four sides but should we have a fixed connection type for each tile 
	public static void printTileTable() {
		String[][] tiles = {
			{"VanBa", "--", "S", "--", "--"},
			{"STVV", "U", "D", "--", "S"},
			{"STVM", "S", "S", "D", "S"},
			{"Stiva", "--", "S", "S", "U"},
			{"MotoD", "S", "M2", "S", "--"},
			{"Motor", "U", "S", "M", "--"},
			{"ModSt", "S", "--", "S", "--"},
			{"Scudo", "()", "()", "--", "U"},
			{"CannD", "C2", "D", "S", "U"},
			{"CANON", "+", "D", "S", "S"},
			{"CabCen", "U", "U", "U", "U"},
			{"CABIN", "S", "S", "D", "S"}
		};

		System.out.println("+--------------------------+-----------+-----------+-----------+-----------+");
		System.out.println("| Tessera                 | Superiore | Destra    | Inferiore | Sinistra  |");
		System.out.println("+--------------------------+-----------+-----------+-----------+-----------+");

		for (String[] tile : tiles) {
			System.out.printf(
				"| %-24s | %-9s | %-9s | %-9s | %-9s |\n",
				tile[0], tile[1], tile[2], tile[3], tile[4]
			);
		}

		System.out.println("+--------------------------+-----------+-----------+-----------+-----------+");
		System.out.println("LEGENDA: U=Universale, D=Doppio, S=Singolo, +=Cannone, M=Motore, C2=CannoneDoppio, M2=MotoreDoppio, ()=Scudo, --=Nullo");
	}
	
	
	
	
	
	
	
	
	//------------------------------------------------------------------
	//------- SETUP PARTITA
	
	public LivelloPartita chiediLivelloGioco() {
	    int scelta = -1;
	    LivelloPartita livelloScelto = null;
	    boolean inputValido = false;

	    while (!inputValido) {
	        System.out.println("\n--- MODALITA' - PREMI: ---");
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
			System.out.println("\nTESSERA PESCATA:\n\n" + tesseraPescata);
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
			System.out.println(tesseraPescata);
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
			System.out.println("\n" + tessera);
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
			        System.out.print("Inserisci la RIGA dove agganciare (es. 1, 2, ...): ");
			        String inputRiga = sc.nextLine().trim();
			        try {
			            riga = Integer.parseInt(inputRiga);
			             //verifica se la riga è dentro i limiti
			             if (riga >= 1 && riga <= PlanceNaveLivello1.getNumRighe()) {
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
			        System.out.print("Inserisci la COLONNA dove agganciare (es. 1, 2, ...): ");
			        String inputColonna = sc.nextLine().trim();
			        try {
			            colonna = Integer.parseInt(inputColonna);
			             
			             if (colonna >= 1 && colonna <= PlanceNaveLivello1.getNumColonne()) {
			                 colonnaValida = true;
			             } else {
			                 System.err.println("Colonna fuori dai limiti della plancia.");
			             }
			        } catch (NumberFormatException e) {
			            System.err.println("Formato colonna non valido. Inserisci un numero.");
			        }
			        
			    }
			    
			    if(GestorePlanceNave.agganciaTessera(giocatore.getPlanceNave(), tesseraDaAgganciare, riga, colonna)) {
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
		System.out.println("-----FASE DI PREPARAZIONE AL DECOLLO-----");
	}
	
	public void posizionamentoAlieni() {
		System.out.println("POSIZIONAMENTO ALIENI e/o EQUIPAGGIO.");
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
	public boolean chiediSeAzionareComponente(String domanda) {
		boolean inputValido = false;
		String scelta;
		while(!inputValido) {
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
	
		return false;
	}
	
	//TODO - da rinominare chiediSeAttivare, togli parametro giocatore
	public boolean chiediAttivare(Giocatore giocatore ) {
		String scelta="";
		boolean inputValido=false;
		while(!inputValido) {
			System.out.println("Vuoi attivare la carta? premi si/no");
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
	
	public void chiediMerciDaPrendere() {
		//TODO 
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

		System.out.println("\n--- TURNO DEL GIOCATORE " + giocatoreCorrente.getColore() + " ---");
	    
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
