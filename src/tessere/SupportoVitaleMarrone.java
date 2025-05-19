package tessere;

public class SupportoVitaleMarrone extends Tessera {
	// attributo unico che è il colore 
	private final String colore;
	// costruttore ereditato dalla superclasse 'Tessera'
	public SupportoVitaleMarrone(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,String colore) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.colore=colore;
	}
	public String getColore() {
		return colore;
	}
	@Override
	public String getNomeBreve() {
    	return "STVM";
	}

}
