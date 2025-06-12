package carteAvventura;

import java.util.List;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;

public class PolvereStellare extends CartaPerditaGiorniVolo {
	// non ha attributi
	public PolvereStellare(int livello) {
		super(livello);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polvere Stellare - Livello: ").append(getLivello()).append("\n");
		sb.append("Ogni giocatore perde -1 giorni di volo per ogni connettore scoperto\n");
		
		return sb.toString();
	}


	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		// contare i connettori esposti per ogni nave di ogni giocatore 
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		for (int i=0;i<giocatore.size();i++) {
			Giocatore giocatoreAttuale=giocatore.get(i);
			inputOutput.stampaMessaggio("\n--- Turno del Giocatore " + giocatoreAttuale.getColore() + " ---");
			PlanceNaveLivello1 planceGiocatore=giocatoreAttuale.getPlanceNave();
			int connettoriEsposti=GestorePlanceNave.contaConnettoriEsposti(planceGiocatore);
			// se il giocatore ne ha, arretrare quanti connettori esposti ha 
			if (connettoriEsposti>0) {
				// tolgo i gg di volo 
				planceVolo.getPosizioneGiocatori().get(i).aggiornaPosizione(-connettoriEsposti);
				inputOutput.stampaMessaggio("Hai una somma pari a " + connettoriEsposti +
						", perdi " + connettoriEsposti + " giorni di volo.");
			}
		}
	}


}
