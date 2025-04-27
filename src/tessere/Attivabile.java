package tessere;

public interface Attivabile {
	// interfaccia che fornisce a determinate tessere il metodo attivabile, da implementare.
	// esempi sono: gli scudi, i cannoni e i motori doppi, le batterie.
	public void attiva(boolean energia);
}
