package carteAvventura;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;
import tessere.Tessera;

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
<<<<<<< Upstream, based on branch 'main' of https://github.com/gpullano/GalaxyTruckerGruppo5.git
	public void attiva(List<Giocatore> giocatori, PlanceVolo plancevolo) {
		Scanner scanner=new Scanner(System.in);
=======
	public void attiva(List<Giocatore>giocatori, PosizioneGiocatore posizione, List<Tessera> tessere) {
>>>>>>> 4143ff1 aggiunta di files
		//devo trovare il leader 
		// inizializzo il leader
		Giocatore leader = plance;
		int giroLeader=leader.getPosizione().getGiro();
		// uso un contatore per vedere se hanno tutti lo stesso giro 
		int cont=0;
		for (int i=1; i<giocatori.size();i++) {
			if (giocatori.get(i).getPosizione().getGiro()<giroLeader) {
				leader=giocatori.get(i);
				if (giocatori.get(i).getPosizione().getGiro()==giroLeader) {
					cont=cont+1;
				}
			}
			
		}if(cont==3) { // sono tutti allo stesso giro, qui bisognerebbe controllare la riga o la colonna
				
		}
		// tolgo l'equipaggio 
		tessere.get(Cabina);
	}
	

	
}

