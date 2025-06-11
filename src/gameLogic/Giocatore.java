package gameLogic;

import plance.PlanceNaveLivello1;

/**
 * Rappresenta un giocatore all'interno del gioco.
 * Mantiene informazioni sullo stato del giocatore, come il colore, la plancia della nave,
 * i crediti e lo stato di partecipazione alla partita.
 */
public class Giocatore {

    /** Il colore unico che identifica il giocatore. */
    private final Colore colore;
    
    /** La plancia personale della nave del giocatore. */
    private PlanceNaveLivello1 planceNave;
    
    /** I crediti stellari accumulati dal giocatore. */
    private int creditistellari;
    
    /** Flag che indica se il giocatore ha abbandonato la partita. */
    private boolean haAbbandonato;
    
    /** Flag che indica se il giocatore ha terminato la fase di assemblaggio. */
    private boolean assemblaggioTerminato;

	/**
     * Costruttore per creare un nuovo giocatore.
     * @param colore Il colore assegnato al giocatore.
     * @param planceNave La plancia nave iniziale del giocatore.
     * @throws NullPointerException se il colore o la plancia nave sono nulli.
     */
    public Giocatore(Colore colore, PlanceNaveLivello1 planceNave) {
    	if (planceNave == null) {
    		throw new NullPointerException("Il giocatore non può avere una nave nulla");
    	}
    	if (colore == null) {
    		throw new NullPointerException("Il giocatore non può avere un colore nullo");
    	}
    	
    	this.colore = colore;
        this.planceNave = planceNave;
        this.creditistellari = 0;
        this.haAbbandonato = false;
        this.assemblaggioTerminato = false; // Inizializzato a false di default
    }

    /**
     * Restituisce il colore del giocatore.
     * @return Il colore del giocatore.
     */
    public Colore getColore() {
        return colore;
    }

    /**
     * Restituisce la plancia della nave del giocatore.
     * @return La plancia della nave.
     */
    public PlanceNaveLivello1 getPlanceNave() {
        return planceNave;
    }

    /**
     * Restituisce il numero di crediti stellari del giocatore.
     * @return I crediti stellari.
     */
    public int getCreditiStellari() {
        return creditistellari;
    }
    
    /**
     * Verifica se il giocatore ha abbandonato la partita.
     * @return true se il giocatore ha abbandonato, altrimenti false.
     */
    public boolean getHaAbbandonato() {
        return haAbbandonato;
    }
    
    /**
     * Verifica se il giocatore ha terminato la fase di assemblaggio.
     * @return true se l'assemblaggio è terminato, altrimenti false.
     */
    public boolean isAssemblaggioTerminato() {
		return assemblaggioTerminato;
	}

    /**
     * Imposta o aggiorna la plancia della nave del giocatore.
     * @param stato La nuova plancia della nave.
     */
    public void setPlanceNave(PlanceNaveLivello1 stato) {
        this.planceNave = stato;
    }
    
    /**
     * Segna che il giocatore ha completato la fase di assemblaggio.
     */
    public void terminaAssemblaggio() {
		this.assemblaggioTerminato = true;
	}
    
    // TODO - valutare se è necessario un setter:
    // public void setPilaScarti(int pilaScarti) {
    // 	   this.pilaScarti = pilaScarti;
    // }
    
    /**
     * Aggiunge una quantità di crediti al totale del giocatore.
     * @param quantita I crediti da aggiungere (può essere un valore negativo).
     */
    public void aggiungiCrediti(int quantita) {
        this.creditistellari += quantita;
    }

    /**
     * Rimuove una quantità di crediti dal totale, solo se il giocatore ne ha abbastanza.
     * @param quantita I crediti da rimuovere.
     */
    public void rimuoviCrediti(int quantita) {
        if (creditistellari >= quantita) {
            this.creditistellari -= quantita; 
        }
    }
 
    /**
     * Imposta lo stato del giocatore come "abbandonato".
     */
    public void abbandonaPartita() {
    	haAbbandonato = true;
        // TODO - va gestito nella classe ConsoleIO
    	// System.out.println("Il giocatore ha deciso di arrendersi.");
    }
}