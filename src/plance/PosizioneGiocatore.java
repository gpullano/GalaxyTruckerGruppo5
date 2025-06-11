package plance;

import gameLogic.Colore;

public class PosizioneGiocatore {
	private int giro;
	private Colore colore;
	private int posizione;
	private static final int LUNGHEZZA_PERCORSO = 18;



public PosizioneGiocatore(int posizione, int giro, Colore colore) {
	this.posizione = posizione;
	this.giro = giro;
	this.colore = colore;
}



public int getGiro() {
	return giro;
}



public void setGiro(int giro) {
	this.giro = giro;
}



public void setColore(Colore colore) {
	this.colore = colore;
}

public Colore getColore() {
	return this.colore;
}

public int getPosizione() {
	return posizione;
}



public void setPosizione(int posizione) {
	this.posizione = posizione;
}

public void aggiornaPosizione(int giorni) {
	//Sforamento del giro in avanti
	if(this.posizione + giorni > LUNGHEZZA_PERCORSO) {
		this.posizione += giorni - LUNGHEZZA_PERCORSO;
		this.giro++;
		
	//Sforamento del giro all'indietro
	}else if (this.posizione + giorni < 1) {
        this.posizione = this.posizione + giorni + LUNGHEZZA_PERCORSO;
        this.giro--;
    }else {
		this.posizione += giorni;
	}
}

@Override
public String toString() {
	return "PosizioneGiocatore{" + "Posizione = " + this.posizione + ", giro=" + this.giro + ", colore=" + this.colore + "}";
	
}






}