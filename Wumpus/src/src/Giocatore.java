package src;


public class Giocatore {
	
	int frecce;
	int stanza;
	
	/**
	 * Costruttore
	 * 
	 * @param numFrecce Numero di frecce con cui il giocatore vuole iniziare a giocare
	 * @param stanza Prima stanza in cui appare il giocatore
	 * @return Oggetto Giocatore
	 */	
	public Giocatore(int numFrecce, int stanza) {
		this.frecce=numFrecce;
		this.stanza=stanza;
	}
	
	/**
	 * Sottrae una freccia da quelle possedute dal giocatore
	 * 
	 * @return Descrizione del valore restituito.
	 */
	public void eliminaFreccia() {
		this.frecce--;
	}
	
	/**
	 * Setta il numero della stanza in cui si trova il giocatore
	 * 
	 * @param stanza Id della stanza da settare
	 */
	public void setStanza(int stanza) {
		this.stanza=stanza;
	}
	
	/**
	 * Ritorna il numero di frecce possedute dal giocatore
	 * 
	 * @return valore contenuto nell'attributo "frecce"
	 */
	public int getFrecce() {
		return this.frecce;
	}
	
	/**
	 * Ritorna l'id della stanza in cui si trova il giocatore
	 * 
	 * @return Id stanza giocatore
	 */
	public int getStanza() {
		return this.stanza;
	}
		
}
