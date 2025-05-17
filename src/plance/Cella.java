package plance;

public class Cella {
	
	private final char freccia;
	
	public Cella(char freccia) {
		this.freccia = freccia;
	}

	public char getFreccia() {
		return freccia;
	}
	
	@Override 
	public String toString() {
		return String.valueOf(freccia);
	}

}
