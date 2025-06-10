package plance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import carteAvventura.Dimensione;
import carteAvventura.Meteorite;
import carteAvventura.Provenienza;
import carteAvventura.RisultatoImpatto;
import gameLogic.ConsoleIO;
import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Tessera;

public class GestorePlanceNave {
	private static final Posizione POSIZIONE_CABINA_CENTRALE = new Posizione(2, 3);
	
	/**
	 * Costruttore privato, il cui scopo è evitare che si tenti di istanziare la
	 * classe.
	 */

	private GestorePlanceNave() {
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
	        System.err.println("Le coordinate (" + riga + "," + colonna + ") sono fuori dai limiti della plancia.");
	        return false;
	    }

	    // --- Controllo Casella Target ---
	    Casella casellaTarget = planceNave.getCaselle()[riga][colonna];
	    if (!casellaTarget.isUtilizzabile() || casellaTarget.getTessera() != null) {
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
	            if (casellaVicina.getTessera() != null) {
	                haAlmenoUnVicinoOccupato = true;
	                break; // Trovato un vicino, non serve controllare gli altri. Esci dal loop 'for'.
	            }
	        }
	    }


	    if (!haAlmenoUnVicinoOccupato) {
	        System.err.println("Errore: La tessera deve essere agganciata adiacente a una tessera esistente.");
	        return false;
	    }

	    
	    casellaTarget.setTessera(tesseraDaAgganciare);    
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
        if (caselle[POSIZIONE_CABINA_CENTRALE.getRiga()][POSIZIONE_CABINA_CENTRALE.getColonna()].getTessera() == null) {
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

                    if (casellaVicina.getTessera() != null && !tessereConnesse.contains(posVicino)) {
                        Tessera tesseraVicina = casellaVicina.getTessera();
                        Connettore connettoreDaAttuale = getLato(tesseraAttuale, i);
                        Connettore connettoreDaVicino = getLatoOpposto(tesseraVicina, i);

                        if (possonoConnettersi(connettoreDaAttuale, connettoreDaVicino)) {
                            tessereConnesse.add(posVicino);
                            codaBFS.offer(posVicino);
                        }
                    }
                }
            }
        }

        // Rimuovi tutte le tessere sulla plancia che non sono state raggiunte dal BFS.
        for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
            for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
                if (caselle[r][c].getTessera() != null && !tessereConnesse.contains(new Posizione(r, c))) {
                    System.out.println("Tessera orfana rimossa a: (" + r + ", " + c + ")");
                    caselle[r][c].setTessera(null);
                    planceNave.incrementaPilaScarti();
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
                if (caselle[r][c].getTessera() != null) {
                    caselle[r][c].setTessera(null);
                }
            }
        }
    }
	

    /**
     * Metodo helper per verificare se due connettori possono connettersi.
     */
    public static boolean possonoConnettersi(Connettore c1, Connettore c2) {
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
                if (caselle[r][c].getTessera() != null) {
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
                        planceNave.incrementaPilaScarti();
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
            if (isPosizioneValida(rigaDavanti, c) && caselle[rigaDavanti][c].getTessera() != null) {
                return false;
            }
        }
        
        // Cannone punta a EST (verso destra)
        if (cannone.getLatoDx() == Connettore.CANNONE || cannone.getLatoDx() == Connettore.CANNONEDOPPIO) {
            int colonnaDavanti = c + 1;
            if (isPosizioneValida(r, colonnaDavanti) && caselle[r][colonnaDavanti].getTessera() != null) {
                return false;
            }
        }
        
        // Cannone punta a SUD (verso il basso)
        if (cannone.getLatoDown() == Connettore.CANNONE || cannone.getLatoDown() == Connettore.CANNONEDOPPIO) {
            int rigaDavanti = r + 1;
            if (isPosizioneValida(rigaDavanti, c) && caselle[rigaDavanti][c].getTessera() != null) {
                return false;
            }
        }
        
        // Cannone punta a OVEST (verso sinistra)
        if (cannone.getLatoSx() == Connettore.CANNONE || cannone.getLatoSx() == Connettore.CANNONEDOPPIO) {
            int colonnaDavanti = c - 1;
            if (isPosizioneValida(r, colonnaDavanti) && caselle[r][colonnaDavanti].getTessera() != null) {
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
        if (isPosizioneValida(rigaDietro, c) && caselle[rigaDietro][c].getTessera() != null) {
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
    
    /**
     * Conta quanti connettori "esposti" ci sono sull'intera plancia.
     * Un connettore è considerato esposto SOLO se è di tipo legame
     * (SINGOLO, DOPPIO, UNIVERSALE) e punta verso una casella vuota 
     * o fuori dai limiti della plancia.
     * NON conta le connessioni illegali tra tessere.
     *
     * @param planceNave La plancia da analizzare.
     * @return Il numero totale di connettori esposti.
     */
    public static int contaConnettoriEsposti(PlanceNaveLivello1 planceNave) {
        Casella[][] caselle = planceNave.getCaselle();
        int contatoreEsposti = 0;

        int[] dr = {-1, 0, 1, 0}; 
        int[] dc = {0, 1, 0, -1};

        // Scansiona ogni casella della plancia
        for (int r = 0; r < PlanceNaveLivello1.getNumRighe(); r++) {
            for (int c = 0; c < PlanceNaveLivello1.getNumColonne(); c++) {
                
                if (caselle[r][c].getTessera() == null) {
                    continue; // Salta le caselle vuote
                }
                
                Tessera tesseraCorrente = caselle[r][c].getTessera();

                // Controlla tutti e 4 i lati della tessera corrente
                for (int i = 0; i < 4; i++) {
                    Connettore connettoreCorrente = getLato(tesseraCorrente, i);

                    // Ci interessa solo se il connettore è di tipo legame
                    if (isConnettoreDiLegame(connettoreCorrente)) {
                        int rigaVicino = r + dr[i];
                        int colonnaVicino = c + dc[i];

                        // Un connettore è esposto se il suo vicino è fuori dalla plancia
                        // O se il suo vicino è una casella non occupata.
                        if (!isPosizioneValida(rigaVicino, colonnaVicino) || 
                            caselle[rigaVicino][colonnaVicino].getTessera() == null) {
                            
                            contatoreEsposti++;
                        }
                    }
                }
            }
        }
        
        return contatoreEsposti;
    }

    /**
     * Metodo helper per ottenere il connettore di una tessera in una data direzione.
     * Usa la stessa convenzione del resto della classe.
     * 0=NORD, 1=EST, 2=SUD, 3=OVEST
     */
    public static Connettore getLato(Tessera t, int direzione) {
        switch (direzione) {
            case 0: return t.getLatoSup();
            case 1: return t.getLatoDx();
            case 2: return t.getLatoDown();
            case 3: return t.getLatoSx();
            default: throw new IllegalArgumentException("Direzione non valida: " + direzione);
        }
    }

    /**
     * Metodo helper per ottenere il connettore OPPOSsTO di una tessera rispetto 
     * alla direzione data. Es. se la direzione è NORD (0), restituisce il lato SUD (Down).
     */
    public static Connettore getLatoOpposto(Tessera t, int direzioneDalVicino) {
        switch (direzioneDalVicino) {
            case 0: // Il vicino è a NORD, quindi il suo lato di connessione è quello a SUD (Down)
                return t.getLatoDown();
            case 1: // Il vicino è a EST, il suo lato di connessione è OVEST (Sx)
                return t.getLatoSx();
            case 2: // Il vicino è a SUD, il suo lato di connessione è NORD (Sup)
                return t.getLatoSup();
            case 3: // Il vicino è a OVEST, il suo lato di connessione è EST (Dx)
                return t.getLatoDx();
            default: throw new IllegalArgumentException("Direzione non valida: " + direzioneDalVicino);
        }
    }
    
    
    
    //TODO - aggiungere javadoc
    public static  Posizione colpisciComponenteDaSopra(PlanceNaveLivello1 planceNave, int colonna) {
		Casella[][] caselle  = planceNave.getCaselle();
		//Shift della colonna per allinearla agli indici della nave
		colonna -= 5;
		//Fissata la colonna, scorro le righe per cercare componenti da colpire
		for(int i = 0; i < PlanceNaveLivello1.getNumRighe(); i++) {
			if(caselle[i][colonna].getTessera() != null) {
				return new Posizione(i, colonna);
			}
		}
		return null;
	}
	public static Posizione colpisciComponenteDaSinistra(PlanceNaveLivello1 planceNave, int riga) {
		Casella[][] caselle  = planceNave.getCaselle();
		//Shift della riga per allinearla agli indici della nave
		riga -= 5;
		//Fissata la riga, scorro le colonne per cercare componenti da colpire
		for(int j = 0; j < PlanceNaveLivello1.getNumColonne(); j++) {
			if(caselle[riga][j].getTessera() != null) {
				return new Posizione(riga, j);
			}
		}
		return null;
	}
	 public static Posizione colpisciComponenteDaSotto(PlanceNaveLivello1 planciaNave, int colonna) {
	        Casella[][] caselle = planciaNave.getCaselle();
	        // Shift della colonna per allinearla agli indici della nave
	        colonna -= 5;
	        
	        // il ciclo for parte dall'ultima riga e va verso la prima.
	        for (int i = PlanceNaveLivello1.getNumRighe() - 1; i >= 0; i--) {
	            if (caselle[i][colonna].getTessera() != null) {
	                // Trovato il primo componente lo restituisco.
	                return new Posizione(i, colonna);
	            }
	        }
	        return null; // Nessun componente trovato nella colonna.
	    }
	 public static Posizione colpisciComponenteDaDestra(PlanceNaveLivello1 planciaNave, int riga) {
	        Casella[][] caselle = planciaNave.getCaselle();
	        // Shift della riga per allinearla agli indici della nave
	        riga -= 5;
	        
	        // il ciclo for parte dall'ultima colonna e va verso la prima.
	        for (int j = PlanceNaveLivello1.getNumColonne() - 1; j >= 0; j--) {
	            if (caselle[riga][j].getTessera() != null) {
	                // Trovato il primo componente lo restituisco.
	                return new Posizione(riga, j);
	            }
	        }
	        return null; // Nessun componente trovato nella riga.
	    }
	 
    
    /**
     * Verifica se una tessera in una data posizione ha un lato liscio (NULLO)
     * esposto nella direzione da cui proviene la minaccia.
     *
     * @param nave La plancia della nave.
     * @param posColpita La posizione della tessera che è stata colpita.
     * @param provenienza La direzione da cui arriva il proiettile.
     * @return true se il lato colpito è liscio, false altrimenti.
     */
    public static boolean haLatoLiscioEsposto(PlanceNaveLivello1 nave, Posizione posColpita, Provenienza provenienza) {
        // Ottieni la tessera che si trova nella posizione colpita
        Tessera tesseraColpita = nave.getCaselle()[posColpita.getRiga()][posColpita.getColonna()].getTessera();
        
        // Se per qualche motivo non c'è una tessera, non può avere un lato liscio
        if (tesseraColpita == null) {
            return false;
        }

        Connettore latoColpito = null;
        
        // Determina quale lato della tessera è stato effettivamente colpito
        // in base alla provenienza del proiettile.
        switch (provenienza) {
            case SOPRA:
                latoColpito = tesseraColpita.getLatoSup();
                break;
            case SOTTO:
                latoColpito = tesseraColpita.getLatoDown();
                break;
            case DESTRA:
                latoColpito = tesseraColpita.getLatoDx();
                break;
            case SINISTRA:
                latoColpito = tesseraColpita.getLatoSx();
                break;
        }
        
        // La difesa ha successo se e solo se il lato colpito è di tipo NULLO.
        return latoColpito == Connettore.NULLO;
    }
    
    
    /**
     * Metodo principale che gestisce l'impatto di un meteorite su una nave.
     * Contiene tutta la logica di difesa e distruzione.
     *
     * @param nave La plancia della nave colpita.
     * @param meteorite L'oggetto meteorite.
     * @param posColpita La posizione del componente colpito.
     * @param inputOutput Per chiedere al giocatore se vuole usare lo scudo.
     * @return Un RisultatoImpatto che descrive l'esito.
     */
    public static RisultatoImpatto gestisciImpattoMeteorite(PlanceNaveLivello1 nave, Meteorite meteorite, Posizione posColpita, ConsoleIO inputOutput) {
        // Se non c'è una posizione, il meteorite ha mancato la nave.
        if (posColpita == null) {
            return RisultatoImpatto.MANCATO;
        }

        // Logica per meteorite PICCOLO
        if (meteorite.getDimensione() == Dimensione.PICCOLO) {
            // 1. Controlla se il lato colpito è un lato liscio
            if (haLatoLiscioEsposto(nave, posColpita, meteorite.getProvenienza())) {
                return RisultatoImpatto.SALVATO_DA_LATO_LISCIO;
            }
            // 2. Se non ha un lato liscio chiede se ci sono scudi per coprire e chiede se attivarli
            if (nave.utilizzoScudo(meteorite.getProvenienza()) && nave.haBatterie()) {
                boolean vuoleUsareScudo = inputOutput.chiediSeEseguireAzione("Un meteorite piccolo sta per colpire un lato non protetto. Vuoi usare 1 batteria per attivare lo scudo?");
                if (vuoleUsareScudo) {
                    nave.aggiungiBatterie(-1); // Consuma la batteria
                    return RisultatoImpatto.SALVATO_DA_SCUDO;
                }
            }
        } 
        // Logica per meteorite GROSSO
        else if (meteorite.getDimensione() == Dimensione.GROSSO) {
            // TODO: Aggiungere logica per chiedere al giocatore se vuole usare cannoni doppi (che consumano energia)
//            if (puoSparareAMeteoriteGrosso(nave, posColpita, meteorite.getProvenienza())) {
//                return RisultatoImpatto.SALVATO_DA_CANNONE;
//            }
        }

        // Se nessuna difesa ha funzionato, il componente viene distrutto.
        inputOutput.stampaMessaggio("COLPITO! Il componente in posizione " + posColpita + " è stato distrutto.");
        distruggiComponente(nave, posColpita);
        return RisultatoImpatto.DISTRUTTO;
    }

    //metodo per la rimozione del componente e la gestione degli orfani
    private static void distruggiComponente(PlanceNaveLivello1 nave, Posizione pos) {
        nave.getCaselle()[pos.getRiga()][pos.getColonna()].setTessera(null);
        nave.incrementaPilaScarti();
        // Dopo ogni distruzione, è FONDAMENTALE controllare se si sono create isole.
        gestisciRimozioneOrfani(nave);
    }


    // helper per trovare la linea colpita
    public static Posizione trovaComponenteColpito(PlanceNaveLivello1 nave, Provenienza provenienza, int lineaDiImpatto) {
        switch(provenienza) {
            case SOPRA: return colpisciComponenteDaSopra(nave, lineaDiImpatto);
            case SOTTO: return colpisciComponenteDaSotto(nave, lineaDiImpatto);
            case DESTRA: return colpisciComponenteDaDestra(nave, lineaDiImpatto);
            case SINISTRA: return colpisciComponenteDaSinistra(nave, lineaDiImpatto);
            default: return null;
        }
    }
    
    
    
    //logica di rimozione degli astronauti - prende la prima cabina che trova scorrendo dall'alto e glieli toglie
    public static void rimozioneAstronauti(PlanceNaveLivello1 planceNave) {
    	for(int i = 0; i < PlanceNaveLivello1.getNumRighe(); i++) {
			for(int j = 0; j < PlanceNaveLivello1.getNumColonne(); j++) {
				if(planceNave.caselle[i][j].getTessera() instanceof Cabina cabina) {
				cabina.setEquipaggio(0);
				}
			}
    	}
    }
    
}
