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
