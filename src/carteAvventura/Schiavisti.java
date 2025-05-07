package carteAvventura;

import java.util.Random;

public class Schiavisti extends CartaPerditaGiorniVolo {
	private final int potenzaFuoco;
	private final int creditiVinti;
	private final int equipaggioRichiesto;

	public Schiavisti(int livello) {
		super(livello);
		Random rand = new Random();
		this.potenzaFuoco = rand.nextInt(4) + 5;
		this.creditiVinti = rand.nextInt(6) + 5;
		this.equipaggioRichiesto = rand.nextInt(3) + 3;
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public int getCreditiVinti() {
		return creditiVinti;
	}

	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}

}
