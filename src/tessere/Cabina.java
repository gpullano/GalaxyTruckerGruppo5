package tessere;

public class Cabina extends Tessera {
	//attributi
	private int equipaggio;
	private int alieni;
	
	//costruttore
	public Cabina(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.setEquipaggio(0);
		this.setAlieni(0);
	}

	//getter e setter
	public int getEquipaggio() {
		return equipaggio;
	}

	public void setEquipaggio(int equipaggio) {
		this.equipaggio = equipaggio;
	}

	public int getAlieni() {
		return alieni;
	}

	public void setAlieni(int alieni) {
		this.alieni = alieni;
	}
	
	
	
	
	//metodi
	

}
