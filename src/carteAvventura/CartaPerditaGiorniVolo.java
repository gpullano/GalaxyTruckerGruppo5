package carteAvventura;

public abstract class CartaPerditaGiorniVolo extends Carta{
	//attributi
	private final int giorniVoloPersi;
	
	//costruttore
	public CartaPerditaGiorniVolo(int livello, int giorniVoloPersi) {
		super(livello);
		this.giorniVoloPersi = giorniVoloPersi;
	}
	
	//getter
	public int getGiorniVoloPersi() {
		return giorniVoloPersi;
	}
	
}
