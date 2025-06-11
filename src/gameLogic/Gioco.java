package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import carteAvventura.Carta;
import carteAvventura.GeneratoreCarte;
import carteAvventura.Mazzetto;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;

/**
 * Rappresenta il flusso principale del gioco "Galaxy Trucker".
 * Inizializza la partita, i giocatori, le plance e gestisce l'esecuzione delle diverse fasi.
 */
public class Gioco {
	
	/** La lista di tutti i giocatori partecipanti. */
	private final List<Giocatore> giocatori;
    
    /** La plancia di volo comune a tutti i giocatori. */
    private PlanceVolo planceVolo; 
    
    /** Il mazzo unico di carte avventura per la fase di volo. */
    private List<Carta> mazzoDiCarte;
    
    /** Il livello di difficoltà della partita corrente. */
    private final LivelloPartita livelloPartita;
    
    /** I mazzetti di carte avventura utilizzati durante la fase di assemblaggio. */
    private final Mazzetto[] mazzettiDiCarte;
    
    private static final int N_MAZZETTI = 4;
    private static final int DIM_MAZZETTO = 2;
    
    /** Il numero totale di giocatori. */
    private final int N_GIOCATORI;
    
    /** L'oggetto per la gestione dell'input/output da console. */
    private final ConsoleIO inputOutput;
    
    /**
     * Costruttore per inizializzare una nuova partita di Galaxy Trucker.
     * Crea i giocatori, la plancia di volo e i mazzetti di carte iniziali.
     * @param numGiocatori Il numero di giocatori (da 2 a 4).
     * @param coloriGiocatori Gli array di colori scelti dai giocatori.
     * @param livelloPartita Il livello di difficoltà scelto.
     * @param inputOutput L'oggetto ConsoleIO per l'interazione.
     */
    public Gioco(int numGiocatori, Colore[] coloriGiocatori, LivelloPartita livelloPartita, ConsoleIO inputOutput) {
        this.livelloPartita = livelloPartita;
        this.N_GIOCATORI = numGiocatori;
        this.planceVolo = new PlanceVolo(coloriGiocatori);
        
        this.giocatori = creaGiocatori(coloriGiocatori);
        
        this.mazzettiDiCarte = creaMazzetti();
        this.inputOutput = inputOutput;
    }
	
    /**
     * Crea le istanze dei giocatori con le rispettive plance nave in base al livello di partita.
     * @param coloriGiocatori L'array dei colori scelti per ogni giocatore.
     * @return Una lista di oggetti Giocatore.
     */
    private List<Giocatore> creaGiocatori(Colore[] coloriGiocatori) {
    	List<Giocatore> giocatoriCreati = new ArrayList<>();
    	
    	switch (livelloPartita) {
	    	case LIVELLO1: {
	    		for (int i = 0; i < N_GIOCATORI; i++) {
	    			giocatoriCreati.add(new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1(coloriGiocatori[i])));
	            }
	    		return giocatoriCreati;
	    	}
    		// TODO: Implementazioni per LIVELLO2, LIVELLO3, TRASVOLATA_INTERGALATTICA
	    	case LIVELLO2: 
	    		System.out.println("Futura implementazione per Livello 2..."); 
	    		break;
	    	// case LIVELLO3: 
	    	// 	System.out.println("Futura implementazione per Livello 3...");
	    	// 	break;
	    	// case TRASVOLATA_INTERGALATTICA: 
	    	// 	System.out.println("Futura implementazione per Trasvolata Intergalattica...");
	    	// 	break;
	    	default: 
	    		System.out.println("Livello non riconosciuto, usando implementazione di default...");
	    	}
    	
    	// Ritorno di default o per livelli non implementati: crea giocatori con PlanceNaveLivello1
    	for (int i = 0; i < N_GIOCATORI; i++) {
    		giocatoriCreati.add(new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1(coloriGiocatori[i])));
        }
		return giocatoriCreati;
    }

    /**
     * Crea e popola i mazzetti di carte avventura iniziali.
     * @return Un array di oggetti Mazzetto.
     */
    private Mazzetto[] creaMazzetti() {
    	Mazzetto[] mazzetti = new Mazzetto[N_MAZZETTI];
    	for (int i = 0; i < N_MAZZETTI; i++) {
    		mazzetti[i] = new Mazzetto();
    		for (int j = 0; j < DIM_MAZZETTO; j++) {
    			mazzetti[i].aggiungiCarta(GeneratoreCarte.generaCartaCasuale(livelloPartita.getNumeroLivello()));
    		}
    	}
    	return mazzetti;
    }
   
    /**
     * Avvia e gestisce il flusso principale del gioco, passando attraverso le diverse fasi.
     */
	public void play() {    
		// 1. Fase di Assemblaggio
		FaseAssemblaggio faseAssemblaggio = new FaseAssemblaggio(this.giocatori, this.planceVolo, this.mazzettiDiCarte, this.inputOutput);
	    faseAssemblaggio.eseguiFase();
	    
	    // 2. Fase di Preparazione al Decollo
	    FasePreparazioneDecollo fasePreparazioneDecollo = new FasePreparazioneDecollo(this.giocatori, this.inputOutput, this.planceVolo);
	    fasePreparazioneDecollo.eseguiFase();
	    
        // Crea il mazzo unico di carte avventura e lo mescola
	    mazzoDiCarte = fasePreparazioneDecollo.creaMazzoUnico(this.mazzettiDiCarte);
	    Collections.shuffle(mazzoDiCarte);
	    
	    // 3. Fase di Volo
	    FaseVolo faseVolo = new FaseVolo(this.giocatori, this.inputOutput, this.planceVolo, this.mazzoDiCarte);
	    faseVolo.eseguiFase();
	    
	    // 4. Fase di Fine del Viaggio
	    FaseFineDelViaggio faseFineDelViaggio = new FaseFineDelViaggio(this.giocatori, this.inputOutput, this.planceVolo);
	    faseFineDelViaggio.eseguiFase();
	}
}