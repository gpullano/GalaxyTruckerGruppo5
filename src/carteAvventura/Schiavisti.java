package carteAvventura;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class Schiavisti extends CartaPerditaGiorniVolo {

	private final int equipaggioPerso;
	private final int potenzaFuoco;
	private final int creditiCosmici;
	
	
	public Schiavisti(int livello) {
		super(livello);
		Random rand = new Random();
		this.equipaggioPerso = rand.nextInt(3)+4;
		this.potenzaFuoco = rand.nextInt(2)+5;
		this.creditiCosmici = rand.nextInt(2)+4;
		// TODO Auto-generated constructor stub
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}


	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}


	public int getCreditiCosmici() {
		return creditiCosmici;
	}

	
	// metodi
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Schiavisti - Livello: ").append(getLivello()).append("\n");
		sb.append("Gli schiavisti hanno una potenza di fuoco = ").append(getPotenzaFuoco()).append("\n");
		sb.append("- Se il giocatore vince ").append("\n");
		sb.append("  - Guadagna ").append(getCreditiCosmici() + " ").append("crediti cosmici\n");
		sb.append("  Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n");
		sb.append("  Per evitare la perdita di giorni di volo, puoi rinunciare alla ricompensa").append("\n\n");
		
		sb.append("- Se il giocatore perde ").append("\n");
        sb.append("  Deve rinunciare a ").append(getEquipaggioPerso() + " ").append("componenti dell'equipaggio\n");
		
		
		return sb.toString();
	}
 

	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		int i = 0;
		boolean schiavistiSconfitti = false;
		boolean giorniVolo = false;
		while(i < giocatore.size() || !schiavistiSconfitti) {
			Giocatore giocatoreCorrente = giocatore.get(i);
			giocatoreCorrente.getPlanceNave().calcolaPotenzaFuoco(inputOutput);
			if (giocatoreCorrente.getPlanceNave().getPotenzaFuoco() > this.potenzaFuoco) {
				//giorniVolo = inputOutput.chideSeVuolePerdereGiorniVolo(giocatoreCorrente); TODO
				if (giorniVolo == true) {
					giocatoreCorrente.aggiungiCrediti(this.creditiCosmici);
					planceVolo.getPosizioneGiocatori()[i].aggiornaPosizione(this.getGiorniVoloPersi());
				} 
				schiavistiSconfitti = true;
			} else if (giocatoreCorrente.getPlanceNave().getPotenzaFuoco() <= this.potenzaFuoco){
				giocatoreCorrente.getPlanceNave().aggiungiEquipaggio(this.equipaggioPerso);
				
			}	
				
			
			}
				i++;
		}

}
