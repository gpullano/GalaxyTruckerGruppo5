package carteAvventura;

public class PolvereStellare extends CartaPerditaGiorniVolo {
	// non ha attributi
	public PolvereStellare(int livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Polvere Stellare - Livello: ").append(getLivello()).append("\n");
		sb.append("Ogni giocatore perde ").append(getGiorniVoloPersi() + " ").append("giorni di volo per ogni connettore scoperto\n");
		
		return sb.toString();
	}


}
