package carteAvventura;

import java.util.Random;
import collezionabili.Merci;

public class Pianeta {
	private final Merci merciPianeta[];
	private boolean occupato;
	
	public Pianeta() {
		//ogni pianeta può avere da 1 a 5 merci
		Random rand = new Random();
		int numeroMerci = rand.nextInt(5) + 1;
		this.merciPianeta = new Merci[numeroMerci];
		
		Merci.Colore[] colori = Merci.Colore.values();
		
		for (int i = 0; i < numeroMerci; i++) {
			Merci.Colore coloreCasuale = colori[rand.nextInt(colori.length)];
			this.merciPianeta[i] = new Merci(coloreCasuale);
			
		}
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
