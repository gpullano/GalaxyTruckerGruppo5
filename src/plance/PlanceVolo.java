package plance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import gameLogic.Colore;


public class PlanceVolo {
	
	private Cella[] celle;
	private List<PosizioneGiocatore> posizioniGiocatori; // Modificato da array a List
	private static final int LUNGHEZZA_PERCORSO = 18;
	
	public PlanceVolo(Colore[] colori) {
		this.celle = new Cella[LUNGHEZZA_PERCORSO];
		
		for(int r = 0; r < LUNGHEZZA_PERCORSO; r++) {
			this.celle[r] = new Cella(' ');
		}

		// Inizializza la lista e la popola con le posizioni iniziali dei giocatori
		this.posizioniGiocatori = new ArrayList<>();
		for (Colore colore: colori) {
		    this.posizioniGiocatori.add(new PosizioneGiocatore(0, 0, colore));
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
	    this.posizioniGiocatori.sort(new Comparator<PosizioneGiocatore>() {
	        
	        @Override
	        public int compare(PosizioneGiocatore p1, PosizioneGiocatore p2) {
	            // Confronta prima i giri.
	            int confrontoGiro = Integer.compare(p2.getGiro(), p1.getGiro());
	            
	            // Se i giri sono diversi, abbiamo già il nostro risultato.
	            if (confrontoGiro != 0) {
	                return confrontoGiro;
	            }
	            
	            // Se i giri sono uguali, confronto la posizione sulla plancia.
	            return Integer.compare(p2.getPosizione(), p1.getPosizione());
	        }
	    });
	}
	
	/**
	 * Rimuove un giocatore (e la sua posizione) dalla plancia di volo,
	 * cercandolo tramite il suo colore.
	 * Questa versione non usa Iterator ed è sicura per la rimozione.
	 *
	 * @param colore Il colore del giocatore da rimuovere.
	 * @return true se il giocatore è stato trovato e rimosso, false altrimenti.
	 */
	public boolean rimuoviGiocatore(Colore colore) {
	    // Iteriamo sulla lista all'indietro per evitare problemi con gli indici
	    // quando rimuoviamo un elemento.
	    for (int i = this.posizioniGiocatori.size() - 1; i >= 0; i--) {
	        
	        // Otteniamo l'elemento alla posizione corrente
	        PosizioneGiocatore pos = this.posizioniGiocatori.get(i);
	        
	        // Controlliamo se è il giocatore che stiamo cercando
	        if (pos.getColore() == colore) {
	            
	            // Trovato! Lo rimuoviamo usando il suo indice.
	            this.posizioniGiocatori.remove(i);
	            
	            // Il nostro lavoro è finito, restituiamo true.
	            return true;
	        }
	    }
	    
	    // Se il loop finisce, significa che non abbiamo trovato il giocatore.
	    return false;
	}


	public Cella[] getCelle() {
		return celle;
	}

	public void setCelle(Cella[] cella) {
		this.celle = cella;
	}
	
	public int getLunghezzaPercorso() {
		return LUNGHEZZA_PERCORSO;
	}
}