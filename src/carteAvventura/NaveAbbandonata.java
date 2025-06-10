package carteAvventura;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceNaveLivello1;
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

	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		int i=0;
		boolean attivata=false;
		while(i<giocatore.size()&&!attivata) {
			inputOutput.stampaMessaggio("\n--- Turno del GIOCATORE " + giocatore.get(i).getColore() + " ---");
			
			if (giocatore.get(i).getPlanceNave().getEquipaggioTotale()>=this.equipaggioRichiesto) {
				inputOutput.stampaMessaggio("\nGIOCATORE " + giocatore.get(i).getColore() + " hai abbastanza equipaggio"
						+ "per ottenere i crediti.");
				int equipaggio=giocatore.get(i).getPlanceNave().getEquipaggioTotale();
				attivata=inputOutput.chiediAttivare(giocatore.get(i));
				if (attivata) {
					giocatore.get(i).getPlanceNave().setEquipaggioTotale(equipaggio-this.equipaggioRichiesto);
					giocatore.get(i).aggiungiCrediti(this.creditiCosmiciOttenuti);
					planceVolo.getPosizioneGiocatori()[i].aggiornaPosizione(this.getGiorniVoloPersi());
				}
			} else {
				inputOutput.stampaMessaggio("\nGIOCATORE " + giocatore.get(i).getColore() + " NON hai abbastanza equipaggio"
						+ "per ottenere i crediti.");
			}
			
			i++;
		}
		
	}
}

