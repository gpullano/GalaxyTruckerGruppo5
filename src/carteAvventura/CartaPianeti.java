package carteAvventura;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import collezionabili.Merci;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class CartaPianeti extends CartaPerditaGiorniVolo {
	// attributi
	private final Pianeta pianeti[];
	

	public CartaPianeti(int livello) {
		super(livello);
		// creo un numero di pianeti quanti sono i posti atterrabili
		int postiAtterrabili = new Random().nextInt(3) + 2;
		this.pianeti = new Pianeta[postiAtterrabili];
		
		for (int i = 0; i < pianeti.length; i++) {
	        this.pianeti[i] = new Pianeta(); 
	    }
	}

	public Pianeta[] getPianeti() {
		return pianeti;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Carta Pianeti - Livello: ").append(getLivello()).append("\n");
		sb.append("Questa carta genera ").append(pianeti.length + " ").append("pianeti atterrabili. \n");
		sb.append("Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n");
		sb.append("Numero pianeti atterrabili: ").append(pianeti.length).append("\n\n");
		
		
		for (int i = 0; i < pianeti.length; i++) {
			sb.append("- Pianeta ").append(i + 1).append(":\n");
			Merci[] merci =pianeti[i].getMerciPianeta();
			
			if (merci.length == 0) {
				sb.append("  - Nessuna merce\n");
			} else {
				for (Merci merce : merci) {
					sb.append("  - Merce di colore: ").append(merce.getColore()).append("\n");
				}
        }
		}
		return sb.toString();
	}

	

	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		int i = 0;
		boolean [] pianetiOccupati = new boolean [pianeti.length];
		int [] scelteAtterraggio = new int[giocatore.size()];
		Arrays.fill(scelteAtterraggio, -1);//imposta tutti i pianeti con -1 quindi tutti liberi
		while(i < giocatore.size()) {
			Giocatore giocatoreCorrente = giocatore.get(i);
			boolean attivata=inputOutput.chiediAttivare(giocatoreCorrente);
				if (attivata) {
					int sceltaPianeta = inputOutput.scegliPianeta(giocatoreCorrente, pianeti, pianetiOccupati);
					pianetiOccupati[sceltaPianeta] = true;
					scelteAtterraggio[i] = sceltaPianeta;					
				}
				i++;
		}
		
		for (int j = giocatore.size() -1; j >= 0; j--) {
			int pianetaScelto = scelteAtterraggio[j];
			if (pianetaScelto >= 0) {
				Giocatore giocatoreCorrente = giocatore.get(j);
				planceVolo.getPosizioneGiocatori().get(j).aggiornaPosizione(this.getGiorniVoloPersi());
				Merci[] merciAcquisite = pianeti[pianetaScelto].getMerciPianeta();
				int spazioRimanente = giocatoreCorrente.getPlanceNave().getSpazioMerciDisponibileTotale();

				if (spazioRimanente >= merciAcquisite.length) {
				    inputOutput.stampaMessaggio("Hai spazio per tutte le merci, sono TUTTE TUE.");
				    giocatoreCorrente.getPlanceNave().aggiungiMerci(Arrays.asList(merciAcquisite));	
				} else {
				    inputOutput.stampaMessaggio("NON hai abbastanza spazio per tutte le merci. Puoi caricarne solo " + spazioRimanente + ".");
				    // Passa lo spazio rimanente per far sapere al giocatore quante può sceglierne
				    List<Merci> merciDaCaricare = inputOutput.chiediMerciDaPrendere(Arrays.asList(merciAcquisite), spazioRimanente);
				    giocatoreCorrente.getPlanceNave().aggiungiMerci(merciDaCaricare);
				}
			}
			
		}
		
		
	}

}
