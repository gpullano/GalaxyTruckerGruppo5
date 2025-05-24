package gameLogic;

public class SetupPartita {
	//attributi
	private final LivelloPartita livelloPartita;
	private final int numGiocatori;
	private final Colore coloreGiocatori[];
	
	//costruttore
	public SetupPartita(ConsoleIO inputOutput) {
		this.livelloPartita = inputOutput.chiediLivelloGioco();
		this.numGiocatori = inputOutput.chiediNumGiocatori();
		this.coloreGiocatori = inputOutput.chiediColoreGiocatori(numGiocatori);
		
	}
	
	//getters e setters
	public LivelloPartita getLivelloPartita() {
		return livelloPartita;
	}

	public int getNumGiocatori() {
		return numGiocatori;
	}

	public Colore[] getColoreGiocatori() {
		return coloreGiocatori;
	}

}
