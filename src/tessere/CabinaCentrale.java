package tessere;

public class CabinaCentrale extends Tessera {
	// attributi 
	private final Colore colore;
	private int equipaggio;
	// non possiede alieni
	

	public CabinaCentrale(Colore colore) {
		super(Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE, Connettore.UNIVERSALE);
		this.setEquipaggio(2);
		this.colore=colore;
			
	}


	public int getEquipaggio() {
		return equipaggio;
	}


	public void setEquipaggio(int equipaggio) {
		this.equipaggio = equipaggio;
	}


	public Colore getColore() {
		return colore;
	}


	

}
