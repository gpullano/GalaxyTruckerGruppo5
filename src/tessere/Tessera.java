package tessere;

public abstract class Tessera {
	//attributi
	private Connettore latoDx;
	protected Connettore latoSx;
	protected Connettore latoSup;
	private Connettore latoDown;
	
	//costruttore
	public Tessera(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown)
	{
		this.latoDx = latoDx;
		this.latoSx = latoSx;
		this.latoSup = latoSup;
		this.latoDown = latoDown;
		if(this.latoDown == null || this.latoDx == null || this.latoSup == null || this.latoSx == null) {
			throw new NullPointerException("I lati della tessera devono essere non nulli");
		}
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

	public void ruota() {
		// gira in senso antiorario 
		Connettore temp=latoSup;
		latoSup=latoSx;
		latoSx=latoDown;
		latoDown=latoDx;
		latoDx=temp;
	}
	@Override
	public String toString() {
		String sup = (getLatoSup() != null) ? getLatoSup().toString() : " ";
		String inf = (getLatoDown() != null) ? getLatoDown().toString() : " ";
		String sx = (getLatoSx() != null) ? getLatoSx().toString() : " ";
		String dx = (getLatoDx() != null) ? getLatoDx().toString() : " ";

		String nome = getNomeBreve();//get the name of every unic class

		String risultato = "";
		risultato += "    " + sup + "\n";
		risultato += sx + nome + dx + "\n";
		risultato +="    "+ inf;

		return risultato;
			 
	}
	//method to get the name of every class
	public String getNomeBreve() {
   		return this.getClass().getSimpleName().toUpperCase();
	}

	}
