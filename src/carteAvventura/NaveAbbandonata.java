package carteAvventura;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class NaveAbbandonata extends CartaPerditaGiorniVolo {
	// attributi secondo me equipaggio e crediti vanno generati casualmente 
	private final int equipaggioRichiesto;
	private final int creditiCosmiciOttenuti;
	
	public NaveAbbandonata(int livello) {
		super(livello);
		Random rand =new Random();
		this.creditiCosmiciOttenuti=rand.nextInt(6)+2;
		this.equipaggioRichiesto=rand.nextInt(10)+2;
	}
	
	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}
	public int getCreditiCosmiciOttenuti() {
		return creditiCosmiciOttenuti;
	}
	
// metodi 

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nave Abbandonata - Livello: ").append(getLivello()).append("\n");
		sb.append("Questa carta richiede di rinunciare a ").append(getEquipaggioRichiesto() + " ").append("pedine equipaggio\n");
		sb.append("La carta permette di guadagnare ").append(getCreditiCosmiciOttenuti() + " ").append("crediti cosmici\n");
		sb.append("Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n\n");
	
		
		return sb.toString();
	}

	@Override
	public void attiva(List<Giocatore> giocatori, PlanceVolo plancevolo) {
	}
}

