package gameLogic;

import plance.PlanceNave;

public class Giocatore {
    // Attributi
    private Colore colore;
    private PlanceNave stato;
    private int creditistellari;
    private boolean haAbbandonato;
    private int pilaScarti;
   

	// Costruttore
    public Giocatore(Colore colore, PlanceNave stato ) {
    	this.colore= colore;
        this.stato = stato;
        this.creditistellari = 0;
        this.haAbbandonato = false;
        this.pilaScarti = 0;   
        
    	if(stato == null) {
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

    public PlanceNave getStato() {
        return stato;
    }

    public int getCreditiStellari() {
        return creditistellari;
    }
    
    public boolean getHaAbbandonato() {
        return haAbbandonato;
    }
    
    public int getPilaScarti() {
		return pilaScarti;
	}

    // Setters

    public void setStato(PlanceNave stato) {
        this.stato = stato;
    }

    public void setColore(Colore colore) {
        this.colore = colore;
    }
 

//	valutare se è necessario un setter:
//	public void setPilaScarti(int pilaScarti) {
//		this.pilaScarti = pilaScarti;
//	}
    
    //metodi
     public void aggiungiCrediti(int quantita) {
        this.creditistellari += quantita;
    }

    public void rimuoviCrediti(int quantita) {
        if(creditistellari>=quantita){
            this.creditistellari -=quantita; 
        }
    }
 
    public void abbandonaPartita() {
    	haAbbandonato = true;
        System.out.println("Il giocatore ha deciso di arrendersi.");
    }
    
    public void incrementaPilaScarti() {
    	this.pilaScarti++;
    }
}
