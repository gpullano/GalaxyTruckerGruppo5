package tessere;

/**
* generatore scudi per proteggere la nave.
*/
public class GeneratoreScudi extends Tessera{ 
	
/**
*costruttore del generatorescudi.
*@param latoDx connettore lato destro
*@param latoSx connettore lato sinistro
*@param latoSup connettore lato superiore
*@param latoDown connettore lato inferiore
*/
	public GeneratoreScudi(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

/**
*restituisce il nome breve della tessera.
*@return stringa scudo
*/
	@Override
	public String getNomeBreve() {
    	return " Scudo ";
	}

}
