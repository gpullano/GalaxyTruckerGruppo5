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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
