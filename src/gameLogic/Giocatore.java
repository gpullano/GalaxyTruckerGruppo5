package gameLogic;

import plance.PlanceNave;

public class Giocatore {
	public class giocatore {
    // Attributi
    private Colore colore;
    private PlanceNave stato;
    private int creditistellari;
//	la posizione la assegnamo alla plancia volo
//    private int posizione;
    private boolean haAbbandonato;
    public int pilaScarti;
   

	// Costruttore
    public giocatore(Colore colore, PlanceNave stato ) {
    	if(stato == null) {
    		throw new NullPointerException("Il giocatore non può avere una nave nulla");
    	}
    	if(colore == null) {
    		throw new NullPointerException("Il giocatore non può avere un colore nullo");
    	}
    	
        this.colore= colore;
        this.stato = stato;
        this.creditistellari = 0;
//        this.posizione = 0;
        this.haAbbandonato = false;
        this.pilaScarti = 0;        
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

//    public int getPosizione() {
//        return posizione;
//    }
    
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


//    public void setPosizione(int posizione) {
//        this.posizione = posizione;
//    }
   

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

//    public void avanzaPosizione() {
//        if(posizione<20){
//            posizione++; 
//        }
//    }
 
    public void abbandonaPartita() {
    	haAbbandonato = true;
        System.out.println("Il giocatore ha deciso di arrendersi.");
    }
    
    public void incrementaPilaScarti() {
    	this.pilaScarti++;
    }
}

	
}
