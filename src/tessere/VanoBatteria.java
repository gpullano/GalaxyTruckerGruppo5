package tessere;

public class VanoBatteria extends Tessera {
	// attributo
	private int batterie;
	//costruttore ereditato superclasse
	public VanoBatteria(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int batterie) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.batterie=batterie;
	}
	//getter 
	public int getBatterie() {
		return batterie;
	}
	@Override
	public String getNomeBreve() {
    	return "VanBa";
	}

	
	
	
}
