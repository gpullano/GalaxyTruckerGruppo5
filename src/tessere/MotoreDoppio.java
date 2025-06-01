package tessere;

/** 
*rappresenta una tessea che può  essere attivata per dare maggiore potenza
*/
public class MotoreDoppio extends Tessera {
	
/**
*costruttore motoredoppio
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*/	
// costruttore
	public MotoreDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}
	
	
/**
*restituisce la potenza del motore doppio
*@return potenza pari a 2
*/
// getter
	public int getPotenza() {
		return 2;
	}

/**
*restituisce il nome breve della tessera
*@return string MotoD
*/
        @Override
	public String getNomeBreve() {
    	return " MotoD ";
	}

}
