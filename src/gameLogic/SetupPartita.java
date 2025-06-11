package gameLogic;

/**
 * Raccoglie e memorizza le impostazioni iniziali della partita.
 * Durante la sua creazione, interagisce con l'utente tramite ConsoleIO
 * per determinare il livello, il numero di giocatori e i loro colori.
 */
public class SetupPartita {
	
	/** Il livello di gioco selezionato. */
	private final LivelloPartita livelloPartita;
	
	/** Il numero di giocatori partecipanti. */
	private final int numGiocatori;
	
	/** L'array dei colori scelti dai giocatori. */
	private final Colore coloreGiocatori[];
	
	/**
	 * Costruttore che avvia il processo di setup della partita,
	 * chiedendo all'utente le informazioni necessarie.
	 * @param inputOutput L'oggetto ConsoleIO utilizzato per dialogare con l'utente.
	 */
	public SetupPartita(ConsoleIO inputOutput) {
		this.livelloPartita = inputOutput.chiediLivelloGioco();
		this.numGiocatori = inputOutput.chiediNumGiocatori();
		this.coloreGiocatori = inputOutput.chiediColoreGiocatori(numGiocatori);
	}
	
	/**
	 * Restituisce il livello di partita scelto.
	 * @return Il livello della partita.
	 */
	public LivelloPartita getLivelloPartita() {
		return livelloPartita;
	}

	/**
	 * Restituisce il numero di giocatori.
	 * @return Il numero di giocatori.
	 */
	public int getNumGiocatori() {
		return numGiocatori;
	}

	/**
	 * Restituisce l'array dei colori scelti dai giocatori.
	 * @return Un array contenente i colori dei giocatori.
	 */
	public Colore[] getColoreGiocatori() {
		return coloreGiocatori;
	}
}