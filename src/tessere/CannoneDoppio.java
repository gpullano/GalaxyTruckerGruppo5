package tessere;

/**
*la classe cannonedoppio rappresenta una tessera attacco che si attiva con doppia potenza di fuoco.
*/
public class CannoneDoppio extends Tessera {
	
/**
*costruttore della classe cannonedoppio
*@param latoDx  connettore lato destro
*@param latoSx  connettore lato sinistro
*@param latoSup connettore lato superiore
*@param latoDown connettore lato inferiore 
*/
	// costruttore
	public CannoneDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

/**
*restituisce la potenza di fuoco del cannonedoppio. 
*@return 2 se il lato superiore è un cannone se no 1.
*/
	public int getSparo() {
		if(this.getLatoSup() == Connettore.CANNONE) {
			return 2;
		}
		return 1;
	}
	
/**
*restituisce il nome breve della tessera.
*@return stringa CannD
*/
	@Override
	public String getNomeBreve() {
    	return " CannD ";
	}
}
