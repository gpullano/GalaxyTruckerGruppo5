package tessere;

/**
*rappresenta una tesser per i collegamenti.
*/
public class ModuliStrutturali extends Tessera{

/**
*costruttore moduli strutturali
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*/
	public ModuliStrutturali(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		
	}

/**
*restituisce il nome breve della tessera.
*@return stringa ModSt
*/
	@Override
	public String getNomeBreve() {
    	return " ModSt ";
	}
}
