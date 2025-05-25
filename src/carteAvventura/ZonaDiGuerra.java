package carteAvventura;

public class ZonaDiGuerra extends CartaPerditaGiorniVolo{
	private final int equipaggioPerso;
	private final int potenzaFuoco;
	private final Cannonata cannonata[];
	
	
	public ZonaDiGuerra(int livello) {
		super(livello);
		this.equipaggioPerso = 0;
		this.potenzaFuoco = 0;
		this.cannonata = new Cannonata[2];
		// TODO Auto-generated constructor stub
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}


	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}
	
	public Cannonata[] getCannonata() {
		return cannonata;
	}


	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Zona Di Guerra - Livello: ").append(getLivello()).append("\n");
		sb.append("- Prima linea:\n");
		sb.append("  Il giocatore con meno equipaggio perde 3 giorni di volo\n");
		sb.append("- Seconda linea:\n");
		sb.append("  Il giocatore con meno potenza motrice perde 2 membri dell'equipaggio\n");
		sb.append("- Terza linea:\n");
		sb.append("  Il giocatore con meno potenza di fuoco riceve una cannonata leggera e una cannonata pesante proveniente da dietro\n");
		
		return sb.toString();
	}

}
