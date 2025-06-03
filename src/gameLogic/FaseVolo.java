package gameLogic;

import java.util.List;
import carteAvventura.Carta;
import carteAvventura.CartaPerditaGiorniVolo;
import dadiEClessidra.Dadi;
import plance.PlanceVolo;

public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;
	private Dadi dadi;

	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte, Dadi dadi) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
		this.dadi = dadi;
	}

	@Override
	public void eseguiFase() {
		for(Carta carta: mazzoDiCarte) {
			if(carta instanceof CartaPerditaGiorniVolo) {
				((CartaPerditaGiorniVolo) carta).attiva(giocatori, planceVolo, inputOutput);
			}
			//TODO - completa
		}

	}
	
	public void rimuoviCartaInCima() {
		this.mazzoDiCarte.remove(0);
	}

}
