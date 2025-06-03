package tessere;

/**
*classe astratta che presenta una tessera composta da 4 connettori.
*/
public abstract class Tessera {
	//attributi
	private Connettore latoDx;
	protected Connettore latoSx;
	protected Connettore latoSup;
	private Connettore latoDown;
	
/**
*costruttore protetto per azionare una tessera attraverso i quattro connettori
*@param latoDx connettore destro
*@param latoSx connettore sinistro
*@param latoSup connettore superiore
*@param latoDown connettore inferiore
*@throws NullPointerException se un connettore è nullo.
*/
	//costruttore
	protected Tessera(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown)
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

/**
*restituisce il connettore sul lato destro.
*@return connettore destro
*/
	public Connettore getLatoDx() {
		return latoDx;
	}

/**
*restituisce il connettore sul lato sinistro.
*@return connettore sinistro.
*/
	public Connettore getLatoSx() {
		return latoSx;
	}

/**
*restituisce il connettore sul lato superiore.
*@return connettore superiore
*/
	public Connettore getLatoSup() {
		return latoSup;
	}

/**
*restituisce il connettore sul lato inferiore.
*@return connettore inferiore.
*/
	public Connettore getLatoDown() {
		return latoDown;
	}
	
	//metodi

/**
*ruota la tessera in senso antiorario.
*scambia i connettori in base al lato
*/
	public void ruota() {
		// gira in senso antiorario 
		Connettore temp=latoSup;
		latoSup=latoSx;
		latoSx=latoDown;
		latoDown=latoDx;
		latoDx=temp;
	}


/**
*restituisce in formato scritto la tessera
*vedendo i connettori e il nome breve
*/
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


/**
*restituisce il nome breve della tessera.
*@return nome breve
*/
	public String getNomeBreve() {
   		return this.getClass().getSimpleName().toUpperCase();
	}

	}
