package tessere;

import collezionabili.Merci;
/**
*rappresenta una tessera speciale.
*è di colore rosso e può avere solo 1 o 2 scomparti generati casualmente.
*estende la classe tessera
*/
public class StivaSpeciale extends Tessera {
	
	private final int scomparti;

/** 
*array di merci contenuti nella stiva
*/
	private Merci merci[];

/**
*costruttore per creare una stivaspeciale
*@param latoDx connettore destro
*@param latoSx connettore sinistro
*@param latoSup connettore superiore
*@param latoDown connettore inferiore
*@param scomparti numero scomparti 
*/
public StivaSpeciale(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int scomparti) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparti=scomparti;
		this.setMerci(new Merci[scomparti]);
	}

/**
*restituisce il numero scomparti nella stiva
*@return numero di scomparti
*/
	public int getScomparto() {
		return scomparti;
	}

/**
*restituisce l'array delle merci nella stiva
*@return array merci
*/
	public Merci[] getMerci() {
		return merci;
	}

/**
*imposta le merci all'interno della stiva
*@param merci array numero merci da assegnare
*/
	public void setMerci(Merci merci[]) {
			this.merci = merci;
	}

/**
*restituisce il nome breve della tessera
*@return stringa StivS
*/	
	@Override
	public String getNomeBreve() {
    	return " StivS ";
	}

}
