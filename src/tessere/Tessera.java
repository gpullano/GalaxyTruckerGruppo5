package tessere;

public abstract class Tessera {
	//attributi
	private final Connettore latoDx;
	private final Connettore latoSx;
	private final Connettore latoSup;
	private final Connettore latoDown;
	
	//costruttore
	public Tessera(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown)
	{
		this.latoDx = latoDx;
		this.latoSx = latoSx;
		this.latoSup = latoSup;
		this.latoDown = latoDown;
	}

	//getter e setter
	public Connettore getLatoDx() {
		return latoDx;
	}

	public Connettore getLatoSx() {
		return latoSx;
	}

	public Connettore getLatoSup() {
		return latoSup;
	}

	public Connettore getLatoDown() {
		return latoDown;
	}
	
	//metodi
}
