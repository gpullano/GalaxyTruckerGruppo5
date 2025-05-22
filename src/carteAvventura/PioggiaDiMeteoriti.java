package carteAvventura;
import java.util.Random;

public class PioggiaDiMeteoriti extends Carta {
	// attributi
	
	private int numeroMeteorite;
	private Meteorite[] meteoriti;
	// costruttore
	public PioggiaDiMeteoriti(int livello) {
		super(livello);
		Random rand=new Random();
		numeroMeteorite=rand.nextInt(4)+1;
		meteoriti=new Meteorite[numeroMeteorite];
		for (int i=0;i<meteoriti.length;i++) {
			meteoriti[i]=new Meteorite(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub

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

}
