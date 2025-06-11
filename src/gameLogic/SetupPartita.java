package gameLogic;

/**
*la classe SetupPartita gestisce la raccolta delle impostazioni iniziali della partita.
*/
public class SetupPartita {
	//attributi
	private final LivelloPartita livelloPartita;
	private final int numGiocatori;
	private final Colore coloreGiocatori[];
	
	/**
	*costruttore della classe SetupPartita.
	*@param inputOutput l'oggetto per gestire l'input e l'output.
	*/
	public SetupPartita(ConsoleIO inputOutput) {
		this.livelloPartita = inputOutput.chiediLivelloGioco();
		this.numGiocatori = inputOutput.chiediNumGiocatori();
		this.coloreGiocatori = inputOutput.chiediColoreGiocatori(numGiocatori);
		
	}
	
	/**
	*restituisce il livello di partita scelto.
	*@return il livello della partita.
	*/
	public LivelloPartita getLivelloPartita() {
		return livelloPartita;
	}

	/**
	*restituisce il numero di giocatori.
	*@return il numero dei giocatori.
	*/
	public int getNumGiocatori() {
		return numGiocatori;
	}

	/**
	*restituisce i colori scelti dai giocatori.
	*@return un array contenente i colori dei giocatori.
	*/
	public Colore[] getColoreGiocatori() {
		return coloreGiocatori;
	}

}