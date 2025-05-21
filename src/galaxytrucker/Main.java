package galaxytrucker;

import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.GeneratoreScudi;
import tessere.Stiva;
import tessere.Tessera;

public class Main {
    public static void main(String[] args) {
        System.out.println("It just works");
        Cannone c1 = new Cannone(Connettore.SINGOLO, Connettore.DOPPIO, Connettore.MOTORE, Connettore.SINGOLO);
        System.out.println(c1);
        Cabina c2 = new Cabina(Connettore.DOPPIO, Connettore.SINGOLO,Connettore.UNIVERSALE, Connettore.MOTORE);
        System.out.println(c2);
        CannoneDoppio c3 =new CannoneDoppio(Connettore.UNIVERSALE, Connettore.MOTORE,Connettore.MOTOREDOPPIO, Connettore.SINGOLO);
        System.out.println(c3);
        GeneratoreScudi c4 = new GeneratoreScudi(Connettore.SINGOLO, Connettore.DOPPIO, Connettore.MOTORE, Connettore.SINGOLO);
        System.out.println(c4);
        Stiva c5 = new Stiva(Connettore.SINGOLO, Connettore.DOPPIO, Connettore.MOTORE, Connettore.SINGOLO,4);
        System.out.println(c5);
        

 
    }
}
