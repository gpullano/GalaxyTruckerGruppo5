package gameLogic;

import eccezioni.NumeroNonValidoException;

/**
*l'enum AzioneAssemblaggio rappresenta le diverse azioni che un giocatore può compiere durante la fase di assemblaggio.
*/
public enum AzioneAssemblaggio {
	//Sempre disponibili
	PESCA_TESSERA(1),
	//Disponibili dopo aver agganciato una tessera
	TERMINA_ASSEMBLAGGIO(2),
	GUARDA_MAZZETTI_CARTE(3),
	//Disponibile dopo aver prenotato una tessera
    PRENDI_TESSERA_PRENOTATA(4),
    //Disponibile dopo che ci sono tessere scoperte
    PRENDI_TESSERA_SCOPERTA(5),
    
    //Disponibili dopo aver preso una tessera
    //prenotata o non prenotata
    RUOTA_TESSERA(6),
    AGGANCIA_TESSERA(7),
    RIMETTI_TESSERA_A_POSTO(8),
    PRENOTA_TESSERA(9);
    
	
	private final int numeroScelta;

	/**
	*costruttore privato dell'enum.
	*@param numeroScelta il numero associato all'azione.
	*/
	private AzioneAssemblaggio(int numeroScelta) {
        this.numeroScelta = numeroScelta;
	 }
	
	/**
	*restituisce il numero associato all'azione.
	*@return il numero intero che rappresenta la scelta.
	*/
	public int getNumeroScelta() {
		return numeroScelta;
	}
	
	/**
	*converte un numero intero nell'azione di assemblaggio corrispondente.
	*@param numero il numero dell'azione da trovare.
	*@return l'azione di assemblaggio corrispondente al numero.
	*@throws NumeroNonValidoException se il numero non corrisponde a nessuna azione.
	*/
	public static AzioneAssemblaggio fromNumero(int numero) throws NumeroNonValidoException{
        for (AzioneAssemblaggio azione : values()) {
            if (azione.getNumeroScelta() == numero) {
                return azione;
            }
        }
        throw new NumeroNonValidoException("Numero non valido. Reinseriscilo");
    }
}