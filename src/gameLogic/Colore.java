package gameLogic;

public enum Colore {
    // Assegna un valore a ogni colore, come da regole
    ROSSO(4),
    GIALLO(3),
    VERDE(2),
    BLU(1);

    private final int valore;

    /**
     * Costruttore privato dell'enum per associare il valore.
     * @param valore Il valore in crediti della merce di questo colore.
     */
    private Colore(int valore) {
        this.valore = valore;
    }

    /**
     * Restituisce il valore in crediti di questo colore.
     * @return il valore della merce.
     */
    public int getValore() {
        return this.valore;
    }

    // Il tuo metodo statico esistente va benissimo e non necessita modifiche
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