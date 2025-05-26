package testingClassi;
import tessere.GeneratoreTessere;
import tessere.Tessera;
import java.util.Deque;
import java.util.Random;
import java.util.ArrayDeque;
public class TestTessere {
    private Deque<Tessera> mucchioTessere;

    public TestTessere() {
    	this.mucchioTessere = creaMucchioTessere();
    }
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < 10; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }
	
	public void ottieniPrimo() {
		System.out.println(this.mucchioTessere.pop());
	}
	
	}
