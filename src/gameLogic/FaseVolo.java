package gameLogic;

import java.util.List;
import carteAvventura.Carta;
import carteAvventura.CartaPerditaGiorniVolo;
import dadiEClessidra.Dadi;
import plance.PlanceVolo;

public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;
	private Dadi dadi;

	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
	}

	@Override
	public void eseguiFase() {
		for(Carta carta: mazzoDiCarte) {
			//TODO - aggiungi metodo per chiedere se i giocatori vogliono abbandonare
			if(carta instanceof CartaPerditaGiorniVolo) {
				((CartaPerditaGiorniVolo) carta).attiva(this.getGiocatori(), this.getPlanceVolo(), this.getInputOutput());
			}
			//TODO - completa
		}

	}
	
	//TODO - vedi se serve
	public void rimuoviCartaInCima() {
		this.mazzoDiCarte.remove(0);
	}

}
