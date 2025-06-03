package carteAvventura;
import java.util.List;
import java.util.Random;

import dadiEClessidra.Dadi;
import gameLogic.Giocatore;

public class PioggiaDiMeteoriti extends Carta {
	// attributi
	private int numeroMeteorite;
	private Meteorite[] meteoriti;
	private Dadi dadi;
	
	// costruttore
	public PioggiaDiMeteoriti(int livello) {
		super(livello);
		Random rand=new Random();
		numeroMeteorite=rand.nextInt(4)+1;
		meteoriti=new Meteorite[numeroMeteorite];
		for (int i=0;i<meteoriti.length;i++) {
			meteoriti[i]=new Meteorite(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
		this.dadi = new Dadi();
	}

	public int getNumeroMeteorite() {
		return numeroMeteorite;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pioggia Di Meteoriti - Livello: ").append(getLivello()).append("\n");
		sb.append("La tua nave verrà colpita da ").append(getNumeroMeteorite() + " ").append("meteoriti\n");
		
		for(int i = 0; i < meteoriti.length; i++) {
			sb.append(" - Meteorite ").append(i +1).append(": ").append(meteoriti[i].getDimensione()).append(" da ").append(meteoriti[i].getProvenienza()).append("\n");
		}
		
		
		return sb.toString();
	}
	
	public void attiva(List<Giocatore> giocatori) {
	// TODO guardare come implementare il discorso di colpire nave

	}

}
