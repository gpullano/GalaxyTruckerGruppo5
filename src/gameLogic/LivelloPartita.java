package gameLogic;

import eccezioni.NumeroNonValidoException;

/**
*l'enum LivelloPartita rappresenta i diversi livelli di difficoltà del gioco.
*/
public enum LivelloPartita {
    LIVELLO1(1),   
    LIVELLO2(2),  
    LIVELLO3(3),
    TRASVOLATA_INTERGALATTICA(4); 
	
	//attributi
    private final int numeroLivello;

    /**
    *costruttore privato dell'enum.
    *@param numeroLivello il numero associato al livello.
    */
    private LivelloPartita(int numeroLivello) {
        this.numeroLivello = numeroLivello;
    }

    /**
    *restituisce il numero che rappresenta il livello.
    *@return il numero del livello.
    */
    public int getNumeroLivello() {
        return numeroLivello;
    }

    /**
    *converte un numero intero nel livello di partita corrispondente.
    *@param numero il numero del livello da trovare.
    *@return il livello di partita corrispondente.
    *@throws NumeroNonValidoException se il numero non corrisponde a nessun livello.
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