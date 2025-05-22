package galaxytrucker;

import dadiEClessidra.Clessidra;
import gameLogic.LivelloPartita;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("It just works");
        Clessidra c = new Clessidra(10, LivelloPartita.LIVELLO1);
        c.start();
        //counting with seconds wait one second 2->1 (phase=1second)
        //just 5 second than stop
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000); // wait 1 second
            c.tick();       // manually reduce the timer
        }
        c.stop();
      



        
        


        

 
    }
}
