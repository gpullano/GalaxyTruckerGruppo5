package gameLogic;

public class Giocatore {
	public class giocatore {
    // Attributi
    private Colore colore;
    private nave stato;
    private int creditistellari;
    private int posizione;
    private boolean hasGivenUp;
    

    // Costruttore
    public giocatore(Colore colore, nave stato ) {
        this.colore= colore;
        this.stato = stato;
        this.creditistellari = 0;
        this.posizione = 0;
        this.hasGivenUp = false;
        
    }

    // Getters
    public Colore getColore() {
        return colore;
    }

    public nave getStato() {
        return stato;
    }

    public int getCreditiStellari() {
        return creditistellari;
    }

    public int getPosizione() {
        return posizione;
    }
    public boolean getHasGivenUp() {
        return hasGivenUp;
    }

    // Setters

    public void setStato(nave stato) {
        this.stato = stato;
    }

    public void setColore(Colore colore) {
        this.colore = colore;
    }


    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }
    //methodi
     public void aggiungiCrediti(int quantita) {
        this.creditistellari += quantita;
    }

    public void rimuoviCrediti(int quantita) {
        if(creditistellari>=quantita){
            this.creditistellari -=quantita; 
        }
    }

    public void avanzaPosizione() {
        if(posizione<20){
            posizione++; 
        }
    }
 
    public void giveUp() {
        hasGivenUp = true;
        System.out.println("Il giocatore ha deciso di arrendersi.");
    }
}

	
}
