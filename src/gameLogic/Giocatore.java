package gameLogic;

import plance.PlanceNave;

public class Giocatore {
    // Attributi
    private final Colore colore;
    private PlanceNave planceNave;
    private int creditistellari;
    private boolean haAbbandonato;
    private int pilaScarti;
    private boolean assemblaggioTerminato;


	// Costruttore
    public Giocatore(Colore colore, PlanceNave planceNave) {
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

    public PlanceNave getPlanceNave() {
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

    public void setPlanceNave(PlanceNave stato) {
        this.planceNave = stato;
    }
    
    public void setAssemblaggioTerminato(boolean assemblaggioTerminato) {
		this.assemblaggioTerminato = assemblaggioTerminato;
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

	public int ContaPotenzaMotori() {
		// TODO da implementare
		return 0;
	}
}
