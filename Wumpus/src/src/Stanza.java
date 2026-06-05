package src;

import java.util.ArrayList;

public class Stanza {
	
	int id;
	public enum Stato {POZZO,PIPISTRELLI,TUMAEROS,WUMPUS,GIOCATORE};
	ArrayList<Stato> stato;
	
	public Stanza (int id) {
		this.stato = new ArrayList<>();
	}
	
	public int cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return 1;
		}
		return 0;
    }
	
	public void removeStato(Stato stato) {
		this.stato.remove(stato);
	}
	
	public int setStatoIfEmpty(Stato stato) {
		if(this.stato.isEmpty()) {
			this.stato.add(stato);
			return 1;
		}
		return 0;
	}
	
	public void setStato(Stato stato) {
		this.stato.add(stato);
	}
	
	public ArrayList<Stato> getArrayStato(){
		return this.stato;
	}
	
	public int getId() {
		return this.id;
	}

}
package src;

import java.util.ArrayList;

public class Stanza {
	
	int id;
	public enum Stato {POZZO,PIPISTRELLI,TUMAEROS,WUMPUS,GIOCATORE};
	//public int occupata;
	ArrayList<Stato> stato;
	
	public Stanza (int id) {
		this.stato = new ArrayList<>();
		//this.occupata=0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<Stato> getArrayStato(){
		return this.stato;
	}
	
	/*public void setId(int id) {
		this.id=id;
	}*/
	
	public void setStato(Stato stato) {
		this.stato.add(stato);
	}
	
	public int cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return 1;
		}
		return 0;
    }
	
	/*
	 * public boolean cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return true;
		}
		return false;
    }
	 */
	
	public void removeStato(Stato stato) {
		this.stato.remove(stato);
	}
	
	public int setStatoIfEmpty(Stato stato) {
		if(this.stato.isEmpty()) {
			this.stato.add(stato);
			return 1;
		}
		return 0;
	}
	

}
package src;

import java.util.ArrayList;

public class Stanza {
	
	int id;
	public enum Stato {POZZO,PIPISTRELLI,TUMAEROS,WUMPUS,GIOCATORE};
	//public int occupata;
	ArrayList<Stato> stato;
	
	public Stanza (int id) {
		this.stato = new ArrayList<>();
		//this.occupata=0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<Stato> getArrayStato(){
		return this.stato;
	}
	
	/*public void setId(int id) {
		this.id=id;
	}*/
	
	public void setStato(Stato stato) {
		this.stato.add(stato);
	}
	
	public int cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return 1;
		}
		return 0;
    }
	
	/*
	 * public boolean cercaStato (Stato stato) {
		for (Stato s : this.stato) {
			if (s==stato) return true;
		}
		return false;
    }
	 */
	
	public void removeStato(Stato stato) {
		this.stato.remove(stato);
	}
	
	

}
