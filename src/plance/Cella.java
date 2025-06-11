package plance;

public class Cella {
	
	private final char simbolo;
	
	public Cella(char simbolo) {
		this.simbolo = simbolo;
	}

	public char getSimbolo() {
		return simbolo;
	}
	
	@Override 
	public String toString() {
		return String.valueOf(simbolo);
	}

}
