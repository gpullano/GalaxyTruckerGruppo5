package carteAvventura;
import java.util.Random;
public class Contrabbandieri extends CartaPerditaGiorniVolo {
	// attributi generare casualmente gli attributi in base al livello 
	private final int fuocoNemico;
	private final int merciRimosse;
	private final int merciAcquisite;
	Random rand=new Random();
	public Contrabbandieri( int livello ) {
		super(livello);
		this.fuocoNemico=rand.nextInt(6)+4;
		this.merciRimosse =rand.nextInt(3)+2;	
		this.merciAcquisite=rand.nextInt(4)+2;
		
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



	public int getMerciAcquisite() {
		return merciAcquisite;
	}
}
