package gameLogic;

import java.util.LinkedList;
import java.util.List;
//import java.util.Queue;
//
import carteAvventura.Carta;
import dadiEClessidra.Clessidra;
import dadiEClessidra.Dadi;
import plance.PlanceNaveLivello1;
//import plance.PlanceVolo;
//import tessere.Tessera;

public class Gioco {
	private final Giocatore[] giocatori;
//    private Deque<Tessera> pilaDiTessere;
    private Clessidra clessidra;
//    private List<PlanceVolo> planceVolo; // Una per ogni giocatore
    private Dadi dadi; // Se hai una classe Dadi
//    private List<Carta> mazzoDiCarte[];
    private final LivelloPartita livelloPartita;
//    private final int DIM_MAZZI = 4;
    
    public Gioco(int numGiocatori, Colore[] coloriGiocatori, LivelloPartita livelloPartita) {
        this.livelloPartita = livelloPartita;
        
        //inizializzo l'array di giocatori
        this.giocatori = Gioco.creaGiocatori(numGiocatori, livelloPartita.getNumeroLivello(), coloriGiocatori);
        
        this.clessidra = new Clessidra(20, livelloPartita);
        this.dadi = new Dadi();
        //TODO - implementazioni mazzi di carte
        
        
        // TODO - fai la funzione che in base al livello crea i mazzi di carte (magari in setupPartita)

    }
	
   // TODO - valuta il lancio di un'eccezione per questa funzione
    private static Giocatore[] creaGiocatori(int numGiocatori, int livelloPartita, Colore[] coloriGiocatori) {
    	Giocatore[] giocatori = new Giocatore[numGiocatori];
    	
    	//creazione dei giocatori in base al livello
    	switch(livelloPartita) {
    	case 1: {
	    		for (int i = 0; i < numGiocatori; i++) {
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
    	for (int i = 0; i < numGiocatori; i++) {
            giocatori[i] = new Giocatore(coloriGiocatori[i], new PlanceNaveLivello1());
        }
    	return giocatori;
    }
//    private static LinkedList<Carta>[] creaMazzoDiCarte(int livelloPartita, int dimensioneMazzi){
//    	LinkedList<Carta> mazzoDiCarte = new LinkedList<Carta>[dimensioneMazzi]();
//    	switch(livelloPartita) {
//    	case 1: {
//	    		for(int i = 0; i < dimensioneMazzi, i++)
//	    		{
//	    			
//	    		}
//    		return mazzoDiCarte;
//	    	}
//
//    	case 2: System.out.println("Futura implementazione..."); break;
//    	//case 3: System.out.println("Futura implementazione...");break;
//    	//case 4: System.out.println("Futura implementazione...");break;
//    	
//    	default: System.out.println("Futura implementazione...");
//    	}
//    	
//    	
//    	
//    	return mazzoDiCarte;
//    }
	public void play() {    
		
	}
}
