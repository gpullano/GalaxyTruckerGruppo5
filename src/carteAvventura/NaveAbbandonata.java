package carteAvventura;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gameLogic.Giocatore;
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
	public void attiva(List<Giocatore>giocatori) {
		Scanner scanner=new Scanner(System.in);
		//devo trovare il leader 
		// inizializzo il leader
		Giocatore leader=giocatori.get(0);
		int giroLeader=leader.getPosizione().getGiro();
		for (int i=1; i<giocatori.size();i++) {
			if (giocatori.get(i).getPosizione().getGiro()<giroLeader) {
				leader=giocatori.get(i);
			}
			
		}
		System.out.println("il leader è il giocatore: "+leader);
		System.out.println(leader+" vuoi avvalerti della carta Nave Abbandonata?(si/no)");
		String risposta=scanner.nextLine().trim().toLowerCase();
		if (risposta=="si"||risposta=="sì") {
			
		}
	}

	
}

