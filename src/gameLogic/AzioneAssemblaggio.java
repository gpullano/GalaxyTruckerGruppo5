package gameLogic;

import eccezioni.NumeroNonValidoException;

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

	private AzioneAssemblaggio(int numeroScelta) {
        this.numeroScelta = numeroScelta;
	 }
	
	public int getNumeroScelta() {
		return numeroScelta;
	}
	
	public static AzioneAssemblaggio fromNumero(int numero) throws NumeroNonValidoException{
        for (AzioneAssemblaggio azione : values()) {
            if (azione.getNumeroScelta() == numero) {
                return azione;
            }
        }
        throw new NumeroNonValidoException("Numero non valido. Reinseriscilo");
    }
}
