package carteAvventura;

import gameLogic.Giocatore;
import gameLogic.Gioco;


public class SpazioAperto extends Carta {
// non ha attributi 
	public SpazioAperto(int livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attiva(Gioco gioco) {
		int potenzaRichiesta = 0;

		switch (getLivello()) {
			case 1:
				potenzaRichiesta = 2;
				break;
			case 2:
				potenzaRichiesta = 4;
				break;
			case 3:
				potenzaRichiesta = 6;
				break;
			default:
				System.out.println("livello non supportato");
				break;
		}
		//check if every player is on the game or not 
		for(Giocatore g : gioco.getGiocatori()){
			if(g.getHaAbbandonato()){
				 System.out.println("Giocatore " + g.getColore() + " ha abbandonato, nessun effetto.");
				continue;
			}
			int motoriAttivi=g.ContaMotoriAttivi();
			 System.out.println("Giocatore " + g.getColore() + " ha " + motoriAttivi + " motori attivi.");

            if (motoriAttivi >= potenzaRichiesta) {
                // Apply the effect — e.g. increase flight days or points
                // For example:
                System.out.println("Giocatore " + g.getColore() + " avanza di 1 giorno di volo!");
                // Here you could add code to update player's status/score, etc.
				// Here: increase the flight days of the player
            } else {
                System.out.println("Giocatore " + g.getColore() + " non ha potenza sufficiente.");
            }
        }
			

		}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Spazio Aperto - Livello: ").append(getLivello()).append("\n");
		sb.append("A turno partendo dal leader ogni giocatore dichiara la sua potenza motrice");
		sb.append("Ogni giocatore guadagna un giorno di volo per ogni potenza motrice che compone la sua nave ");	
		return sb.toString();
	}

}
