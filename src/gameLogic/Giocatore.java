package gameLogic;

import plance.PlanceNaveLivello1;

/**
*la classe Giocatore rappresenta un partecipante al gioco, con la sua nave e i suoi attributi.
*/
public class Giocatore {
    // Attributi
    private final Colore colore;
    private PlanceNaveLivello1 planceNave;
    private int creditistellari;
    private boolean haAbbandonato;
    private boolean assemblaggioTerminato;

	/**
	*costruttore della classe Giocatore.
	*@param colore il colore univoco del giocatore.
	*@param planceNave la plancia della nave associata al giocatore.
	*/
    public Giocatore(Colore colore, PlanceNaveLivello1 planceNave) {
    	this.colore= colore;
        this.planceNave = planceNave;
        this.creditistellari = 0;
        this.haAbbandonato = false;  
    	if(planceNave == null) {
    		throw new NullPointerException("Il giocatore non può avere una nave nulla");
    	}
    	if(colore == null) {
    		throw new NullPointerException("Il giocatore non può avere un colore nullo");
    	}     
    }

    /**
    *restituisce il colore del giocatore.
    *@return il colore del giocatore.
    */
    public Colore getColore() {
        return colore;
    }

    /**
    *restituisce la plancia della nave del giocatore.
    *@return la plancia della nave.
    */
    public PlanceNaveLivello1 getPlanceNave() {
        return planceNave;
    }

    /**
    *restituisce il numero di crediti stellari del giocatore.
    *@return i crediti stellari.
    */
    public int getCreditiStellari() {
        return creditistellari;
    }
    
    /**
    *indica se il giocatore ha abbandonato la partita.
    *@return true se il giocatore ha abbandonato, false altrimenti.
    */
    public boolean getHaAbbandonato() {
        return haAbbandonato;
    }
    
    /**
    *indica se il giocatore ha terminato la fase di assemblaggio.
    *@return true se l'assemblaggio è terminato, false altrimenti.
    */
    public boolean isAssemblaggioTerminato() {
		return assemblaggioTerminato;
	}

    /**
    *imposta la plancia della nave del giocatore.
    *@param stato la nuova plancia della nave.
    */
    public void setPlanceNave(PlanceNaveLivello1 stato) {
        this.planceNave = stato;
    }
    
    /**
    *imposta lo stato di assemblaggio del giocatore come terminato.
    */
    public void terminaAssemblaggio() {
		this.assemblaggioTerminato = true;
	}
    
    /**
    *aggiunge una quantità di crediti al totale del giocatore.
    *@param quantita il numero di crediti da aggiungere.
    */
     public void aggiungiCrediti(int quantita) {
        this.creditistellari += quantita;
    }

    /**
    *rimuove una quantità di crediti dal totale del giocatore, se disponibili.
    *@param quantita il numero di crediti da rimuovere.
    */
    public void rimuoviCrediti(int quantita) {
        if(creditistellari >= quantita){
            this.creditistellari -= quantita; 
        }
    }
 
    /**
    *imposta lo stato del giocatore come "abbandonato".
    */
    public void abbandonaPartita() {
    	haAbbandonato = true;
    }

}