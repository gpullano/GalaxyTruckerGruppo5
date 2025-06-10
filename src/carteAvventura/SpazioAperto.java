package carteAvventura;

import java.util.List;

import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import gameLogic.Gioco;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;


public class SpazioAperto extends Carta {
// non ha attributi 
	public SpazioAperto(int livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Spazio Aperto - Livello: ").append(getLivello()).append("\n");
		sb.append("A turno partendo dal leader ogni giocatore dichiara la sua potenza motrice");
		sb.append("Ogni giocatore guadagna un giorno di volo per ogni potenza motrice che compone la sua nave ");	
		return sb.toString();
	}
	
	
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		int i=0;
		while(i<giocatore.size()) {
			Giocatore giocatoreCorrente = giocatore.get(i);
			inputOutput.stampaMessaggio("\n--- Turno del Giocatore " + giocatoreCorrente.getColore() + " ---");
			int potenzaMotoriGiocatoreCorrente = giocatoreCorrente.getPlanceNave().getPotenzaMotori(inputOutput);
			if (potenzaMotoriGiocatoreCorrente == 0) {
				giocatoreCorrente.abbandonaPartita();
				inputOutput.stampaMessaggio("Non hai più potenza motori, sei costretto ad abbandonare la corsa");
			} else {
				planceVolo.getPosizioneGiocatori().get(i).aggiornaPosizione(potenzaMotoriGiocatoreCorrente);
				inputOutput.stampaMessaggio("Hai una potenza motori pari a " + potenzaMotoriGiocatoreCorrente +
						", guadagni " + potenzaMotoriGiocatoreCorrente + " giorni di volo.");
				}
			
			i++;
		} 
		
	}

}
