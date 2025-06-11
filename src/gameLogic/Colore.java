package gameLogic;

/**
 * Rappresenta i colori utilizzati nel gioco, ciascuno con un valore numerico associato.
 * Viene usato per merci, giocatori o altri elementi di gioco.
 */
public enum Colore {
    /** Colore Rosso. */
    ROSSO(4),
    /** Colore Giallo. */
    GIALLO(3),
    /** Colore Verde. */
    VERDE(2),
    /** Colore Blu. */
    BLU(1);

    /** Il valore numerico del colore. */
    private final int valore;

    /**
     * Costruttore privato per associare un valore a ogni colore.
     * @param valore Il valore del colore (es. in crediti).
     */
    private Colore(int valore) {
        this.valore = valore;
    }

    /**
     * Restituisce il valore associato al colore.
     * @return il valore numerico del colore.
     */
    public int getValore() {
        return this.valore;
    }

    /**
     * Converte una stringa nel colore corrispondente (ignora maiuscole/minuscole).
     * @param input La stringa da convertire (es. "ROSSO").
     * @return Il {@code Colore} corrispondente, o {@code null} se non trovato.
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