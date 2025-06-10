package tessere;

/**
*la classe cannone rappresenta una tessera con funzione attacco.
*spara con potenza diversa in base al tipo di connettore che c'è superiormente. 
*/
public class Cannone extends Tessera {

/**
* costruttore della classe cannone.
*@param latoDx connettore lato destro
*@param latoSx connettore lato sinistro
*@param latoSup connettore lato superiore
*@param latoDown connettore lato inferiore
*/
	//costruttore
	public Cannone(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

/**
*restituisce la potenza di fuoco del cannone.
*@return 1 se il lato supeiore è un cannone ,altrimenti 0,5.
*/
	// getter
	public float getSparo() {
		if (this.getLatoSup() == Connettore.CANNONE)
			return 1;
		else
			return 0.5f;
	}

/**
*restituisce il nome breve della tessera.
*@return stringa canon. 
*/
	@Override
	public String getNomeBreve() {
    	return " Canno ";
	}

}
