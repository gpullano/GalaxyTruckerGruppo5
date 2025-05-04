package tessere;

public abstract class Tessera {
	//attributi
	private Connettore latoDx;
	private Connettore latoSx;
	private Connettore latoSup;
	private Connettore latoDown;
	
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
//	TODO
//	public void ruota() {
//	}
}
