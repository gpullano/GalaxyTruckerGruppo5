package collezionabili;

import gameLogic.Colore;

/**
*rappresenta una merce con un colore associato.
*/

public class Merci {
	private Colore colore;

/**
*costruttore della classe merci
*@param colore il colore della merce
*/
	public Merci (Colore colore) {
		this.setColore(colore);
	}

/**
*restituisce il colore della merce
*@return colore associato alla merce
*/
	public Colore getColore() {
		return colore;
	}

/**
*imposta il colore della merce.
*@param colore il nuovo colore asociato alla merce.
*/
	public void setColore(Colore colore) {
		this.colore = colore;
	}
	
}
