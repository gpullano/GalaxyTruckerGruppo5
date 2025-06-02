package gameLogic;

import plance.PlanceNaveLivello1;

public class Giocatore {
    // Attributi
    private final Colore colore;
    private PlanceNaveLivello1 planceNave;
    private int creditistellari;
    private boolean haAbbandonato;
    private int pilaScarti;
    private boolean assemblaggioTerminato;


	// Costruttore
    public Giocatore(Colore colore, PlanceNaveLivello1 planceNave) {
    	this.colore= colore;
        this.planceNave = planceNave;
        this.creditistellari = 0;
        this.haAbbandonato = false;
        this.pilaScarti = 0;   
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
    
    public int getPilaScarti() {
		return pilaScarti;
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
    
 

//	TODO - valutare se è necessario un setter:
//	public void setPilaScarti(int pilaScarti) {
//		this.pilaScarti = pilaScarti;
//	}
    
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
        // TODO - va gestito nella classe ConsoleIO
    	// System.out.println("Il giocatore ha deciso di arrendersi.");
    }
    
    public void incrementaPilaScarti() {
    	this.pilaScarti++;
    }

}
