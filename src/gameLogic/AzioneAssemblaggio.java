package gameLogic;

import eccezioni.NumeroNonValidoException;

/**
 * Rappresenta le azioni possibili durante la fase di assemblaggio del gioco.
 * Ogni azione è associata a un numero intero per la selezione da menu.
 */
public enum AzioneAssemblaggio {
    /** Azione per pescare una tessera. */
    PESCA_TESSERA(1),

    /** Azione per terminare il proprio turno di assemblaggio. */
    TERMINA_ASSEMBLAGGIO(2),

    /** Azione per guardare i mazzetti delle carte obiettivo. */
    GUARDA_MAZZETTI_CARTE(3),

    /** Azione per prendere una tessera precedentemente prenotata. */
    PRENDI_TESSERA_PRENOTATA(4),

    /** Azione per prendere una delle tessere scoperte. */
    PRENDI_TESSERA_SCOPERTA(5),

    /** Azione per ruotare la tessera in mano. */
    RUOTA_TESSERA(6),

    /** Azione per agganciare la tessera in mano al regno. */
    AGGANCIA_TESSERA(7),

    /** Azione per rimettere a posto una tessera presa. */
    RIMETTI_TESSERA_A_POSTO(8),

    /** Azione per prenotare la tessera in mano. */
    PRENOTA_TESSERA(9);

    /** Numero intero associato all'azione. */
    private final int numeroScelta;

    /**
     * Costruttore privato per associare un numero all'azione.
     * @param numeroScelta Il numero che identifica l'azione.
     */
    private AzioneAssemblaggio(int numeroScelta) {
        this.numeroScelta = numeroScelta;
    }

    /**
     * Restituisce il numero di scelta associato all'azione.
     * @return Il numero intero dell'azione.
     */
    public int getNumeroScelta() {
        return numeroScelta;
    }

    /**
     * Converte un numero intero nella corrispondente azione.
     * @param numero Il numero da convertire.
     * @return L'azione {@code AzioneAssemblaggio} corrispondente.
     * @throws NumeroNonValidoException Se il numero non corrisponde a nessuna azione.
     */
    public static AzioneAssemblaggio fromNumero(int numero) throws NumeroNonValidoException {
        for (AzioneAssemblaggio azione : values()) {
            if (azione.getNumeroScelta() == numero) {
                return azione;
            }
        }
        throw new NumeroNonValidoException("Numero non valido. Reinseriscilo");
    }
}