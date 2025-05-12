package carteAvventura;

import collezionabili.Merci;
import java.util.Random;

public class StazioneAbbandonata extends CartaPerditaGiorniVolo {
		// attributi 
	private final int equipaggioRichiesto;
	private final Merci merce;
	
	
	public StazioneAbbandonata(int livello) {
		super(livello);
		Random rand=new Random();
		this.equipaggioRichiesto=rand.nextInt(3)+4;
		this.merce=Merci.generaMerceCasuale();
	}
	
	 
	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}


	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}


	public Merci getMerce() {
		return merce;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
