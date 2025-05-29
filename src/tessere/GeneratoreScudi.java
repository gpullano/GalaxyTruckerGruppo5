package tessere;

/**
* generatore scudi per proteggere la nave.
*/
public class GeneratoreScudi extends Tessera implements Attivabile{ 
	
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
*attiva il generatore scudi se alimentato.
*@param energia true se alimentato se no false
*/
	@Override
	public void attiva(boolean energia) {
		System.out.println("Ti sei difeso con lo scudo!");
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
