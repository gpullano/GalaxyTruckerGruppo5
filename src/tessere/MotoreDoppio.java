package tessere;

/** 
*rappresenta una tessea che può  essere attivata per dare maggiore potenza
*/
public class MotoreDoppio extends Tessera implements Attivabile{
	
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
*attiva il motore doppio se viene data energia.
*@param energia true per attivare il motore se no false
*/
// metodi 
	@Override
	public void attiva(boolean energia) {
			if(energia) {
				getPotenza();
			}
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
