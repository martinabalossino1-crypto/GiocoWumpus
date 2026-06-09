package src;

public class Grotta {
	
	public static int[] vTunnels;
	public static Stanza[] vStanze;
	
	/**
	 * Costruttore
	 * 
	 * @return un oggetto di tipo Grotta
	 */
	public Grotta() {
		vStanze = new Stanza[20];
		for (int i=0;i<=19;i++) {
				vStanze[i] = new Stanza(i);
		}
	}
	
	/**
	 * Ritorna la stanza presente all'indice passato in input del vettore vStanze
	 * 
	 * @param stanza Id stanza, che corrisponde anche alla sua posizione nel vettore vStanze
	 * @return la stanza indicata
	 */
	public Stanza getStanza(int stanza) {
		return vStanze[stanza];
	}
	
	/**
	 * Ritorna l'array che indica quali sono le stanze adiacenti ad ogni stanza della grotta
	 * 
	 * @return Array dei tunnel
	 */
	public int[] getTunnel() {
		return vTunnels;
	}
	
	/**
	 * Indica se una certa stanza è adiacente ad un'altra andando a leggere quali stanze sono adiacenti alla prima dal vettore vTunnel
	 * 
	 * @param stanza1 stanza di partenza
	 * @param stanza2 stanza in cui si vuole andare
	 * @return 1 se le due stanze sono adiacenti, 0 altrimenti
	 */
	public static int percorsoPossibile(int stanza1, int stanza2) {
		if(vTunnels[stanza1*3]==stanza2||vTunnels[stanza1*3+1]==stanza2||vTunnels[stanza1*3+2]==stanza2) return 1;
		return 0;
	}
	
}
