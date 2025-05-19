package gameLogic;

public enum LivelloPartita {
    LIVELLO1(1),   
    LIVELLO2(2),  
    LIVELLO3(3),
    TRASVOLATA_INTERGALATTICA(4); 
	
	//attributi
    private final int numeroLivello;

    // Costruttore (è privato perché usato solo all'interno dell'enum)
    private LivelloPartita(int numeroLivello) {
        this.numeroLivello = numeroLivello;
    }

    public int getNumeroLivello() {
        return numeroLivello;
    }

    // Metodo statico per ottenere l'enum dal numero intero
    public static LivelloPartita fromNumero(int numero) {
        for (LivelloPartita livello : values()) {
            if (livello.numeroLivello == numero) {
                return livello; // Trovato il valore corrispondente
            }
        }
        throw new IllegalArgumentException("Numero livello non valido: " + numero + ". I valori validi sono 1, 2, 3, 4.");
    }
}
