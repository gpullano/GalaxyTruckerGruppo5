package carteAvventura;
import java.util.Random;

import collezionabili.Merci;
public class Contrabbandieri extends CartaPerditaGiorniVolo {
	// attributi generare casualmente gli attributi in base al livello 
	private final int fuocoNemico;
	private final int merciRimosse;
	private final Merci merciAcquisite[];
	
	public Contrabbandieri( int livello ) {
		super(livello);
		Random rand=new Random();
		this.fuocoNemico=rand.nextInt(6)+4;
		this.merciRimosse =rand.nextInt(3)+2;	
		
		int numeroMerci = rand.nextInt(4) + 2;
		this.merciAcquisite= new Merci[numeroMerci];
		
		Merci.Colore[] colori = Merci.Colore.values();
		
		for (int i = 0; i < numeroMerci; i++) {
			Merci.Colore coloreCasuale = colori[rand.nextInt(colori.length)];
			this.merciAcquisite[i] = new Merci(coloreCasuale);
			
		}
		
	}

	

	public int getFuocoNemico() {
		return fuocoNemico;
	}

	public int getMerciRimosse() {
		return merciRimosse;
	}
	
	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}



	public Merci[] getMerciAcquisite() {
		return merciAcquisite;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Carta Pianeti - Livello: ").append(getLivello()).append("\n");
		sb.append("I contrabbandieri hanno una potenza di fuoco = ").append(getFuocoNemico()).append("\n");
		sb.append("Attaccano in ordine di rotta partendo dal leader").append("\n\n");
		sb.append("- Se il giocatore perde ").append("\n");
		sb.append("  - Perde ").append(getMerciRimosse() + " ").append("merci delle sue più preziose").append("\n\n");
		sb.append("- Se il giocatore vince ").append("\n");
		sb.append("  - Guadagna: ").append("\n");
		collezionabili.Merci[] merci = getMerciAcquisite();
		for (collezionabili.Merci merce : merci) {
			sb.append("    - Merce di colore: ").append(merce.getColore()).append("\n");
		}
		sb.append("    Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n\n");
		sb.append("- Se il giocatore pareggia ").append("\n");
		sb.append("  - non succede niente però i contrabbandieri continuano ad attaccare").append("\n");
		
		return sb.toString();
	}

}
