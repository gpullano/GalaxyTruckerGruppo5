package carteAvventura;

import java.util.Random;

import collezionabili.Merci;

public class StazioneAbbandonata extends CartaPerditaGiorniVolo {
	private final int equipaggioRichiesto;
	private final Merci merciAcquisite[];
	
	public StazioneAbbandonata(int livello) {
		super(livello);
		Random rand = new Random();
		this.equipaggioRichiesto = rand.nextInt(6) + 5;
		this.merciAcquisite= new Merci[rand.nextInt(4)+2];
	}
	
	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}

	public Merci[] getMerciAcquisite() {
		return merciAcquisite;
	}

	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}

}
