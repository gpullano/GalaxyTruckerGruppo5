package tessere;

import gameLogic.Colore;

/**
* La classe CabinaCentrale rappresenta una tessera centrale della nave.
*contiene due membri dell'equipaggio iniziale ed è associata ad un colore specifico.
*/

//TODO - valutare che cabinaCentrale possa estendere cabina
public class CabinaCentrale extends Tessera {
	// attributi 
	private static final int NUM_EQUIPAGGIO = 2;
	private final Colore colore;
	private int equipaggio;
	// non possiede alieni


/**
*costruttore della cabinacentrale.
*imposta i connettori universali e imposta 2 membri dell'equipaggio.
*@param colore il colore assocciato alla cabina centrale.
*/
	public CabinaCentrale(Colore colore) {
		super(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE);
		this.setEquipaggio(NUM_EQUIPAGGIO);
		this.colore=colore;

	}

/**
*restituisce il numero di membri dell'equipaggio nella cabina centrale.
*@return numero membri equipaggio
*/
	public int getEquipaggio() {
		return equipaggio;
	}

/**
*imposta il numero di membri dell'equipaggio nella cabina centrale.
*@param equipaggio numero da assegnare
*/
	public void setEquipaggio(int equipaggio) {
		this.equipaggio = equipaggio;
	}

/**
*restituisce il colore della cabina centrale.
*@return colore cabina
*/
       public Colore getColore() {
		return colore;
	}

/**
*restituisce il nome breve della tessera.
*@return stringa CabCen
*/
	@Override
	public String getNomeBreve() {
    	return "CabCen";
	}
	
	public void aggiungiEquipaggio(int equipaggio) {
		if(this.equipaggio - equipaggio < 0) {
			this.equipaggio = 0;
		} else {
			this.equipaggio -= equipaggio;
		}
	}
}
