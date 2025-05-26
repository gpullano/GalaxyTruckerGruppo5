package gameLogic;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//import java.util.Queue;
//
import carteAvventura.Carta;
import carteAvventura.GeneratoreCarte;
import carteAvventura.Mazzetto;
import dadiEClessidra.Clessidra;
import dadiEClessidra.Dadi;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
//import plance.PlanceVolo;
//import tessere.Tessera;
import tessere.Tessera;

public class Gioco {
	private final Giocatore[] giocatori;
    private Deque<Tessera> mucchioTessere;
//    TODO - implementa il mucchio di tessere viste
//    private List<Tessera> tessereViste;
    private Clessidra clessidra;
    private PlanceVolo planceVolo;
    private Dadi dadi; // Se hai una classe Dadi
    private List<Carta>[] mazzoDiCarte;
    private final LivelloPartita livelloPartita;
    private final Mazzetto[] mazzettiDiCarte;
    private static final int N_MAZZETTI = 4;
    private static final int DIM_MAZZETTO = 2;
    private final int N_GIOCATORI;
    private static final int N_TESSERE = 156;
    
    
    public Gioco(int numGiocatori, Colore[] coloriGiocatori, LivelloPartita livelloPartita) {
        this.livelloPartita = livelloPartita;
        this.N_GIOCATORI = numGiocatori;
        this.clessidra = new Clessidra(20, livelloPartita);
        this.dadi = new Dadi();
        this.planceVolo = new PlanceVolo(5, 8, N_GIOCATORI, coloriGiocatori);
        
        //inizializzo l'array di giocatori
        this.giocatori = creaGiocatori(coloriGiocatori);
        
        //creazione mazzi di carte
        this.mazzettiDiCarte = creaMazzetti();
        
        //creazione pila di tessere
        this.mucchioTessere = creaMucchioTessere();
        
    }
	
   // TODO - valuta il lancio di un'eccezione per questa funzione
    private Giocatore[] creaGiocatori(Colore[] coloriGiocatori) {
    	Giocatore[] giocatori = new Giocatore[N_GIOCATORI];
    	
    	//creazione dei giocatori in base al livello
    	switch(livelloPartita.getNumeroLivello()) {
    	case 1: {
	    		for (int i = 0; i < N_GIOCATORI; i++) {
	                giocatori[i] = new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1());
	            }
	    		return giocatori;
	    	}

    	case 2: System.out.println("Futura implementazione..."); break;
    	//case 3: System.out.println("Futura implementazione...");break;
    	//case 4: System.out.println("Futura implementazione...");break;
    	
    	default: System.out.println("Futura implementazione...");
    	}
    	
    	// nel caso vengano scelti livelli diversi dal livello 1:
    	for (int i = 0; i < N_GIOCATORI; i++) {
            giocatori[i] = new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1());
        }
    	return giocatori;
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
    
    private List<Carta> creaMazzoUnico(){
    	List<Carta> mazzoUnico = new LinkedList<>(); // Inizializza il nuovo mazzo unico

        if (this.mazzettiDiCarte != null) {
            for (Mazzetto mazzettoCorrente : this.mazzettiDiCarte) {
                if (mazzettoCorrente != null && mazzettoCorrente.getCarte() != null) {
                    // Aggiunge tutte le carte del mazzettoCorrente al mazzoUnico
                    mazzoUnico.addAll(mazzettoCorrente.getCarte());
                }
            }
        }
        return mazzoUnico;
    }
   
    private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
//    		TODO - finisci implementazione
    		mucchio.add(null);
    	}
    	
		return mucchio; 	
    }

	public void play() {    
		
		
		// dopo aver completato l'assemblaggio delle navi
		List<Carta> mazzoDiCarte = creaMazzoUnico();
		Collections.shuffle(mazzoDiCarte);
		
	}
}
