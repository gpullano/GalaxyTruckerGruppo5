package plance;

import java.util.ArrayList;
import java.util.List;
import gameLogic.Colore;


public class PlanceVolo {
	
	private Cella[][] cella;
	private List<PosizioneGiocatore> posizioniGiocatori; // Modificato da array a List
	private static final int LUNGHEZZA_PERCORSO = 18;
	
	public PlanceVolo(int riga, int colonna, Colore[] colori) {
		this.cella = new Cella[riga][colonna];
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				this.cella[r][c] = new Cella(' ');
			}
		}

		// Inizializza la lista e la popola con le posizioni iniziali dei giocatori
		this.posizioniGiocatori = new ArrayList<>();
		for (Colore colore: colori) {
		    this.posizioniGiocatori.add(new PosizioneGiocatore(0, 0, 0, colore));
		}
	}
	
	public void percorso() {
		int righe = cella.length;
		int colonne = cella[0].length;
		for(int c = 1; c < colonne - 1; c++) {//▶
			cella[0][c] = new Cella('→');
			cella[0][1] = new Cella('4');
			cella[0][2] = new Cella('3');
			cella[0][3] = new Cella('2');
			cella[0][5] = new Cella('1');
			
		}
        for(int r = 1; r < righe - 1; r++) {//▼
			
			cella[r][colonne - 1] = new Cella('↓');
			
		}
        
        for(int c = colonne - 2; c > 0;c--) {//◀
        	cella[righe - 1][c] = new Cella('←');
        }
        
        for(int r = righe - 2; r > 0; r--) {//▲
        	cella[r][0] = new Cella('↑');
        }
		
    }
	
	/**
	 * Restituisce la lista delle posizioni dei giocatori.
	 * @return Una List<PosizioneGiocatore>.
	 */
	public List<PosizioneGiocatore> getPosizioneGiocatori() {
		return this.posizioniGiocatori;
	}

    /**
     * Trova e restituisce l'oggetto PosizioneGiocatore associato a un dato colore.
     * @param colore Il colore del giocatore da cercare.
     * @return L'oggetto PosizioneGiocatore, o null se non trovato.
     */
    public PosizioneGiocatore getPosizioneDi(Colore colore) {
        for (PosizioneGiocatore pos : this.posizioniGiocatori) {
            if (pos.getColore() == colore) {
                return pos;
            }
        }
        return null;
    }
	
	public void stampaGiocatori() {
		for (PosizioneGiocatore p : posizioniGiocatori ) {
			System.out.println(p);
		}
	}
	
	/**
	 * Ordina la lista dei giocatori dalla prima all'ultima posizione.
	 * Il giocatore più avanti (giro maggiore, o posizione maggiore a parità di giro)
	 * si troverà all'indice 0 della lista.
	 */
	public void ordinaGiocatoriPerPosizione() {
	    this.posizioniGiocatori.sort((p1, p2) -> {
	        // Confronta prima i giri. L'ordine è decrescente (p2 vs p1).
	        int confrontoGiro = Integer.compare(p2.getGiro(), p1.getGiro());
	        if (confrontoGiro != 0) {
	            return confrontoGiro;
	        }
	        // Se i giri sono uguali, confronta la posizione, sempre in ordine decrescente.
	        return Integer.compare(p2.getPosizione(), p1.getPosizione());
	    });
	}


	public Cella[][] getCella() {
		return cella;
	}

	public void setCella(Cella[][] cella) {
		this.cella = cella;
	}
	
	public int getLunghezzaPercorso() {
		return LUNGHEZZA_PERCORSO;
	}
}