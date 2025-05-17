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
		
		for (int i = 0; i < pianeti.length; i++) {
	        this.pianeti[i] = new Pianeta(); 
	    }
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
		StringBuilder sb = new StringBuilder();
		sb.append("Carta Pianeti - Livello: ").append(getLivello()).append("\n");
		sb.append("Questa carta genera ").append(pianeti.length + " ").append("pianeti atterrabili. \n");
		sb.append("Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n");
		sb.append("Numero pianeti atterrabili: ").append(pianeti.length).append("\n\n");
		
		
		for (int i = 0; i < pianeti.length; i++) {
			sb.append("- Pianeta ").append(i + 1).append(":\n");
			collezionabili.Merci[] merci =pianeti[i].getMerciPianeta();
			
			if (merci.length == 0) {
				sb.append("  - Nessuna merce\n");
			} else {
				for (collezionabili.Merci merce : merci) {
					sb.append("  - Merce di colore: ").append(merce.getColore()).append("\n");
				}
        }
		}
		return sb.toString();
	}

}
