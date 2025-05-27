package gameLogic;

import java.util.List;
import carteAvventura.Carta;
import dadiEClessidra.Dadi;

public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;
	private Dadi dadi;

	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, List<Carta> mazzoDiCarte, Dadi dadi) {
		super(giocatori, inputOutput);
		this.mazzoDiCarte = mazzoDiCarte;
		this.dadi = dadi;
	}

	@Override
	public void eseguiFase() {
		// TODO Auto-generated method stub

	}
	
	public void rimuoviCartaInCima() {
		this.mazzoDiCarte.remove(0);
	}

}
