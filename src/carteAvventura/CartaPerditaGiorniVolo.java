package carteAvventura;

import java.util.Random;

public abstract class CartaPerditaGiorniVolo extends Carta{
	//attributi
	private final int giorniVoloPersi;
	
	//costruttore
	public CartaPerditaGiorniVolo(int livello) {
		super(livello);
		this.giorniVoloPersi = new Random().nextInt(3) + 1;
	}
	
	//getter
	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}
	
}
