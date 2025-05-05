package carteAvventura;

import java.util.Random;

public class CartaPianeti extends CartaOccupabile {
	// attributi: pianeti, gg di volo[ da 2 a 4], merci,  
	private final Pianeta pianeti[];

	public CartaPianeti(int giorniDiVolo, int livello) {
		super(giorniDiVolo, livello, new Random().nextInt(3) + 2);
		// creo un numero di pianeti quanti sono i posti atterrabili
		int postiAtterrabili = this.getPostoAtterrabile().length;
		this.pianeti = new Pianeta[postiAtterrabili];
	}

	public Pianeta[] getPianeti() {
		return pianeti;
	}

	

}
