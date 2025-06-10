package tessere;

/**
*La classe Cabina rappresenta una tessera contenente membri dell'equipaggio e alieni.
*Estende la classe e aggiunge attributi per controllare l'equipaggio e gli alieni.
*/
public class Cabina extends Tessera {
	private static final int NUM_EQUIPAGGIO = 2;
	//attributi
	private int equipaggio;
	private boolean alienoViola;
	private boolean alienoMarrone;

	/**
	*Costruttore della classe cabina.
	*Inizializza i connettori e imposta a zero l'equipaggio e alieni.
	*@param LatoDX   connettore sul lato destro
	*@param LatoSX   connettore sul lato sinistro
	*@param LatoSup  connettore sul lato superiore
	*@param LatoDown connettore sul lato inferiore
	*/	
	//costruttore
	public Cabina(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.equipaggio = 0;
		
	}

	/**
	* Restituisce il numero dei membri dell'equipaggio nella cabina.
	*@return numero membri equipaggio.
	*/
	public int getEquipaggio() {
		return equipaggio;
	}

	/**
	*Imposta il numero dei membri dell'equipaggio nella cabina.
	*@param equipaggio il numero membri equipaggio.
	*/
	public void setEquipaggio(int num) {
		if(num < 0) {
			throw new IllegalArgumentException("Non puoi inserire un equipaggio minore di zero.");
		}
		this.equipaggio = num;
	}

	/**
	 * @return the alienoViola
	 */
	public boolean isAlienoViola() {
		return alienoViola;
	}

	/**
	 * @param alienoViola the alienoViola to set
	 */
	public void setAlienoViola(boolean alienoViola) {
		this.alienoViola = alienoViola;
	}

	/**
	 * @return the alienoMarrone
	 */
	public boolean isAlienoMarrone() {
		return alienoMarrone;
	}

	/**
	 * @param alienoMarrone the alienoMarrone to set
	 */
	public void setAlienoMarrone(boolean alienoMarrone) {
		this.alienoMarrone = alienoMarrone;
	}
	
	/**
	*Da il nome breve della cabina.
	*@return la stringa CABIN.
	*/
	@Override
	public String getNomeBreve() {
    	return " CABIN ";
	}

	/**
	 * @return il numEquipaggio
	 */
	public static int getNumEquipaggio() {
	return NUM_EQUIPAGGIO;
	}

	public void aggiungiEquipaggio(int equipaggio) {
		if(this.equipaggio - equipaggio < 0) {
			this.equipaggio = 0;
		} else {
			this.equipaggio -= equipaggio;
		}
	}

}
