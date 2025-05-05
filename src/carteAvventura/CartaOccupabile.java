package carteAvventura;

public class CartaOccupabile extends Carta{
	private final boolean postoAtterrabile[];

	public CartaOccupabile(int giorniDiVolo, int livello, int NumPostiAtterrabili) {
		super(giorniDiVolo, livello);
		this.postoAtterrabile = new boolean[NumPostiAtterrabili];
	}

	public boolean[] getPostoAtterrabile() {
		return postoAtterrabile;
	}

}
