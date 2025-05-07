package gameLogic;

public class Cannonata {
	private final Potenza potenza;
	private final Provenienza provenienza;
	
	public Cannonata(Potenza potenza, Provenienza provenienza)
	{
		this.potenza = potenza;
		this.provenienza = provenienza;
	}

	public Potenza getPotenza() {
		return potenza;
	}

	public Provenienza getProvenienza() {
		return provenienza;
	}
}
