package carteAvventura;

import java.util.Random;

import tessere.Merci;

public class Pianeta {
	private final Merci merciPianeta[];
	
	public Pianeta() {
		//ogni pianeta può avere da 1 a 5 merci
		this.merciPianeta = new Merci[new Random().nextInt(5) + 1];
	}

	public Merci[] getMerciPianeta() {
		return merciPianeta;
	}
}
