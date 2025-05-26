package carteAvventura;

import java.util.Random;


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
	
}
