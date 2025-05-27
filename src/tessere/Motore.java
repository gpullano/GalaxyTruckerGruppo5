package tessere;

public class Motore extends Tessera {
	private boolean attivo;  // true if the engine is active (not destroyed)


	// costruttore
	
	public Motore(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.attivo = true;  // by default, the engine starts active
	}

	//getter

	public int getPotenza() {
		return 1;
	}

	// getter/setter for attivo

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

	//metodi

	@Override
	public String getNomeBreve() {
    	return " Motor ";
	}

}
