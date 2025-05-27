package gameLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import carteAvventura.Mazzetto;
import dadiEClessidra.Clessidra;
import plance.PlanceVolo;
import tessere.GeneratoreTessere;
import tessere.Tessera;

public class FaseAssemblaggio extends Fase {
	private static final int N_TESSERE = 20; // TODO - da modificare con 156 per il gioco vero
	private static final int TEMPO_CLESSIDRA = 20;
    private Deque<Tessera> mucchioTessere;
    private List<Tessera> tessereScoperte;
    private Mazzetto[] mazzettiDiCarte;
    private Clessidra clessidra;
    private PlanceVolo planceVolo;
    
    //costruttore
	public FaseAssemblaggio(List<Giocatore> giocatori, PlanceVolo planceVolo,  Mazzetto[] mazzettiDiCarte, ConsoleIO inputOutput) {
		super(giocatori, inputOutput);
		this.planceVolo = planceVolo;
		this.setMucchioTessere(creaMucchioTessere());
		
		// Le 3 pile inferiori - La pila in alto (mazzettiDiCarteAvventura[3]) è ignota
		this.mazzettiDiCarte = new Mazzetto[]{mazzettiDiCarte[0], mazzettiDiCarte[1], mazzettiDiCarte[2]}; 
		
		this.clessidra = new Clessidra(TEMPO_CLESSIDRA);
		this.tessereScoperte = new ArrayList<>();	
	}
	
	//getter e setter
	public Deque<Tessera> getMucchioTessere() {
		return mucchioTessere;
	}

	public void setMucchioTessere(Deque<Tessera> mucchioTessere) {
		this.mucchioTessere = mucchioTessere;
	}
	
	
	//metodi
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }


	@Override
	public void eseguiFase() {
		// TODO Auto-generated method stub
		
	}

	

}
