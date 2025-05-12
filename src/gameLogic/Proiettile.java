package gameLogic;

public abstract class Proiettile {
	private final Provenienza provenienza;
	private final Dimensione dimensione;
	
	public Proiettile(Dimensione dimensione, Provenienza provenienza)
	{
		this.dimensione = dimensione;
		this.provenienza = provenienza;
	}

	public Dimensione getPotenza() {
		return dimensione;
	}

	public Provenienza getProvenienza() {
		return provenienza;
	}
}
