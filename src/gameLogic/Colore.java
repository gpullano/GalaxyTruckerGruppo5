package gameLogic;

/**
*l'enum Colore rappresenta i colori delle merci con il loro valore associato.
*/
public enum Colore {
    // Assegna un valore a ogni colore, come da regole
    ROSSO(4),
    GIALLO(3),
    VERDE(2),
    BLU(1);

    private final int valore;

    /**
     *costruttore privato dell'enum.
     *@param valore il valore associato al colore.
     */
    private Colore(int valore) {
        this.valore = valore;
    }

    /**
     *restituisce il valore associato al colore.
     *@return il valore del colore.
     */
    public int getValore() {
        return this.valore;
    }

    /**
    *converte una stringa nel colore corrispondente.
    *@param input la stringa da convertire.
    *@return il Colore corrispondente o null se non c'è corrispondenza.
    */
    public static Colore fromStringSemplice(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        String cleanedInput = input.trim().toUpperCase();

        for (Colore colore : Colore.values()) {
            if (colore.name().equals(cleanedInput)) { 
                return colore; 
            }
        }
        return null;
    }
}