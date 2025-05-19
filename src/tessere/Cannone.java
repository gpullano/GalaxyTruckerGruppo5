package tessere;

public class Cannone extends Tessera {
	
	//costruttore
	public Cannone(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

	// getter
	public float getSparo() {
		if (this.getLatoSup() == Connettore.CANNONE)
			return 1;
		else
			return 0.5f;
	}
	@Override
	public String getNomeBreve() {
    	return "CANON";
	}



	
}
