package plance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import collezionabili.Merci;
import gameLogic.Colore;
import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Tessera;

public class PlanceNaveLivello1 extends PlanceNave{
	private static final int NUM_RIGHE = 5;
	public static int getNumRighe() {
		return NUM_RIGHE;
	}

	private static final int NUM_COLONNE = 7;
	public static int getNumColonne() {
		return NUM_COLONNE;
	}

	private static final int NUM_TESSERE_PRENOTABILI = 2;
	
	private Casella[][] caselle;
	private int potenzaFuoco;
	private int potenzaMotori;
	private int equipaggioTotale;
	private int energiaTotale;
	private int merciTotali;
	private List<Merci> merciNave;
	private boolean componenteAgganciato; // boolean, true/false
	private List<Tessera> spazioTesserePrenotate;

	public PlanceNaveLivello1(Colore colore) {
		super(NUM_RIGHE, NUM_COLONNE);
		this.creaNave();
		this.equipaggioTotale = 0;
		this.potenzaFuoco = 0;
		this.potenzaMotori = 0;
		this.energiaTotale = 0;
		this.setMerciTotali(0);
		this.merciNave = new LinkedList<>();
		this.componenteAgganciato = false;
		this.spazioTesserePrenotate = new LinkedList<>();
		this.caselle[2][3].setTessera(new CabinaCentrale(colore));
	}

	// getters e setters
	
	public List<Tessera> getTesserePrenotate(){
		return spazioTesserePrenotate;
	}
	
	public int getSpazioMerciRimasto() {
		return this.merciTotali - this.merciNave.size();
	}
	
	public void aggiungiTesseraPrenotata(Tessera t) {
		if(this.spazioTesserePrenotate.size() >= NUM_TESSERE_PRENOTABILI) {
			throw new IllegalArgumentException("Non puoi prenotare ulteriori tessere, "
					+ "il numero massimo e'" + NUM_TESSERE_PRENOTABILI);
		} 
		this.spazioTesserePrenotate.add(t);
	}
	
	
	// getter e setter
	public int getMerciTotali() {
		return merciTotali;
	}

