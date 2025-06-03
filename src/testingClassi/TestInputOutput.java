package testingClassi;

import gameLogic.Colore;
import gameLogic.ConsoleIO;
import tessere.Cannone;
import tessere.Connettore;

public class TestInputOutput {

	public void test() {
		ConsoleIO inputOutput = new ConsoleIO();
		inputOutput.chiediAzioneAssemblaggio(Colore.ROSSO, false, true, true, false);
		inputOutput.chiediAzioneSulleTessere(Colore.GIALLO, false, new Cannone(
				Connettore.SINGOLO, 
				Connettore.CANNONE, 
				Connettore.DOPPIO, Connettore.NULLO), true);
	}

}
