package tessere;

public class StivaSpeciale extends Tessera {
	/* la stiva speciale è di colore rosso e può avere solo 1 o 2 scomparti, sempre generati casualmente */
	private final int scomparti;
	private Merci merci[];

	public StivaSpeciale(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown, int scomparti) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.scomparti=scomparti;
		this.setMerci(new Merci[scomparti]);
	}


	public int getScomparto() {
		return scomparti;
	}


	public Merci[] getMerci() {
		return merci;
	}


	public void setMerci(Merci merci[]) {
			this.merci = merci;
	}

}