	public void setMerciTotali(int merciTotali) {
		this.merciTotali = merciTotali;
	}
	
	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}


	public void setPotenzaFuoco(int potenzaFuoco) {
		this.potenzaFuoco = potenzaFuoco;
	}


	public int getPotenzaMotori() {
		return potenzaMotori;
	}


	public void setPotenzaMotori(int potenzaMotrice) {
		this.potenzaMotori = potenzaMotrice;
	}


	
	public int getEquipaggioTotale() {
		return equipaggioTotale;
	}


	public void setEquipaggioTotale(int equipaggioTotale) {
		this.equipaggioTotale = equipaggioTotale;
	}
	
	public int getEnergiaTotale() {
		return energiaTotale;
	}


	public void setEnergiaTotale(int energiaTotale) {
		this.energiaTotale = energiaTotale;
	}

	
	public boolean isComponenteAgganciato() {
		return componenteAgganciato;
	}

	public void setComponenteAgganciato(boolean componenteAgganciato) {
		this.componenteAgganciato = componenteAgganciato;
	}

	
	// metodi
	
	public boolean isSpazioTesserePrenotatePieno() {
		return spazioTesserePrenotate.size() == NUM_TESSERE_PRENOTABILI;
	}	
	
	/**
	 * Metodo che verifica se sono stati prenotati componenti
	 * viene utilizzato nella classe dedicata all'input/output (ConsoleIO)
	 * nella fase di assemblaggio per mostrare determinate opzioni
	 * ad esempio: "PRENDI TESSERA PRENOTATA".
	 * @return
	 */
	public boolean haTesserePrenotate() {
		return !this.spazioTesserePrenotate.isEmpty();
	}
	
	//TODO - verificare se serve
	public void aggiungiEnergia(int energia) {
		if(energia < 0) {
			throw new IllegalArgumentException("Non puoi inserire un'energia negativa");
		}
		this.energiaTotale += energia;
	}
	
	public void calcolaPotenzaFuoco(int energieDaSpendere) {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cannone cannone) {
					this.potenzaFuoco += cannone.getSparo();
				} else if(this.caselle[i][j].getTessera() instanceof CannoneDoppio cannoneDoppio && 
						energieDaSpendere > 0 && this.energiaTotale > 0) {
					this.potenzaFuoco += cannoneDoppio.getSparo();
					this.energiaTotale--;
					energieDaSpendere--;
				}
			}
		}
	}
	
	public void calcolaPotenzaMotori(int energieDaSpendere) {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Motore motore) {
					this.potenzaMotori += motore.getPotenza();
				} else if(this.caselle[i][j].getTessera() instanceof MotoreDoppio motoreDoppio && 
						energieDaSpendere > 0 && this.energiaTotale > 0) {
					this.potenzaMotori += motoreDoppio.getPotenza();
					this.energiaTotale--;
					energieDaSpendere--;
				}
			}
		}
	}
	
	public void calcolaEquipaggio() {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cabina cabina) {
					this.equipaggioTotale += cabina.getEquipaggio() + cabina.getAlieni();
				} else if(this.caselle[i][j].getTessera() instanceof CabinaCentrale cabinaCentrale) {
					this.equipaggioTotale += cabinaCentrale.getEquipaggio();
				}
			}
		}
	}
	
	//TODO - valutare una funzione attiva scudo che permette di attivare lo scudo se abbiamo energia
	// e creare eventualmente un attributo "latiProtetti" che tiene traccia dei lati della nave
	// protetti dagli scudi, di modo da non dover controllare tessera per tessera. Questa funzione
	// verrà chiamata durante l'assemblaggio.
	
	@Override
	public void creaNave() {
		for(int r = 0; r <= 4; r++) {
		int c = 0;
		if (r == 0) {
			for( c = 3; c <= 3;c++) {
				getCaselle()[r][c].setUtilizzabile(true);	
		}
		}
		if (r == 1) {
			for(c = 2; c <= 4; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 2) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 3) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 4) {
			for(c = 1; c <= 2; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			} 
			for(c = 4; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}	
		}
	}
	
	public boolean posizionaTessera(Tessera tessera, int riga,int colonna) {
		/*if (riga < 0 || riga >= getCaselle().length || colonna < 0 || colonna >= getCaselle()[0].length) {
			System.out.println("Errore: posizione fuori dai limiti (" + riga + "," + colonna + ")");
		    return false;
		}*/
		
		if (!getCaselle()[riga][colonna].isUtilizzabile()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") non è utilizzabile");
		    return false;
		}
		
		if (getCaselle()[riga][colonna].isOccupata()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") è già occupata");
		    return false;
		}
		
		getCaselle()[riga][colonna].setTessera(tessera);
		System.out.println("Tessera posizionata con successo in (" + riga + "," + colonna + ")");
		return true;
	}
	
	

	/**
	 * Tenta di agganciare una tessera alla plancia in una data posizione.
	 * Esegue i controlli di base:
	 * 1. La posizione è entro i limiti della plancia.
	 * 2. La casella target è utilizzabile e non già occupata.
	 * 3. La casella target è adiacente ad almeno un'altra tessera già piazzata.
	 *
	 *
	 * @param riga Riga target (0-based).
	 * @param colonna Colonna target (0-based).
	 * @param tesseraDaAgganciare La tessera da piazzare.
	 * @return true se il piazzamento è valido e la tessera è stata agganciata, false altrimenti.
	 */
	public boolean agganciaTessera(int riga, int colonna, Tessera tesseraDaAgganciare) {
	    // --- Controllo Limiti Plancia ---
	    // Verifica se le coordinate target (riga, colonna) sono valide per la matrice.
	    if (riga < 0 || riga >= NUM_RIGHE || colonna < 0 || colonna >= NUM_COLONNE) {
	        System.err.println("Errore: Le coordinate (" + riga + "," + colonna + ") sono fuori dai limiti della plancia.");
	        return false;
	    }

	    // --- Controllo Casella Target ---
	    Casella casellaTarget = this.getCaselle()[riga][colonna];
	    if (!casellaTarget.isUtilizzabile() || casellaTarget.isOccupata()) {
	        System.err.println("Errore: La casella (" + riga + "," + colonna + ") non è utilizzabile o è già occupata.");
	        return false;
	    }

	    // --- Controllo di Adiacenza Base ---
	    // Deve esserci almeno un vicino già occupato.
	    boolean haAlmenoUnVicinoOccupato = false;
	    int[] dr = {-1, 0, 1, 0}; // Delta per NORD, EST, SUD, OVEST
	    int[] dc = {0, 1, 0, -1};

	    for (int i = 0; i < 4; i++) {
	        int rigaVicino = riga + dr[i];
	        int colonnaVicino = colonna + dc[i];

	        //Verifico che i lati adiacenti siano compresi nella matrice (per evitare una IndexOutOfBoundsException)
	        if (rigaVicino >= 0 && rigaVicino < NUM_RIGHE &&
	            colonnaVicino >= 0 && colonnaVicino < NUM_COLONNE) {

	            // Ora che siamo sicuri che le coordinate del vicino sono valide, possiamo accedere alla casella.
	            Casella casellaVicina = this.getCaselle()[rigaVicino][colonnaVicino];
	            if (casellaVicina.isOccupata()) {
	                haAlmenoUnVicinoOccupato = true;
	                break; // Trovato un vicino, non serve controllare gli altri. Esci dal loop 'for'.
	            }
	        }
	    }


	    if (!haAlmenoUnVicinoOccupato) {
	        System.err.println("Errore: La tessera deve essere agganciata adiacente a una tessera esistente.");
	        return false;
	    }

	    //TODO - da spostare in ConsoleIO
	    // --- Se tutti i controlli leggeri passano, piazza la tessera ---
	    casellaTarget.setTessera(tesseraDaAgganciare);
	    System.out.println("Tessera agganciata con successo alla posizione (" + riga + "," + colonna + ").");
	    return true;
	}

	public List<Merci> getMerciNave() {
		return merciNave;
	}

	public void setMerciNave(List<Merci> merciNave) {
		this.merciNave = merciNave;
	}
	
	
	
	public static List<Tessera> gestisciRimozioneComponente(
            PlanceNave planciaNave,
            Posizione posizioneDaRimuovere,
            List<Posizione> posizioniCabineIniziali) {

        List<Tessera> tessereRimosse = new ArrayList<>();
        Casella[][] griglia = planciaNave.getCaselle();

        // 1. Rimuovi la tessera specificata inizialmente (se presente)
        Casella casellaIniziale = griglia[posizioneDaRimuovere.getRiga()][posizioneDaRimuovere.getColonna()];
        if (casellaIniziale.isOccupata()) {
            tessereRimosse.add(casellaIniziale.getTessera());
            casellaIniziale.setTessera(null); // Rimuove la tessera e imposta occupata a false
        }

        // 2. Esegui BFS per trovare tutte le tessere connesse alle cabine
        Set<Posizione> tessereConnesse = new HashSet<>();
        Queue<Posizione> codaBFS = new ArrayDeque<>();

        // Aggiungi tutte le cabine iniziali alla coda e al set delle connesse
        for (Posizione posCabina : posizioniCabineIniziali) {
            if (griglia[posCabina.getRiga()][posCabina.getColonna()].isOccupata()) {
                codaBFS.offer(posCabina);
                tessereConnesse.add(posCabina);
            }
        }

        //Delta colonna e delta riga
        int[] dr = {-1, 0, 1, 0}; // Spostamento per NORD, EST, SUD, OVEST (rispetto alla tessera attuale)
        int[] dc = {0, 1, 0, -1}; // Spostamento per NORD, EST, SUD, OVEST (rispetto alla tessera attuale)

        while (!codaBFS.isEmpty()) {
            Posizione attuale = codaBFS.poll();
            // TODO - valuta se è necessaria quest'implementazione seguente
            // Non c'è bisogno di prendere la tessera qui se la casella è garantita occupata
            // Tessera tesseraAttuale = griglia[attuale.getRiga()][attuale.getColonna()].getTessera();
            // if (tesseraAttuale == null) continue; // Controllo di sicurezza, anche se non dovrebbe succedere

            // Esplora i vicini
            for (int i = 0; i < 4; i++) { // i=0 (NORD), i=1 (EST), i=2 (SUD), i=3 (OVEST)
                int rigaVicino = attuale.getRiga() + dr[i];
                int colonnaVicino = attuale.getColonna() + dc[i];
                Posizione posVicino = new Posizione(rigaVicino, colonnaVicino);

                // Controlla se il vicino è dentro i limiti della plancia
                if (rigaVicino >= 0 && rigaVicino < NUM_RIGHE && 
                    colonnaVicino >= 0 && colonnaVicino < NUM_COLONNE) {

                    Casella casellaVicina = griglia[rigaVicino][colonnaVicino];
                    Casella casellaAttuale = griglia[attuale.getRiga()][attuale.getColonna()];

                    // Se il vicino è occupato e non ancora visitato/connesso
                    if (casellaVicina.isOccupata() && !tessereConnesse.contains(posVicino)) {
                        Tessera tesseraAttuale = casellaAttuale.getTessera(); 
                        Tessera tesseraVicina = casellaVicina.getTessera();

                        if (tesseraAttuale == null) continue; // Controllo ulteriore

                        Connettore connettoreDaAttuale = null;
                        Connettore connettoreDaVicino = null;

                        // Mappa 'i' al lato corretto della tessera attuale e del suo vicino
                        switch (i) {
                            case 0: // Stiamo controllando il vicino a NORD della tessera attuale
                                connettoreDaAttuale = tesseraAttuale.getLatoSup();  // Lato NORD (superiore) della tessera attuale
                                connettoreDaVicino = tesseraVicina.getLatoDown(); // Lato SUD (inferiore) della tessera vicina (che è a Nord)
                                break;
                            case 1: // Stiamo controllando il vicino a EST della tessera attuale
                                connettoreDaAttuale = tesseraAttuale.getLatoDx();   // Lato EST (destro) della tessera attuale
                                connettoreDaVicino = tesseraVicina.getLatoSx();  // Lato OVEST (sinistro) della tessera vicina (che è a Est)
                                break;
                            case 2: // Stiamo controllando il vicino a SUD della tessera attuale
                                connettoreDaAttuale = tesseraAttuale.getLatoDown(); // Lato SUD (inferiore) della tessera attuale
                                connettoreDaVicino = tesseraVicina.getLatoSup();  // Lato NORD (superiore) della tessera vicina (che è a Sud)
                                break;
                            case 3: // Stiamo controllando il vicino a OVEST della tessera attuale
                                connettoreDaAttuale = tesseraAttuale.getLatoSx();   // Lato OVEST (sinistro) della tessera attuale
                                connettoreDaVicino = tesseraVicina.getLatoDx();   // Lato EST (destro) della tessera vicina (che è a Ovest)
                                break;
                        }

                        if (possonoConnettersi(connettoreDaAttuale, connettoreDaVicino)) {
                            tessereConnesse.add(posVicino);
                            codaBFS.offer(posVicino);
                        }
                    }
                }
            }
        }

        // 3. Rimuovi tutte le tessere che sono sulla plancia ma non nel set 'tessereConnesse'
        for (int r = 0; r < NUM_RIGHE; r++) {
            for (int c = 0; c < NUM_COLONNE; c++) {
                Posizione posCorrente = new Posizione(r, c);
                Casella casellaCorrente = griglia[r][c];
                if (casellaCorrente.isOccupata() && !tessereConnesse.contains(posCorrente)) {
                    tessereRimosse.add(casellaCorrente.getTessera());
                    casellaCorrente.setTessera(null);
                }
            }
        }

        return tessereRimosse;
    }

    /**
     * Metodo helper per verificare se due connettori possono connettersi.
     */
    private static boolean possonoConnettersi(Connettore c1, Connettore c2) {
        if (c1 == Connettore.NULLO || c2 == Connettore.NULLO) {
            return false; // I lati lisci (NULLO) non si connettono
        }
        
        //escludo tutti i connettori diversi da SINGOLO, UNIVERSALE e DOPPIO
        if (c1 == Connettore.UNIVERSALE && c2 == Connettore.UNIVERSALE ||
        	c1 == Connettore.UNIVERSALE && c2 == Connettore.SINGOLO ||
        	c1 == Connettore.SINGOLO && c2 == Connettore.UNIVERSALE ||
        	c1 == Connettore.UNIVERSALE && c2 == Connettore.DOPPIO ||
        	c1 == Connettore.DOPPIO && c2 == Connettore.UNIVERSALE) {
            return true; // Universale si connette con tutto (tranne NULLO, già gestito)
        }
        if ((c1 == Connettore.SINGOLO && c2 == Connettore.SINGOLO) ||
            (c1 == Connettore.DOPPIO && c2 == Connettore.DOPPIO)) {
            return true;
        }
        return false;
    }



	
}
