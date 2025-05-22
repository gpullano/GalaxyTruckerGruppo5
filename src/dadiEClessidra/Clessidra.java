package dadiEClessidra;

import gameLogic.LivelloPartita;

public class Clessidra {
    //***attribute
    private LivelloPartita level;
    private int secondsLeft;
    private boolean running;
    
    // constructions
    public Clessidra(int second , LivelloPartita level) {
    this.level = level;
    this.running = false;
    this.secondsLeft=second;
    
    }
    
    // getter
    
    public LivelloPartita getLevel() {
        return level;
    }
    
    //method
    public void setGameLevel(LivelloPartita level) {
        this.level = level;
        switch (level) {
            case LIVELLO1:
                System.out.println("LEVEL_I: 1 clessidra, when the first player finishes ,the game starts");
                break;
            case LIVELLO2:
                System.out.println("LEVEL_II: 1 clessidra, when the second player finishes, the game starts");
                break;
            case LIVELLO3:
                System.out.println("LEVEL_III: 1 clessidra, when the third player finishes, the game starts");
                break;
		default:
			break;
    }
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
    
