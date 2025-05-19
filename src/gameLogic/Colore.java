package gameLogic;

public enum Colore {
    ROSSO, GIALLO, VERDE, BLU;


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
