package carteAvventura;

import collezionabili.Merci;
import gameLogic.Colore;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StazioneAbbandonata extends CartaPerditaGiorniVolo {
		// attributi 
	private final int equipaggioRichiesto;
	private final Merci[] merciAcquisite;
	
	
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
		Merci[] merci = getMerciAcquisite();
		for (Merci merce : merci) {
			sb.append("    - Merce di colore: ").append(merce.getColore()).append("\n");
		}
		sb.append("Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n\n");
		
		return sb.toString();
	}



	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		int i = 0;
		boolean attivata = false;
		while(i < giocatore.size() || !attivata) {
			Giocatore giocatoreCorrente = giocatore.get(i);
			if (giocatoreCorrente.getPlanceNave().getEquipaggioTotale() >= this.equipaggioRichiesto) {
				attivata=inputOutput.chiediAttivare(giocatoreCorrente);
				if (attivata) {
					planceVolo.getPosizioneGiocatori()[i].aggiornaPosizione(this.getGiorniVoloPersi());
					if(giocatoreCorrente.getPlanceNave().getSpazioMerciRimasto() >= merciAcquisite.length) {
						giocatoreCorrente.getPlanceNave().getMerciNave().addAll(Arrays.asList(merciAcquisite));
					} else {
						inputOutput.chiediMerciDaPrendere();
					}
					
				}
			}
				i++;
		}
		
	}




	

}
