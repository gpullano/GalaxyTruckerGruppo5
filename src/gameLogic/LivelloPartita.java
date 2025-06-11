package gameLogic;

import eccezioni.NumeroNonValidoException;

/**
 * Rappresenta i diversi livelli o modalità di gioco.
 * Ogni livello è associato a un numero per facilitare la selezione dell'utente.
 */
public enum LivelloPartita {
    /** Livello di gioco 1. */
    LIVELLO1(1),   
    
    /** Livello di gioco 2. */
    LIVELLO2(2),  
    
    /** Livello di gioco 3. */
    LIVELLO3(3),
    
    /** Modalità di gioco completa "Trasvolata Intergalattica". */
    TRASVOLATA_INTERGALATTICA(4); 
	
	/** Il numero intero che identifica il livello. */
    private final int numeroLivello;

    /**
     * Costruttore privato per associare un numero al livello.
     * @param numeroLivello Il numero identificativo del livello.
     */
    private LivelloPartita(int numeroLivello) {
        this.numeroLivello = numeroLivello;
    }

    /**
     * Restituisce il numero intero associato a questo livello.
     * @return Il numero del livello.
     */
    public int getNumeroLivello() {
        return numeroLivello;
    }

    /**
     * Converte un numero intero nel corrispondente livello di partita.
     * @param numero Il numero da convertire.
     * @return Il livello di partita corrispondente.
     * @throws NumeroNonValidoException Se il numero non corrisponde a nessun livello valido.
     */
    public static LivelloPartita fromNumero(int numero) throws NumeroNonValidoException {
        for (LivelloPartita livello : values()) {
            if (livello.numeroLivello == numero) {
                return livello;
            }
        }
        throw new NumeroNonValidoException("Numero livello non valido: " + numero + ". I valori validi sono 1, 2, 3, 4.");
    }
}