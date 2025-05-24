package galaxytrucker;

import gameLogic.ConsoleIO;
import gameLogic.Gioco;
import gameLogic.SetupPartita;

public class Main {
    public static void main(String[] args) {
    	ConsoleIO inputOutput = new ConsoleIO();
        SetupPartita setup = new SetupPartita(inputOutput);
        Gioco g = new Gioco(setup.getNumGiocatori(), setup.getColoreGiocatori(), setup.getLivelloPartita());

        g.play();

        inputOutput.chiudiScanner();
    }
}
