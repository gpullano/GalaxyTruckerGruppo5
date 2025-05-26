package testingClassi;

import java.util.ArrayDeque;
import java.util.Deque;

import tessere.GeneratoreTessere;
import tessere.Tessera;

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
