package collezionabili;

import gameLogic.Colore;

/**
 * Rappresenta una merce con un colore associato.
 */
public class Merci {
    private Colore colore;

    /**
     * Costruttore della classe merci.
     * @param colore il colore della merce.
     */
    public Merci(Colore colore) {
        this.setColore(colore);
    }

    /**
     * Restituisce il colore della merce.
     * @return colore associato alla merce.
     */
    public Colore getColore() {
        return colore;
    }

    /**
     * Imposta il colore della merce.
     * @param colore il nuovo colore associato alla merce.
     */
    public void setColore(Colore colore) {
        this.colore = colore;
    }
    
    /**
     * Restituisce il valore in crediti di questa merce, basato sul suo colore.
     * @return il valore della merce.
     */
    public int getValore() {
        // Se il colore non è nullo, restituisce il suo valore, altrimenti 0.
        return (this.colore != null) ? this.colore.getValore() : 0;
    }
}
