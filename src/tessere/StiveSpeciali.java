package tessere;

public class StiveSpeciali extends Tessera {
	/* la stiva speciale è di colore rosso e può avere solo 1 o 2 scomparti, sempre generati casualmente */
	private final int scomparti;

	public StiveSpeciali(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int scomparti) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparti=scomparti;
	}


	public int getScomparto() {
		return scomparti;
	}

}
