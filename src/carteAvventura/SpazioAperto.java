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
	
	
	public void attiva(List<Giocatore> giocatore, PosizioneGiocatore posizioneGiocatore, ConsoleIO inputOutput) {
		int potenzaRichiesta = 0;

		//check if every player is on the game or not 
		for(Giocatore g : giocatori){
			if(g.getHaAbbandonato()){
				 System.out.println("Giocatore " + g.getColore() + " ha abbandonato, nessun effetto.");
				continue;
			}
			
			int motoriAttivi = g.ContaPotenzaMotori();
			 System.out.println("Giocatore " + g.getColore() + " ha " + motoriAttivi + " motori attivi.");

            if (motoriAttivi >= potenzaRichiesta) {
                // Apply the effect — e.g. increase flight days or points
                // For example:
                System.out.println("Giocatore " + g.getColore() + " avanza di 1 giorno di volo!");
                // TODO - Here you could add code to update player's status/score, etc.
				// Here: increase the flight days of the player
            } else {
                System.out.println("Giocatore " + g.getColore() + " non ha potenza sufficiente.");
            }
        }
			

		}

}
