package gameLogic;

public class SetupPartita {
	//attributi
	private final LivelloPartita livelloPartita;
	//TODO - parte giocatori
	
	//costruttore
	public SetupPartita(ConsoleIO inputOutput) {
		this.livelloPartita = inputOutput.chiediLivelloGioco();
		// TODO - parte giocatori
	}
	
	//getters e setters
	public LivelloPartita getLivelloPartita() {
		return livelloPartita;
	}

}
