package tessere;


/**
*rappresenta una tessera usata per immagazzinare merci.
*può avere 2 o 3 scomparti
*/
public class Stiva extends Tessera {
	//La stiva per motivi di tempo è stata inizializzata a tre scomparti
	private static final int N_SCOMPARTI = 3;

	
/**
*costruttore stiva
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*/
	public Stiva(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

/** 
*restituisce il numero di scomparti della stiva.
*@return numero scomparti
*/
	public static int getScomparti() {
		return N_SCOMPARTI;
	}

/** 
*restituisce il nome breve della tessera
*@return stringa stiva
*/
	@Override
	public String getNomeBreve() {
    	return " Stiva ";
	}
}
