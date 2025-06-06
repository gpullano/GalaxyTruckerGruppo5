package plance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Tessera;

public class GestorePlanceNave {
	private static final Posizione POSIZIONE_CABINA_CENTRALE = new Posizione(2, 3);

	public static boolean posizionaTessera(PlanceNave planceNave, Tessera tessera, int riga,int colonna) {
		/*if (riga < 0 || riga >= getCaselle().length || colonna < 0 || colonna >= getCaselle()[0].length) {
			System.out.println("Errore: posizione fuori dai limiti (" + riga + "," + colonna + ")");
		    return false;
		}*/
		
		if (!planceNave.getCaselle()[riga][colonna].isUtilizzabile()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") non è utilizzabile");
		    return false;
		}
		
		if (planceNave.getCaselle()[riga][colonna].isOccupata()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") è già occupata");
		    return false;
		}
		
		planceNave.getCaselle()[riga][colonna].setTessera(tessera);
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
	public static boolean agganciaTessera(PlanceNaveLivello1 planceNave, Tessera tesseraDaAgganciare, int riga, int colonna) {
	    // --- Controllo Limiti Plancia ---
	    // Verifica se le coordinate target (riga, colonna) sono valide per la matrice.
	    if (riga < 0 || riga >= PlanceNaveLivello1.getNumRighe() || colonna < 0 || colonna >= PlanceNaveLivello1.getNumColonne()) {
	        System.err.println("Errore: Le coordinate (" + riga + "," + colonna + ") sono fuori dai limiti della plancia.");
	        return false;
	    }

	    // --- Controllo Casella Target ---
	    Casella casellaTarget = planceNave.getCaselle()[riga][colonna];
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
	        if (rigaVicino >= 0 && rigaVicino < PlanceNaveLivello1.getNumRighe() &&
	            colonnaVicino >= 0 && colonnaVicino < PlanceNaveLivello1.getNumColonne()) {

	            // Ora che siamo sicuri che le coordinate del vicino sono valide, possiamo accedere alla casella.
	            Casella casellaVicina = planceNave.getCaselle()[rigaVicino][colonnaVicino];
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

	
	/**
     * Esegue una scansione completa della nave e rimuove tutte le tessere "orfane",
     * ovvero quelle che non sono più connesse alla cabina centrale fissa.
     *
     */
    public static void gestisciRimozioneOrfani(PlanceNaveLivello1 planceNave) {       
     // Controlla se la cabina esiste ANCORA. Se no, tutta la nave è persa.
    	Casella[][] caselle = planceNave.getCaselle();
        if (!caselle[POSIZIONE_CABINA_CENTRALE.getRiga()][POSIZIONE_CABINA_CENTRALE.getColonna()].isOccupata()) {
            // Metodo helper per pulire l'intera plancia
            rimuoviTuttaLaNave(planceNave); 
            return; // Esci subito dal metodo
        }


        Set<Posizione> tessereConnesse = new HashSet<>();
        Queue<Posizione> codaBFS = new ArrayDeque<>();

        // Inizializza il BFS con la posizione della cabina centrale.
        codaBFS.offer(POSIZIONE_CABINA_CENTRALE);
        tessereConnesse.add(POSIZIONE_CABINA_CENTRALE);
        
        // I vettori di spostamento per esplorare i vicini
        int[] dr = {-1, 0, 1, 0}; // N, E, S, W
        int[] dc = {0, 1, 0, -1}; // N, E, S, W

        while (!codaBFS.isEmpty()) {
            Posizione attuale = codaBFS.poll();
            Tessera tesseraAttuale = caselle[attuale.getRiga()][attuale.getColonna()].getTessera();

            for (int i = 0; i < 4; i++) {
                int rigaVicino = attuale.getRiga() + dr[i];
                int colonnaVicino = attuale.getColonna() + dc[i];
                
                if (isPosizioneValida(rigaVicino, colonnaVicino)) {
                    Posizione posVicino = new Posizione(rigaVicino, colonnaVicino);
                    Casella casellaVicina = caselle[rigaVicino][colonnaVicino];

                    if (casellaVicina.isOccupata() && !tessereConnesse.contains(posVicino)) {
                        Tessera tesseraVicina = casellaVicina.getTessera();
                        Connettore connettoreDaAttuale = null;
                        Connettore connettoreDaVicino = null;

                        // Logica identica a prima per ottenere i connettori corretti
                        switch (i) {
                            case 0: // Vicino a NORD
                                connettoreDaAttuale = tesseraAttuale.getLatoSup();
                                connettoreDaVicino = tesseraVicina.getLatoDown();
                                break;
                            case 1: // Vicino a EST
                                connettoreDaAttuale = tesseraAttuale.getLatoDx();
                                connettoreDaVicino = tesseraVicina.getLatoSx();
                                break;
                            case 2: // Vicino a SUD
                                connettoreDaAttuale = tesseraAttuale.getLatoDown();
                                connettoreDaVicino = tesseraVicina.getLatoSup();
                                break;
                            case 3: // Vicino a OVEST
                                connettoreDaAttuale = tesseraAttuale.getLatoSx();
                                connettoreDaVicino = tesseraVicina.getLatoDx();
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

        // Fase 3: Rimuovi tutte le tessere sulla plancia che non sono state raggiunte dal BFS.
        for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
            for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
                if (caselle[r][c].isOccupata() && !tessereConnesse.contains(new Posizione(r, c))) {
                    System.out.println("Tessera orfana rimossa a: (" + r + ", " + c + ")");
                    caselle[r][c].setTessera(null);
                }
            }
        }
    }
    
    /**
     * metodo helper per rimuovere tutta la nave. E' chiamato in gestisciRimozioneOrfani().
     */
    private static void rimuoviTuttaLaNave(PlanceNaveLivello1 planceNave) {
    	Casella[][] caselle = planceNave.getCaselle();
        //TODO - da spostare in consoleIO
    	System.out.println("La Cabina Centrale è stata distrutta! Tutta la nave è persa.");
        for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
            for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
                if (caselle[r][c].isOccupata()) {
                    caselle[r][c].setTessera(null);
                }
            }
        }
    }
	

    /**
     * Metodo helper per verificare se due connettori possono connettersi.
     */
    private static boolean possonoConnettersi(Connettore c1, Connettore c2) {
        // Un connettore valido per la connessione è SINGOLO, DOPPIO o UNIVERSALE.
        // Altri tipi (NULLO, CANNONE, MOTORE, etc.) non formano legami.
        if (!isConnettoreDiLegame(c1) || !isConnettoreDiLegame(c2)) {
            return false;
        }

        // Se uno è universale, la connessione è sempre valida.
        if (c1 == Connettore.UNIVERSALE || c2 == Connettore.UNIVERSALE) {
            return true;
        }

        // Altrimenti, devono essere dello stesso tipo (SINGOLO-SINGOLO o DOPPIO-DOPPIO).
        return c1 == c2;
    }

    /**
     * Helper per determinare se un connettore è usato per legare tessere.
     */
    private static boolean isConnettoreDiLegame(Connettore c) {
        return c == Connettore.SINGOLO || c == Connettore.DOPPIO || c == Connettore.UNIVERSALE;
    }

    
    /**
     * Verifica l'intera nave e rimuove immediatamente tutte le tessere
     * che violano le regole di piazzamento (es. motori/cannoni bloccati).
     * Poiché rimuove componenti, potrebbe creare delle tessere orfane. E' 
     * per questo motivo seguita da una chiamata al metodo gestisciRimozioneOrfani
     */
    public static void verificaERimuoviTessereIllegali(PlanceNaveLivello1 planceNave) {
        Casella[][] caselle  = planceNave.getCaselle();
        List<Posizione> posizioniDaRimuovere = new ArrayList<>();

        // Fase 1: Identifica tutte le tessere illegali senza modificare la griglia
        // per evitare problemi di concorrenza durante l'iterazione.
        for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
            for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
                if (caselle[r][c].isOccupata()) {
                    Tessera tesseraCorrente = caselle[r][c].getTessera();
                    boolean isLegale = true;

                    if (tesseraCorrente instanceof Cannone || tesseraCorrente instanceof CannoneDoppio) {
                        isLegale = isPiazzamentoCannoneLegale(planceNave, r, c);
                    } else if (tesseraCorrente instanceof Motore || tesseraCorrente instanceof MotoreDoppio) {
                        isLegale = isPiazzamentoMotoreLegale(planceNave, r, c);
                    }
                    //TODO - aggiungi controlli supporti vitali

                    if (!isLegale) {
                        posizioniDaRimuovere.add(new Posizione(r, c));
                    }
                }
            }
        }

        // Fase 2: Rimuovi tutte le tessere identificate come illegali.
        for (Posizione pos : posizioniDaRimuovere) {
        	caselle[pos.getRiga()][pos.getColonna()].setTessera(null);
            System.out.println("Tessera illegale rimossa a: " + pos);
        }
    }


    /**
     * Verifica se il piazzamento di un cannone alla posizione data è legale.
     * Un cannone è illegale se la casella davanti alla sua bocca da fuoco è occupata.
     *
     * @param r Riga del cannone.
     * @param c Colonna del cannone.
     * @return true se il piazzamento è legale, false altrimenti.
     */
    private static boolean isPiazzamentoCannoneLegale(PlanceNaveLivello1 planceNave, int r, int c) {
        Tessera cannone = planceNave.getCaselle()[r][c].getTessera();
        Casella[][] caselle  = planceNave.getCaselle();
        // Controlliamo ogni lato per la presenza di un connettore di tipo cannone.
        
        // Cannone punta a NORD (verso l'alto)
        if (cannone.getLatoSup() == Connettore.CANNONE || cannone.getLatoSup() == Connettore.CANNONEDOPPIO) {
            int rigaDavanti = r - 1;
            // Se c'è una casella davanti e questa è occupata, il piazzamento è illegale.
            if (isPosizioneValida(rigaDavanti, c) && caselle[rigaDavanti][c].isOccupata()) {
                return false;
            }
        }
        
        // Cannone punta a EST (verso destra)
        if (cannone.getLatoDx() == Connettore.CANNONE || cannone.getLatoDx() == Connettore.CANNONEDOPPIO) {
            int colonnaDavanti = c + 1;
            if (isPosizioneValida(r, colonnaDavanti) && caselle[r][colonnaDavanti].isOccupata()) {
                return false;
            }
        }
        
        // Cannone punta a SUD (verso il basso)
        if (cannone.getLatoDown() == Connettore.CANNONE || cannone.getLatoDown() == Connettore.CANNONEDOPPIO) {
            int rigaDavanti = r + 1;
            if (isPosizioneValida(rigaDavanti, c) && caselle[rigaDavanti][c].isOccupata()) {
                return false;
            }
        }
        
        // Cannone punta a OVEST (verso sinistra)
        if (cannone.getLatoSx() == Connettore.CANNONE || cannone.getLatoSx() == Connettore.CANNONEDOPPIO) {
            int colonnaDavanti = c - 1;
            if (isPosizioneValida(r, colonnaDavanti) && caselle[r][colonnaDavanti].isOccupata()) {
                return false;
            }
        }

        return true; // Se nessun controllo ha fallito, il piazzamento è legale.
    }


    /**
     * Verifica se il piazzamento di un motore alla posizione data è legale.
     * Un motore è illegale se non punta "indietro" (verso il basso, SUD) o
     * se la casella dietro il suo scarico è occupata.
     *
     * @param r Riga del motore.
     * @param c Colonna del motore.
     * @return true se il piazzamento è legale, false altrimenti.
     */
    private static boolean isPiazzamentoMotoreLegale(PlanceNaveLivello1 planceNave, int r, int c) {
        Tessera motore = planceNave.getCaselle()[r][c].getTessera();
        Casella[][] caselle  = planceNave.getCaselle();

        // Controlla se c'è un connettore motore su un lato che non sia quello SUD.
        if (motore.getLatoSup() == Connettore.MOTORE || motore.getLatoSup() == Connettore.MOTOREDOPPIO ||
            motore.getLatoDx() == Connettore.MOTORE || motore.getLatoDx() == Connettore.MOTOREDOPPIO ||
            motore.getLatoSx() == Connettore.MOTORE || motore.getLatoSx() == Connettore.MOTOREDOPPIO) {
            return false; // Piazzamento illegale: motore non punta indietro.
        }

        //TODO - valutare se necessario
        // Controlla se il lato SUD ha effettivamente un motore. Se no, non è un motore valido.
//        if (motore.getLatoDown() != Connettore.MOTORE && motore.getLatoDown() != Connettore.MOTOREDOPPIO) {
//            return false;
//        }

        // Ora controlliamo se lo scarico ha dietro una tessera
        int rigaDietro = r + 1;
        if (isPosizioneValida(rigaDietro, c) && caselle[rigaDietro][c].isOccupata()) {
            return false; // Piazzamento illegale: scarico del motore bloccato.
        }
        
        return true; // Se tutti i controlli passano, il piazzamento è legale.
    }

    /**
     * Metodo helper per verificare se una data posizione (r,c) è all'interno
     * dei limiti della plancia.
     */
    private static boolean isPosizioneValida(int r, int c) {
        return r >= 0 && r < PlanceNaveLivello1.getNumRighe() && c >= 0 && c < PlanceNaveLivello1.getNumColonne();
    }
}
