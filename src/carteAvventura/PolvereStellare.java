package carteAvventura;

import java.util.List;

import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class PolvereStellare extends CartaPerditaGiorniVolo {
	// non ha attributi
	public PolvereStellare(int livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polvere Stellare - Livello: ").append(getLivello()).append("\n");
		sb.append("Ogni giocatore perde ").append(getGiorniVoloPersi() + " ").append("giorni di volo per ogni connettore scoperto\n");
		
		return sb.toString();
	}


	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		// contare i connettori esposti per ogni nave di ogni giocatore 
		// se il giocatore ne ha, arretrare quanti connettori esposti ha 
		// planceVolo.
		
		
	}


}
