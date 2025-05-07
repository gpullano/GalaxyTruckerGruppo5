package gameLogic;

public class Meteorite {
	private final Dimensione dimensione;
	private final Provenienza provenienza;
	
	public Meteorite(Dimensione dimensione, Provenienza provenienza)
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
