package tessere;

/**
*La classe Cabina rappresenta una tessera contenente membri dell'equipaggio e alieni.
*Estende la classe e aggiunge attributi per controllare l'equipaggio e gli alieni.
*/
public class Cabina extends Tessera {
	private static final int NUM_EQUIPAGGIO = 2;
	//attributi
	private int equipaggio;
	private int alieni;

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
		this.setEquipaggio(0);
		this.setAlieni(0);
	}

/**
* Restituisce il numero dei membri dell'equipaggio nella cabina.
*@return numero membri equipaggio.
*/
	//getter e setter
	public int getEquipaggio() {
		return equipaggio;
	}

/**
*Imposta il numero dei membri dell'equipaggio nella cabina.
*@param equipaggio il numero membri equipaggio.
*/
	public void setEquipaggio(int equipaggio) {
		this.equipaggio = equipaggio;
	}

/**
* Restituisce il numero alieni nella cabina.
*@return numero alieni.
*/
	public int getAlieni() {
		return alieni;
	}

/**
*Imposta il numero degli alieni nella cabina.
*@param alieni il numero membri equipaggio.
*/
	public void setAlieni(int alieni) {
		this.alieni = alieni;
	}
	//Methodi
	
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

	
	
	
	

}
