package carteAvventura;

import collezionabili.Merci;
import gameLogic.Colore;

import java.util.Random;

public class StazioneAbbandonata extends CartaPerditaGiorniVolo {
		// attributi 
	private final int equipaggioRichiesto;
	private final Merci merciAcquisite[];
	
	
	public StazioneAbbandonata(int livello) {
		super(livello);
		Random rand=new Random();
		this.equipaggioRichiesto=rand.nextInt(3)+4;
		
		int numeroMerci = rand.nextInt(4) + 2;
		this.merciAcquisite= new Merci[numeroMerci];
		
        Colore[] colori = Colore.values();
		
		for (int i = 0; i < numeroMerci; i++) {
			Colore coloreCasuale = colori[rand.nextInt(colori.length)];
			this.merciAcquisite[i] = new Merci(coloreCasuale);
			
		}
	}
	
	 
	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}


	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}


	public Merci[] getMerciAcquisite() {
		return merciAcquisite;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Stazione Abbandonata - Livello: ").append(getLivello()).append("\n");
		sb.append("Equipaggio richiesto: ").append(getEquipaggioRichiesto()).append("\n");
		sb.append("se il giocatore attacca guadagna:").append("\n");
		collezionabili.Merci[] merci = getMerciAcquisite();
		for (collezionabili.Merci merce : merci) {
			sb.append("    - Merce di colore: ").append(merce.getColore()).append("\n");
		}
		sb.append("Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n\n");
		
		return sb.toString();
	}



	

}
