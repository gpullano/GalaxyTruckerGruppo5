package tessere;

public class Cannone extends Tessera {
	//attributi
	private final float sparo;
	
	//costruttore
	public Cannone(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, float sparo) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.sparo=sparo;
		
	}

	// getter
	public float getSparo() {
		return sparo;
	}
	//metodi 

}
