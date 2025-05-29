package gameLogic;

public enum AzioneAssemblaggio {
	PESCA_TESSERA,        		  	// Pesca dal mucchio comune a faccia in giù
    PRENDI_TESSERA_SCOPERTA,     	// Prende una tessera già a faccia in su sul tavolo
    AGGANCIA_TESSERA_NON_PRENOTATA, // Opzione dopo aver preso/pescato una tessera
    RUOTA_TESSERA,       			// Opzione dopo aver preso/pescato una tessera
    PRENOTA_TESSERA,     			// Opzione dopo aver preso/pescato una tessera
    RIMETTI_TESSERA_SUL_TAVOLO,     // Scarta la tessera corrente tra quelle scoperte
    AGGANCIA_TESSERA_PRENOTATA,   	// Sceglie una tessera prenotata e la aggancia
    GUARDA_MAZZI_CARTE,
    TERMINA_ASSEMBLAGGIO;
}
