package plance;

import gameLogic.Colore;

public class PosizioneGiocatore {
	private int riga;
	private int colonna;
	private int giro;
	private Colore colore;



public PosizioneGiocatore(int riga, int colonna, int giro, Colore colore) {
	this.setRiga(riga);
	this.setColonna(colonna);
	this.setGiro(giro);
	this.setColore(colore);
}



public int getRiga() {
	return riga;
}



public void setRiga(int riga) {
	this.riga = riga;
}



public int getColonna() {
	return colonna;
}



public void setColonna(int colonna) {
	this.colonna = colonna;
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

@Override
public String toString() {
	return "PosizioneGiocatore{" + "riga=" + riga + ", colonna=" + colonna + ", giro=" + giro + ", colore=" + colore + "}";
	
}


}