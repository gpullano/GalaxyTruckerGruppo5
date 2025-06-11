package gameLogic;

import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;

/**
*la classe astratta Fase rappresenta il modello base per tutte le fasi del gioco.
*/
public abstract class Fase {
	private List<Giocatore> giocatori;
	private ConsoleIO inputOutput;
	private PlanceVolo planceVolo;
	
	/**
	*costruttore della classe Fase.
	*@param giocatori la lista dei giocatori.
	*@param inputOutput l'oggetto per l'input/output.
	*@param planceVolo la plancia di volo comune.
	*/
	protected Fase(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		this.setGiocatori(giocatori);
		this.setInputOutput(inputOutput);
		this.setPlanceVolo(planceVolo);
	}
	
	/**
	*esegue la logica specifica della fase.
	*/
	public abstract void eseguiFase();

	/**
	*restituisce la lista dei giocatori.
	*@return la lista dei giocatori.
	*/
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	/**
	*imposta la lista dei giocatori.
	*@param giocatori la nuova lista dei giocatori.
	*/
	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	/**
	*restituisce l'oggetto di input/output.
	*@return l'oggetto ConsoleIO.
	*/
	public ConsoleIO getInputOutput() {
		return inputOutput;
	}

	/**
	*imposta l'oggetto di input/output.
	*@param inputOutput il nuovo oggetto ConsoleIO.
	*/
	public void setInputOutput(ConsoleIO inputOutput) {
		this.inputOutput = inputOutput;
	}

	/**
	*restituisce la plancia di volo.
	*@return la plancia di volo.
	*/
	public PlanceVolo getPlanceVolo() {
		return planceVolo;
	}
	
	/**
	*imposta la plancia di volo.
	*@param planceVolo la nuova plancia di volo.
	*/
	public void setPlanceVolo(PlanceVolo planceVolo) {
		this.planceVolo = planceVolo;
	}
	
}