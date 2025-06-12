package gameLogic;

import java.util.List;
import plance.PlanceVolo;

public abstract class Fase {
	private List<Giocatore> giocatori;
	private ConsoleIO inputOutput;
	private PlanceVolo planceVolo;
	
	
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
