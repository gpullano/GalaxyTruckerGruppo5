package gameLogic;

import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;

public abstract class Fase {
	private List<Giocatore> giocatori;
	private PlanceVolo planceVolo;
	private ConsoleIO inputOutput;
	// list giocatore
	// dentro fase di assemblaggio attributo mucchio e intero giocatore corrente
	// fase volo - queue eventi
	
	//esiste prossimo turno
	//prossimo turno
	//esegui fase
	
	protected Fase(List<Giocatore> giocatori, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		this.giocatori = giocatori;
		this.planceVolo = planceVolo;
		this.inputOutput = inputOutput;
	}
	
	public abstract void eseguiFase();
	
}
