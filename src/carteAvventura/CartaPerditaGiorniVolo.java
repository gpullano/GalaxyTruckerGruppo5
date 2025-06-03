package carteAvventura;

import java.util.Random;
import gameLogic.Giocatore;
import plance.PosizioneGiocatore;


public abstract class CartaPerditaGiorniVolo extends Carta{
	//attributi
	private final int giorniVoloPersi;
	
	//costruttore
	protected CartaPerditaGiorniVolo(int livello) {
		super(livello);
		this.giorniVoloPersi = new Random().nextInt(3) - 3;
	}
	
	//getter
	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}
	
	// metodi 
	public abstract void attiva(Giocatore giocatore, PosizioneGiocatore posizioneGiocatore);
	
}
