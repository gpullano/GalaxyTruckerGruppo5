package gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import carteAvventura.Carta;
import carteAvventura.GeneratoreCarte;
import carteAvventura.Mazzetto;
import dadiEClessidra.Clessidra;
import dadiEClessidra.Dadi;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;

public class Gioco {
	private final List<Giocatore> giocatori;
    private PlanceVolo planceVolo; 
    private List<Carta> mazzoDiCarte;
    private final LivelloPartita livelloPartita;
    private final Mazzetto[] mazzettiDiCarte;
    private static final int N_MAZZETTI = 4;
    private static final int DIM_MAZZETTO = 2;
    private final int N_GIOCATORI;
    private final ConsoleIO inputOutput;
    
    
    public Gioco(int numGiocatori, Colore[] coloriGiocatori, LivelloPartita livelloPartita, ConsoleIO inputOutput) {
        this.livelloPartita = livelloPartita;
        this.N_GIOCATORI = numGiocatori;
        this.planceVolo = new PlanceVolo(5, 8, N_GIOCATORI, coloriGiocatori);
        
        //inizializzo l'array di giocatori
        this.giocatori = creaGiocatori(coloriGiocatori);
        
        //creazione mazzi di carte
        this.mazzettiDiCarte = creaMazzetti();
        this.inputOutput = inputOutput;
        
    }
	
    
    private List<Giocatore> creaGiocatori(Colore[] coloriGiocatori) {
    	List<Giocatore> giocatoriCreati = new ArrayList<>();
    	
    	//creazione dei giocatori in base al livello
    	switch(livelloPartita) {
    	case LIVELLO1: {
	    		for (int i = 0; i < N_GIOCATORI; i++) {
	    			giocatoriCreati.add(new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1(coloriGiocatori[i])));
	            }
	    		return giocatoriCreati;
	    	}

    	case LIVELLO2: System.out.println("Futura implementazione..."); break;
    	//case LIVELLO3: System.out.println("Futura implementazione...");break;
    	//case TRASVOLATA_INTERGALATTICA: System.out.println("Futura implementazione...");break;
    	
    	default: System.out.println("Futura implementazione...");
    	}
    	
    	// nel caso vengano scelti livelli diversi dal livello 1:
    	for (int i = 0; i < N_GIOCATORI; i++) {
    		giocatoriCreati.add(new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1(coloriGiocatori[i])));
        }
		return giocatoriCreati;
    }

    private Mazzetto[] creaMazzetti() {
    	Mazzetto[] mazzetti = new Mazzetto[N_MAZZETTI];
    	for(int i = 0; i < N_MAZZETTI; i++) {
    		mazzetti[i] = new Mazzetto();
    		for(int j = 0; j < DIM_MAZZETTO; j++) {
    			mazzetti[i].aggiungiCarta(GeneratoreCarte.generaCartaCasuale(livelloPartita.getNumeroLivello()));
    		}
    	}
    	return mazzetti;
    }
   

	public void play() {    
		
		// 1. Assemblaggio
		FaseAssemblaggio faseAssemblaggio = new FaseAssemblaggio(this.giocatori, this.planceVolo, this.mazzettiDiCarte, this.inputOutput);
	    faseAssemblaggio.eseguiFase();
	    
	    // 2. Preparazione al decollo
	    FasePreparazioneDecollo fasePreparazioneDecollo = new FasePreparazioneDecollo(this.giocatori, this.inputOutput, this.planceVolo);
	    fasePreparazioneDecollo.eseguiFase();
	    mazzoDiCarte = fasePreparazioneDecollo.creaMazzoUnico(this.mazzettiDiCarte);
	    Collections.shuffle(mazzoDiCarte);
	    
	    // 3. Il volo
	    FaseVolo faseVolo = new FaseVolo(this.giocatori, this.inputOutput, this.planceVolo, this.mazzoDiCarte);
	    faseVolo.eseguiFase();
	    
	    // 4. Fine del viaggio
	    FaseFineDelViaggio faseFineDelViaggio = new FaseFineDelViaggio(this.giocatori, this.inputOutput, this.planceVolo);
	    faseFineDelViaggio.eseguiFase();
		
	}
}
