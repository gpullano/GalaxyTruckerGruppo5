package tessere;

public class Stive extends Tessera {
	private final int scomparto;
	private final String colore;
	
	public Stive(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,int scomparto) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparto=scomparto;
		this.colore="blu";
		
	}

	public int getScomparto() {
		return scomparto;
	}

	public String getColore() {
		return colore;
	}

}
