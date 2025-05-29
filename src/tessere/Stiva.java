package tessere;

/**
*rappresenta una tessera usata per immagazzinare merci.
*può avere 2 o 3 scomparti
*/
public class Stiva extends Tessera {
	//può avere dai 2 ai 3 scomparti da generare casualmente 
	private final int scomparti;

	
/**
*costruttore stiva
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*/
	public Stiva(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,int scomparti) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparti=scomparti;
		
		
	}

/** 
*restituisce il numero di scomparti della stiva.
*@return numero scomparti
*/
	public int getScomparto() {
		return scomparti;
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
