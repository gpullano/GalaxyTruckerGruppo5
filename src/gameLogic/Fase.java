package gameLogic;

import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;

public abstract class Fase {
	private List<Giocatore> giocatori;
	private ConsoleIO inputOutput;
	private PlanceVolo planceVolo;
	// list giocatore
	// dentro fase di assemblaggio attributo mucchio e intero giocatore corrente
	// fase volo - queue eventi
	
	//esiste prossimo turno
	//prossimo turno
	//esegui fase
	
	protected Fase(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		this.setGiocatori(giocatori);
		this.setInputOutput(inputOutput);
		this.setPlanceVolo(planceVolo);
	}
	
	public abstract void eseguiFase();

	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	public ConsoleIO getInputOutput() {
		return inputOutput;
	}

	public void setInputOutput(ConsoleIO inputOutput) {
		this.inputOutput = inputOutput;
	}

	public PlanceVolo getPlanceVolo() {
		return planceVolo;
	}
	
	public void setPlanceVolo(PlanceVolo planceVolo) {
		this.planceVolo = planceVolo;
	}
	
}
