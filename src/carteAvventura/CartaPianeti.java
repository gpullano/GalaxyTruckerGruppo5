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

	

	public void attiva(List<Giocatore> giocatori, PlanceVolo planceVolo, ConsoleIO inputOutput) {
	    
	    inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta

	    boolean[] pianetiOccupati = new boolean[pianeti.length];
	    int[] scelteAtterraggio = new int[giocatori.size()];
	    Arrays.fill(scelteAtterraggio, -1);

	    // --- FASE 1: Scelta dei Pianeti (in ordine di rotta) ---
	    inputOutput.stampaMessaggio("\n--- FASE DI SCELTA DEI PIANETI (dal primo all'ultimo giocatore) ---");
	    for (int i = 0; i < giocatori.size(); i++) {
	        Giocatore giocatoreCorrente = giocatori.get(i);
	        inputOutput.stampaMessaggio("\n-> Turno di scelta per il GIOCATORE " + giocatoreCorrente.getColore());

	        boolean vuoleAtterrare = inputOutput.chiediAttivare(giocatoreCorrente);
	        if (vuoleAtterrare) {
	            // La validazione della scelta è dentro scegliPianeta
	            int sceltaPianeta = inputOutput.scegliPianeta(giocatoreCorrente, pianeti, pianetiOccupati); 
	            pianetiOccupati[sceltaPianeta] = true;
	            scelteAtterraggio[i] = sceltaPianeta;
	            inputOutput.stampaMessaggio("   Giocatore " + giocatoreCorrente.getColore() + " ha prenotato il Pianeta " + (sceltaPianeta + 1) + ".");
	        } else {
	            inputOutput.stampaMessaggio("   Giocatore " + giocatoreCorrente.getColore() + " decide di non atterrare.");
	        }
	    }

	    // --- FASE 2: Risoluzione degli Effetti (in ordine INVERSO di rotta) ---
	    inputOutput.stampaMessaggio("\n--- FASE DI RISOLUZIONE (dall'ultimo al primo giocatore) ---");
	    for (int j = giocatori.size() - 1; j >= 0; j--) {
	        Giocatore giocatoreCorrente = giocatori.get(j);
	        int pianetaScelto = scelteAtterraggio[j];

	        // Processa solo i giocatori che hanno effettivamente scelto un pianeta
	        if (pianetaScelto != -1) {
	            inputOutput.stampaMessaggio("\n-> Risoluzione per il GIOCATORE " + giocatoreCorrente.getColore() + ", che atterra sul Pianeta " + (pianetaScelto + 1) + "...");

	            // a) Perdi giorni di volo
	            PosizioneGiocatore pos = planceVolo.getPosizioneDi(giocatoreCorrente.getColore());
	            pos.aggiornaPosizione(-this.getGiorniVoloPersi()); // Passa valore negativo
	            inputOutput.stampaMessaggio("   Perde " + this.getGiorniVoloPersi() + " giorni di volo.");

	            // b) Carica le merci
	            Merci[] merciAcquisite = pianeti[pianetaScelto].getMerciPianeta();
	            int spazioRimanente = giocatoreCorrente.getPlanceNave().getSpazioMerciDisponibileTotale();

	            if (spazioRimanente >= merciAcquisite.length) {
	                giocatoreCorrente.getPlanceNave().aggiungiMerci(Arrays.asList(merciAcquisite));	
	                inputOutput.stampaMessaggio("   Carica tutte le " + merciAcquisite.length + " merci disponibili.");
	            } else {
	                inputOutput.stampaMessaggio("   Spazio insufficiente! Puoi caricare solo " + spazioRimanente + " merci su " + merciAcquisite.length + ".");
	                List<Merci> merciDaCaricare = inputOutput.chiediMerciDaPrendere(Arrays.asList(merciAcquisite), spazioRimanente);
	                giocatoreCorrente.getPlanceNave().aggiungiMerci(merciDaCaricare);
	            }
	        }
	    }
	    inputOutput.stampaMessaggio("\n--- Risoluzione della carta Pianeti completata. ---");
	}

}
