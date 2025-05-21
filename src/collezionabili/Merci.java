package collezionabili;

import gameLogic.Colore;

public class Merci {
	private Colore colore;
	
	public Merci (Colore colore) {
		this.setColore(colore);
	}

	public Colore getColore() {
		return colore;
	}

	public void setColore(Colore colore) {
		this.colore = colore;
	}
	
}
