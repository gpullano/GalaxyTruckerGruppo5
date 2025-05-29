package tessere;

/** 
*rappresenta un supporto vitale per equipaggio marrone.
*/
public class SupportoVitaleMarrone extends Tessera {
	// attributo unico che è il colore 
	private final String colore;
	// costruttore ereditato dalla superclasse 'Tessera'
	
/**
*costruttore supportovitalemarrone
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*@param colore   colore associato
*/
	public SupportoVitaleMarrone(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,String colore) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.colore=colore;
	}

/**
*retituisce il colore assocciato al supporto vitale. 
*@retuurn colore stringa
*/
	public String getColore() {
		return colore;
}

/**
*retituisce il nome breve tessera
*@return stringa STVM
*/
	@Override
	public String getNomeBreve() {
    	return " STVM ";
	}

}
