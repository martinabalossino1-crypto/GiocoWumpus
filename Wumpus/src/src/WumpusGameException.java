package src;


public class WumpusGameException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7986241308775561076L;

	/**
	 * Costruttore
	 * @return Ritorna l'oggetto WumpusGameException
	 */	
	public WumpusGameException() {
		super();
	}
	
	/**
	 * Costruttore
	 * @param Messaggio Si tratta della stringa da stampare nel caso venga generata un'eccezione
	 * @return Ritorna l'oggetto WumpusGameException
	 */	
	public WumpusGameException(String messaggio) {
		super(messaggio);
	} 
	
	/**
	 * Costruttore
	 * @param Messaggio Si tratta della stringa da stampare nel caso venga generata un'eccezione
	 * @param thr E' l'oggetto Throwable che indica l'anomalia che si è verificata
	 * @return Ritorna l'oggetto WumpusGameException
	 */	
	public WumpusGameException(String messaggio, Throwable thr) {
		super(messaggio, thr);
	}
	
	/**
	 * Costruttore
	 * @param thr E' l'oggetto Throwable che indica l'anomalia che si è verificata
	 * @return Ritorna l'oggetto WumpusGameException
	 */	
	public WumpusGameException(Throwable thr) {
		super(thr);
	}
}
package src;


public class WumpusGameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5739480770161133862L;

	public WumpusGameException() {
		super("E' STATA CATTURATA UN'ECCEZIONE");
	}
	
	public WumpusGameException(String messaggio) {
		super(messaggio);
	} 
	
	public WumpusGameException(String messaggio, Throwable thr) {
		super(messaggio, thr);
	}
	
	public WumpusGameException(Throwable thr) {
		super(thr);
	}
}
