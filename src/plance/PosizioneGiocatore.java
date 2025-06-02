package plance;

import gameLogic.Colore;

public class PosizioneGiocatore {
	private int riga;
	private int colonna;
	private int giro;
	private Colore colore;
	private int posizione;



public PosizioneGiocatore(int riga, int colonna, int giro, Colore colore) {
	this.setRiga(riga);
	this.setColonna(colonna);
	this.setGiro(giro);
	this.setColore(colore);
}


public void daCordinateAPosizione() {
	
	if (this.riga == 0) {
		this.posizione = this.colonna;
		
	}
	
	if (this.colonna == 0) {
		this.posizione = 19 - this.riga;
	}
	
	if (this.colonna == 7) {
		this.posizione = this.riga + 6;
	}
	
	if (this.riga == 4) {
		this.posizione = 16 - this.colonna;
	}
	
	
	
	setPosizione(this.posizione);
}

public void daPosizioneACoordinate() {

	if (this.posizione >= 1 && this.posizione < 7) {
		this.riga = 0;
		this.colonna = this.posizione;	
	} else if (this.posizione >= 7 && this.posizione < 10) {
		this.riga = this.posizione - 6;
		this.colonna = 7;
	} else if (this.posizione >= 10 && this.posizione < 16) {
		this.riga = 4;
		this.colonna = 16 - this.posizione;
	} else if (this.posizione >= 16 && this.posizione < 19) {
		this.riga = 19 - this.posizione;
		this.colonna = 0;
	}
	
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

public Colore getColore() {
	return this.colore;
}

public int getPosizione() {
	return posizione;
}



public void setPosizione(int posizione) {
	this.posizione = posizione;
}

public void aggiornaPosizione(int giorni, int lunghezzaPercorso) {
	if(this.posizione + giorni > lunghezzaPercorso) {
		this.posizione += giorni - lunghezzaPercorso;
		this.giro++;
	}else {
		this.posizione += giorni;
	}
	daPosizioneACoordinate();
}

@Override
public String toString() {
	return "PosizioneGiocatore{" + "riga=" + riga + ", colonna=" + colonna + ", giro=" + giro + ", colore=" + colore + "}";
	
}






}