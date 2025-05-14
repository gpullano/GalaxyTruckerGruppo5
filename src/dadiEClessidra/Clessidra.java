package dadiEClessidra;

public class Clessidra {
    //***attribute
    private int phaseDuration;
    private int inputPhaseDuration;
    private GameLevel level;
    private Thread timerThread;
    private volatile boolean running = false;
    
    // constructions
    public Clessidra(int phaseDuration, GameLevel level) {
    this.level = level;
    this.phaseDuration = phaseDuration;
    this.inputPhaseDuration = phaseDuration;
    }
    
    // getters
    public int getPhaseDuration() {
        return phaseDuration;
    }
    
    public GameLevel getLevel() {
        return level;
    }
    
    // setters
    public void setPhaseDuration(int phaseDuration) {
        this.phaseDuration = phaseDuration;
    }
    
    public void setGameLevel(GameLevel level) {
        this.level = level;
        switch (level) {
            case LEVEL_I:
                System.out.println("LEVEL_I: 1 clessidra, when the first player finishes ,the game starts");
                break;
            case LEVEL_II:
                System.out.println("LEVEL_II: 1 clessidra, when the second player finishes, the game starts");
                break;
            case LEVEL_III:
                System.out.println("LEVEL_III: 1 clessidra, when the third player finishes, the game starts");
                break;
    }
    }
    
    //***methods
    public void start() {
    
        if (timerThread != null && timerThread.isAlive()) {
            System.err.println("test");
            return; // already running
        }
    
        running = true;
        timerThread = new Thread(() -> {
            System.out.println("Time left: " + phaseDuration + " seconds");
            while (running && phaseDuration > 0) {
                try {
                    Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
                break;
            }
            if (running) {
                phaseDuration--;
                System.out.println("Time left: " + phaseDuration + " seconds");
                }
            }
        });
        timerThread.start();
        
    }
    
    public void stop() {
        running = false;
        if (timerThread != null) {
            timerThread.interrupt(); // optional: in case it's sleeping
            try{
                timerThread.join();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
    }
    }
    
    public void reverse(){
        System.err.println("after reversing");
        stop();
        this.phaseDuration = this.inputPhaseDuration - this.phaseDuration;
        start();
    }
    
    public boolean isExpired() {
        return this.phaseDuration == 0;
    }
   
    }
    
