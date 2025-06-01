package gameLogic;

public enum AzioneAssemblaggio {
	PESCA_TESSERA(1),
	PRENOTA_TESSERA(2),
	TERMINA_ASSEMBLAGGIO(3),
    AGGANCIA_TESSERA_NON_PRENOTATA(4),
    RUOTA_TESSERA(5),
    RIMETTI_TESSERA_SUL_TAVOLO(6),
    AGGANCIA_TESSERA_PRENOTATA(7),
    PRENDI_TESSERA_SCOPERTA(2),
    GUARDA_MAZZI_CARTE(8);
	
	private final int numeroScelta;

	private AzioneAssemblaggio(int numeroScelta) {
        this.numeroScelta = numeroScelta;
	 }
	
	public int getNumeroScelta() {
		return numeroScelta;
	}
	
	public static AzioneAssemblaggio fromNumero(int numero){
        for (AzioneAssemblaggio azione : values()) {
            if (azione.getNumeroScelta() == numero) {
                return azione;
            }
        }
        throw new IllegalArgumentException("Numero non valido.");
    }
}
