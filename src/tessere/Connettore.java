package tessere;

/**
*enum che rappresenta le varie tipologie di connettori.
*ogni connettore è associato ad un simbolo stringa .
*/
public enum Connettore {
	// tipologie di connettori
    UNIVERSALE("U"),
    DOPPIO("D"),
    SINGOLO("S"),
    CANNONE("+"),
    MOTORE("M"),
    CANNONEDOPPIO("+2"),
    MOTOREDOPPIO("M2"),
    SCUDO("()"),
	NULLO("--");


    private final String simbolo;

	/**
	*costruttore privato dell'enum.
	*@param simbolo simbolo stringa associato al connettore.
	*/	
    // Costruttore
    private Connettore(String simbolo) {
        this.simbolo = simbolo;
    }


	/**
	* restituisce il simbolo connettore sotto forma di stringa.
	*@return simbolo connettore.
	*/
    // Metodo toString sovrascritto per mostrare il simbolo
    @Override
    public String toString() {
        return simbolo; // Esempio: restituisce "S", non " S "
    }
	
}
