package tessere;

public class Stive extends Tessera {
	//può avere dai 2 ai 3 scomparti da generare casualmente 
	private final int scomparti;
	
	public Stive(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,int scomparti) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparti=scomparti;
		
		
	}

	public int getScomparto() {
		return scomparti;
	}




}
