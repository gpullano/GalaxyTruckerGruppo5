package gameLogic;

public class Gioco {
	
	public Gioco() {
		
	}
	
	public void play() {
		ConsoleIO inputOutput = new ConsoleIO();
        SetupPartita setup = new SetupPartita(inputOutput);

        
        
        
        
     // alla fine del gioco
        inputOutput.chiudiScanner();
	}
}
