package tessere;

import java.util.ArrayList;
import java.util.List;
import collezionabili.Merci;

/**
 * Rappresenta una tessera usata per immagazzinare merci.
 * Ogni stiva ha una sua lista interna di merci.
 */
public class Stiva extends Tessera {
    private static final int CAPIENZA_MAX = 3; // ogni stiva ha tre merci
    private List<Merci> scomparti; 

    public Stiva(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
        super(latoDx, latoSx, latoSup, latoDown);
        this.scomparti = new ArrayList<>(CAPIENZA_MAX); 
    }

    // --- Metodi per gestire le merci della stiva

    public int getCapienzaMassima() {
        return CAPIENZA_MAX;
    }
    
    public int getMerciContenute() {
        return this.scomparti.size();
    }

    public int getSpazioDisponibile() {
        return CAPIENZA_MAX - this.scomparti.size();
    }

    public boolean isPiena() {
        return this.scomparti.size() >= CAPIENZA_MAX;
    }
    
    /**
     * Aggiunge una merce a questa stiva, se c'è spazio.
     * @param merce La merce da aggiungere.
     * @return true se la merce è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiMerce(Merci merce) {
        if (!isPiena()) {
            this.scomparti.add(merce);
            return true;
        }
        return false;
    }

    /**
     * Rimuove e restituisce la prima merce trovata in questa stiva.
     * @return L'oggetto Merci rimosso, o null se la stiva è vuota.
     */
    public Merci rimuoviMerce() {
        if (!scomparti.isEmpty()) {
            return this.scomparti.remove(0); // Rimuove il primo elemento
        }
        return null;
    }
    
    /**
     * Restituisce una copia della lista di merci per la visualizzazione,
     * per evitare modifiche esterne non controllate.
     */
    public List<Merci> getMerci() {
        return new ArrayList<>(this.scomparti);
    }

    @Override
    public String getNomeBreve() {
        return " Stiva ";
    }
}