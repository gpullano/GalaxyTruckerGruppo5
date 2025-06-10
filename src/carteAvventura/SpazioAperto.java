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
			giocatoreCorrente.getPlanceNave().calcolaPotenzaFuoco(inputOutput);
			int potenzaFuocoGiocatoreCorrente = giocatoreCorrente.getPlanceNave().getPotenzaFuoco(inputOutput);
			if (potenzaFuocoGiocatoreCorrente == 0) {
				giocatoreCorrente.abbandonaPartita();
			} else {
				planceVolo.getPosizioneGiocatori()[i].aggiornaPosizione(potenzaFuocoGiocatoreCorrente);
				}
			
			i++;
		}
		
	}

}
