package src;

import java.util.ArrayList;

public class Stanza {
	int id;
	public enum Stato {POZZO,PIPISTRELLI,TUMAEROS,WUMPUS,GIOCATORE};
	public ArrayList<Stato> stato;
	
	/**
	 * Costruttore
	 * @param id Rappresenta id della stanza
	 * 
	 * @return Oggetto Stanza
	 */	
	public Stanza (int id) {
		this.id=id;
		this.stato = new ArrayList<>();
	}
	
	/**
	 * Metodo che permette di cerca lo stato all'interno dell'ArrayList<>
	 * @param stato è lo stato da cercare 
	 * @return Ritorna 1 se viene trovato lo stato o 0 se non viene trovato
	 */	
	public int cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return 1;
		}
		return 0;
    }
	
	/**
	 * Metodo permette di rimuovere dall'ArrayList lo stato dato in input
	 * @param stato è lo stato da eliminare dall' ArrayList
	 */	
	public void removeStato(Stato stato) {
		this.stato.remove(stato);
	}
	
	/**
	 * Metodo permette di aggiungere uno stato solo se l'ArrayList è vuoto
	 * @param stato è lo stato da inserire
	 * @return Ritorna 1 se è stato inserito o  0 se non è stato inserito
	 */	
	public int setStatoIfEmpty(Stato stato) {
		if(this.stato.isEmpty()) {
			this.stato.add(stato);
			return 1;
		}
		return 0;
	}
	
	/**
	 * Metodo Aggiunge uno stato indipendentemente se l'ArrayList è vuoto o no
	 * @param stato  è lo stato da inserire
	 */	
	public void setStato(Stato stato) {
		this.stato.add(stato);
	}
	
	/**
	 * 	Metodo Ritorna l'ArrayList
	 * 
	 * @return Ritorna ArrayList
	 */	
	public ArrayList<Stato> getArrayStato(){
		return this.stato;
	}
	
	/**
	 * Metodo permette di ritornare id della stanza
	 * 
	 * @return Valore della stanza
	 */	
	public int getId() {
		return this.id;
	}

}
