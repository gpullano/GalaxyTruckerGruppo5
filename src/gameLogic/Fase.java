package gameLogic;

import java.util.List;
import plance.PlanceVolo;

/**
 * Classe astratta che rappresenta una fase generica del gioco.
 * Fornisce una struttura base e dati comuni a tutte le fasi specifiche (es. assemblaggio, volo).
 */
public abstract class Fase {
    private List<Giocatore> giocatori;
    private ConsoleIO inputOutput;
    private PlanceVolo planceVolo;

    /**
     * Costruttore per una fase di gioco.
     * @param giocatori La lista di giocatori partecipanti.
     * @param inputOutput L'oggetto per l'interazione con la console.
     * @param planceVolo La plancia di volo comune a tutti i giocatori.
     */
    protected Fase(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
        this.setGiocatori(giocatori);
        this.setInputOutput(inputOutput);
        this.setPlanceVolo(planceVolo);
    }

    /**
     * Metodo astratto che definisce la logica principale della fase.
     * Deve essere implementato da ogni sottoclasse specifica.
     */
    public abstract void eseguiFase();

    /**
     * Restituisce la lista dei giocatori.
     * @return La lista dei giocatori.
     */
    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    /**
     * Imposta la lista dei giocatori.
     * @param giocatori La nuova lista di giocatori.
     */
    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    /**
     * Restituisce l'oggetto di input/output.
     * @return L'oggetto ConsoleIO.
     */
    public ConsoleIO getInputOutput() {
        return inputOutput;
    }

    /**
     * Imposta l'oggetto di input/output.
     * @param inputOutput Il nuovo oggetto ConsoleIO.
     */
    public void setInputOutput(ConsoleIO inputOutput) {
        this.inputOutput = inputOutput;
    }

    /**
     * Restituisce la plancia di volo.
     * @return La plancia di volo.
     */
    public PlanceVolo getPlanceVolo() {
        return planceVolo;
    }

    /**
     * Imposta la plancia di volo.
     * @param planceVolo La nuova plancia di volo.
     */
    public void setPlanceVolo(PlanceVolo planceVolo) {
        this.planceVolo = planceVolo;
    }
}