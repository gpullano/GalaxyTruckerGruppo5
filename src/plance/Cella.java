package plance;

public class Cella {
	
	private final char freccia;
	private final int num;
	
	public Cella(char freccia) {
		this.freccia = freccia;
		this.num = 0;
	}

	public char getFreccia() {
		return freccia;
	}

	public int getNum() {
		return num;
	}
	
	@Override 
	public String toString() {
		return String.valueOf(freccia);
	}

}
