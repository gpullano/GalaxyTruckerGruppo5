package dadiEClessidra;

/**
* La classe Clessidra rappresenta un timer a conto alla rovescia. 
* Si può avviare, fermare ed esegue il tick ogni secondo, riducendo il tempo rimanente.
*/
public class Clessidra {
    //attributi
    private int secondsLeft;
    private boolean running;

/**
* Costruisce una clessidra con il tempo iniziale specificato.
* @param second Il numero di secondi da definire per il conto alla rovescia.
*/
    public Clessidra(int second) {
    this.running = false;
    this.secondsLeft=second;
    
    }
    

  //metodi
    
/**
* Avvia il timer se non è operativo.
* Stampa un messaggio con il tempo rimanente.
*/
    //TODO - tradurre
    public void start() {
        if (running) {
            System.out.println("Timer already started.");
            return;
        }
        running = true;
        System.out.println("Timer started with " + secondsLeft + " seconds.");
    }

/**
* Esegue un tick del timer. Riducendo il tempo rimanente.
* Stampa il tempo rimasto. Se il tempo finisce, il timer si ferma.
*/
     public void tick() {
        if (running && secondsLeft > 0) {
            secondsLeft--;
            System.out.println("Time left: " + secondsLeft + " seconds");

            if (secondsLeft == 0) {
                running = false;
                System.out.println("Time's up!");
            }
        }
    }

     public void stop() {
        running = false;
        System.out.println("Timer stopped.");
    }

/**
* Si accerta che il timer è scaduto perciò il tempo rimanente è uguale a zero.
* @return zero secondi se il tempo è finito.
*/
    public boolean isNotExpired() {
        return secondsLeft != 0;
    }
   
    }
    
