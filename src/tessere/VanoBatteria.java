package tessere;


/** 
*rappresenta una tessera contenente un vano batteria.
* fornisce energia ad altre tessere
*/
public class VanoBatteria extends Tessera {
	// attributo
	private int batterie;
	
	
/**
*costruttore vanobatteria
*@param latoDx connettori lato destro
*@param latoSx connettori lato sinistro
*@param latoSup connettori lato superiori
*@param latoDown connettori lato inferiori
*@param batterie  quantità batterie presenti
*/
	//costruttore ereditato superclasse
	public VanoBatteria(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int batterie) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.batterie=batterie;
	}

	
/**
*retituisce il numero di batterie nel vano.
*@return numero batterie
*/
//getter 
	public int getBatterie() {
		return batterie;
	}

/**
*retituisce il nome breve tessera
*@return stringa VanBa
*/ 
	@Override
	public String getNomeBreve() {
    	return "VanBa";
	}

	
	
	
}




	

	



	
	


	
