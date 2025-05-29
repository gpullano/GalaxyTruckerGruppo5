package tessere;

/**
*interfaccia che fornisce a determinate tessere il metodo attivabile, da implementare.
*esempi sono: gli scudi, i cannoni e i motori doppi, le batterie.
*/
public interface Attivabile {

/**
* Definisce l'attivazione della tessera in base all'energia che c'è.
* @param energia true se la tessera riceve energia se no false.
*/

	public void attiva(boolean energia);
}
