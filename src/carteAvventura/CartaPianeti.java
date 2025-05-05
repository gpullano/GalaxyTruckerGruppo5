package carteAvventura;

import java.util.Random;

public class CartaPianeti extends CartaPerditaGiorniVolo {
	// attributi
	private final Pianeta pianeti[];

	public CartaPianeti(int giorniDiVolo, int livello) {
		super(giorniDiVolo, livello);
		// creo un numero di pianeti quanti sono i posti atterrabili
		int postiAtterrabili = new Random().nextInt(3) + 2;
		this.pianeti = new Pianeta[postiAtterrabili];
	}

	public Pianeta[] getPianeti() {
		return pianeti;
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}

}
