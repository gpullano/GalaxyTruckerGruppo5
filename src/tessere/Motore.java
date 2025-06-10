package tessere;

/**
*rappresenta una tessera che fornisce potenza alla nave.
*/
public class Motore extends Tessera {
	private boolean attivo;  // true if the engine is active (not destroyed)


	// costruttore
	
/**
*costruttore motore
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*/
	public Motore(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.attivo = true;  // by default, the engine starts active
	}

	//getter

/**
*restituisce la potenza generata dal motore
*@return 1 valore fisso potenza
*/
        public int getPotenza() {
		return 1;
	}

	// getter/setter for attivo


/**
*verifica se il motore è attivo
*@return true se attivo se no false
*/    
    public boolean isAttivo() {
        return attivo;
    }

	
/**
*imposta stato motore.
*@param attivo true se no false
*/
    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

	//metodi

	
/**
*restituisce il nome breve della tessera.
*@return stringa motor
*/
    	@Override
	public String getNomeBreve() {
    	return " MOTOR ";
	}

}
