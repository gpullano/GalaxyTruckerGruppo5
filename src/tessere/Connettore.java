package tessere;

public enum Connettore {
	// tipologie di connettori
	UNIVERSALE("U"),
    DOPPIO("D"),
    SINGOLO("S"),
    CANNONE("+"),
    MOTORE("M"),
    CANNONEDOPPIO("C2"),
    MOTOREDOPPIO("M2"),
    SCUDO("()"),
	NULLO("--");
	
	private final String simbolo;

    // Costruttore
    private Connettore(String simbolo) {
        this.simbolo = simbolo;
    }

    // Metodo toString sovrascritto per mostrare il simbolo
    @Override
    public String toString() {
        return simbolo;
    }
	
}
