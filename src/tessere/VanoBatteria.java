package tessere;

public class VanoBatteria extends Tessera {
	// attributo
	private final int batteria;
	//costruttore ereditato superclasse
	public VanoBatteria(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int batteria) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.batteria=batteria;
	}
	public int getBatteria() {
		return batteria;
	}

	
	
	
}
