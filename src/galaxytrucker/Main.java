package galaxytrucker;

import gameLogic.Gioco;
import gameLogic.SetupPartita;

public class Main {
    //this is your first function, take care!
    public static void main(String[] args) {
        SetupPartita setup = new SetupPartita();
        Gioco gioco = new Gioco();
        gioco.play();
    }
}
