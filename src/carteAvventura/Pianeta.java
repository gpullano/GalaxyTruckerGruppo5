package carteAvventura;

import java.util.Random;

import collezionabili.Merci;

public class Pianeta {
	private final Merci merciPianeta[];
	private boolean occupato;
	
	public Pianeta() {
		//ogni pianeta può avere da 1 a 5 merci
		this.merciPianeta = new Merci[new Random().nextInt(5) + 1];
	}

	public Merci[] getMerciPianeta() {
		return merciPianeta;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
}
