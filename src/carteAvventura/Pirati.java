package carteAvventura;
import java.util.Random;
public class Pirati extends Carta {
	private final int potenzaFuoco;
	private final int creditiCosmici;
	
	public Pirati(int livello) {
		super(livello);
		Random rand=new Random();
		this.potenzaFuoco=rand.nextInt(2)+5;
		this.creditiCosmici=rand.nextInt(3)+5;
		
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public int getCreditiCosmici() {
		return creditiCosmici;
	}

}
