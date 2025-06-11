package plance;

public class Posizione {
	private final int colonna;
	private final int riga;
	
	public Posizione(int riga, int colonna) {
		this.colonna = colonna;
		this.riga = riga;
		
	}

	public int getColonna() {
		return colonna;
	}

	public int getRiga() {
		return riga;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posizione posizione = (Posizione) o;
        return riga == posizione.riga && colonna == posizione.colonna;
    }

    @Override
    public int hashCode() {
        // Usa una funzione hash standard per combinare i due interi
        return java.util.Objects.hash(riga, colonna);
    }

}
