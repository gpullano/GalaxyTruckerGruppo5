package gameLogic;

import plance.PlanceNaveLivello1;

public class Giocatore {
    // Attributi
    private final Colore colore;
    private PlanceNaveLivello1 planceNave;
    private int creditistellari;
    private boolean haAbbandonato;
    private boolean assemblaggioTerminato;


	// Costruttore
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

    // Getters
    public Colore getColore() {
        return colore;
    }

    public PlanceNaveLivello1 getPlanceNave() {
        return planceNave;
    }

    public int getCreditiStellari() {
        return creditistellari;
    }
    
    public boolean getHaAbbandonato() {
        return haAbbandonato;
    }
    
    public boolean isAssemblaggioTerminato() {
		return assemblaggioTerminato;
	}

    // Setters

    public void setPlanceNave(PlanceNaveLivello1 stato) {
        this.planceNave = stato;
    }
    
    public void terminaAssemblaggio() {
		this.assemblaggioTerminato = true;
	}
    
 
    
    //metodi
     public void aggiungiCrediti(int quantita) {
        this.creditistellari += quantita;
    }

    public void rimuoviCrediti(int quantita) {
        if(creditistellari >= quantita){
            this.creditistellari -= quantita; 
        }
    }
 
    public void abbandonaPartita() {
    	haAbbandonato = true;
    }

}
