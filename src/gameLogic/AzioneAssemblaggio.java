package gameLogic;

import eccezioni.InputNonValidoException;

public enum AzioneAssemblaggio {
	//Sempre disponibili
	PESCA_TESSERA(1),
	PRENOTA_TESSERA(2),
	//Disponibili dopo aver agganciato una tessera
	TERMINA_ASSEMBLAGGIO(3),
	GUARDA_MAZZI_CARTE(4),
	//Disponibile dopo aver prenotato una tessera
    PRENDI_TESSERA_PRENOTATA(5),
    //Disponibile dopo che ci sono tessere scoperte
    PRENDI_TESSERA_SCOPERTA(6),
    
    //Disponibili dopo aver preso una tessera
    //prenotata o non prenotata
    RIMETTI_TESSERA_SUL_TAVOLO(7),
    RUOTA_TESSERA(8),
    AGGANCIA_TESSERA(9);
	
	private final int numeroScelta;

	private AzioneAssemblaggio(int numeroScelta) {
        this.numeroScelta = numeroScelta;
	 }
	
	public int getNumeroScelta() {
		return numeroScelta;
	}
	
	public static AzioneAssemblaggio fromNumero(int numero) throws InputNonValidoException{
        for (AzioneAssemblaggio azione : values()) {
            if (azione.getNumeroScelta() == numero) {
                return azione;
            }
        }
        throw new InputNonValidoException("Numero non valido.");
    }
}
