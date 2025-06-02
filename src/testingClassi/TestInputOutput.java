package testingClassi;

import gameLogic.Colore;
import gameLogic.ConsoleIO;

public class TestInputOutput {

	public void test() {
		ConsoleIO inputOutput = new ConsoleIO();
		inputOutput.chiediAzioneAssemblaggio(Colore.ROSSO, false, true, true);
		inputOutput.chiediAzioneSulleTessere(Colore.GIALLO, false);
	}

}
