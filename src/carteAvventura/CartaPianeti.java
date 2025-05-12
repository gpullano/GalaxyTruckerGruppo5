package carteAvventura;

import java.util.Random;

public class CartaPianeti extends CartaPerditaGiorniVolo {
	// attributi
	private final Pianeta pianeti[];
	

	public CartaPianeti(int livello) {
		super(livello);
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
