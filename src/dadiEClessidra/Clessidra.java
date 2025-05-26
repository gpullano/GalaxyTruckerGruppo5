package dadiEClessidra;


public class Clessidra {
    //***attribute
    private int secondsLeft;
    private boolean running;
    
    // constructions
    public Clessidra(int second) {
    this.running = false;
    this.secondsLeft=second;
    
    }
    
    //***methods
    public void start() {
        if (running) {
            System.out.println("Timer already started.");
            return;
        }
        running = true;
        System.out.println("Timer started with " + secondsLeft + " seconds.");
    }

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
    
    public boolean isExpired() {
        return secondsLeft == 0;
    }
   
    }
    
