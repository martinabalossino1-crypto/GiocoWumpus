package src;

import java.util.Random;
import java.util.Scanner;

public class Partita {
	
	Grotta grotta;
	Giocatore giocatore;
	int wumpus;
	int[] pipistrelli= {-1,-1};
	int[] pozzi= {-1,-1};
	int tumaeros;
	int seme;
	int probabilità;
	int finita;
	
	public Partita(int seme,int probabilità) {
		this.seme=seme;
		Random rn = new Random(this.seme);
		int giocatore = rn.nextInt(0,20);
		this.grotta = new Grotta();
		this.giocatore = new Giocatore(5,giocatore);
		this.grotta.getStanza(giocatore).setStato(Stanza.Stato.GIOCATORE);
		int wumpus = rn.nextInt(0,20);
		if(wumpus==giocatore && wumpus==19) {
			this.wumpus=0;
		}
		else if (wumpus==giocatore) {
			this.wumpus=wumpus+1;
		}
		else this.wumpus=wumpus;
		this.probabilità=probabilità;
		this.finita=0;
		this.tumaeros=-1;
	}
	
	public Partita(int probabilità) {
		Random rn = new Random();
		this.seme=rn.nextInt(0,1000);
		rn = new Random(this.seme);
		int giocatore = rn.nextInt(0,20);
		this.grotta = new Grotta();
		this.giocatore = new Giocatore(5,giocatore);
		this.grotta.getStanza(giocatore).setStato(Stanza.Stato.GIOCATORE);
		int wumpus = rn.nextInt(0,20);
		if(wumpus==giocatore && wumpus==19) {
			this.wumpus=0;
		}
		else if (wumpus==giocatore) {
			this.wumpus=wumpus+1;
		}
		else this.wumpus=wumpus;
		this.probabilità=probabilità;
		this.finita=0;
		this.tumaeros=-1;
	}
	
	public int getFinita() {
		return this.finita;
	}
	
	public void scoccaFreccia(int numStanze) {
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		int stanzaPrecedente=21;
		int stanzaTemp=21;
		int stanzaAttuale=this.giocatore.getStanza();
		for (int i=1;i<=numStanze;i++) {
			System.out.println("ROOM #"+i+"?\n");
			stanzaTemp=scn.nextInt();
			if(src.Grotta.percorsoPossibile(stanzaAttuale,stanzaTemp)==1 && stanzaPrecedente!=stanzaTemp) {
				stanzaPrecedente=stanzaAttuale;
				stanzaAttuale=stanzaTemp;
			}
			else {
				Random rn = new Random();
				stanzaPrecedente=stanzaAttuale;
				stanzaAttuale=this.grotta.getTunnel()[stanzaAttuale*3+rn.nextInt(0,3)];
			}
			if(stanzaAttuale==this.wumpus) {
				Random rn = new Random(this.seme);
				int random = rn.nextInt(0,4);
				if(random==1
						|| random==2
						|| random==3) {
					random = rn.nextInt(0,3);
					this.wumpus=this.wumpus*3+random;
					if (this.wumpus==this.giocatore.getStanza()) {
						System.out.println("HAI PERSO");
						this.finita=1;
						return;
					}
				}
			}
		}
		if(stanzaAttuale==this.wumpus) {
			System.out.println("HAI VINTO");
			this.finita=1;
		}
		else if(stanzaAttuale==this.giocatore.getStanza()) {
			System.out.println("HAI PERSO");
			this.finita=1;
		}
		this.giocatore.eliminaFreccia();
	}
	
	public void start() {
		Random rn = new Random(this.seme);
		int stanzaRandom;
		int i=0;
		while(i<2) {
			stanzaRandom=rn.nextInt(0,20);
			if(this.grotta.getStanza(stanzaRandom).getArrayStato().isEmpty()) {
				this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.POZZO);
				this.pozzi[i]=stanzaRandom;
				i++;
			}
		}
		i=0;
		while(i<2) {
			stanzaRandom=rn.nextInt(0,20);
			if(this.grotta.getStanza(stanzaRandom).getArrayStato().isEmpty()) {
				this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.PIPISTRELLI);
				this.pipistrelli[i]=stanzaRandom;
				i++;
			}
		}
		i=0;
		while(i==0) {
			stanzaRandom=rn.nextInt(0,20);
			if(this.grotta.getStanza(stanzaRandom).getArrayStato().isEmpty()) {
				this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.TUMAEROS);
				this.tumaeros=stanzaRandom;
				i=1;
			}
		}
		i=0;
		while(i==0) {
			stanzaRandom=rn.nextInt(0,20);
			if(this.grotta.getStanza(stanzaRandom).cercaStato(Stanza.Stato.WUMPUS)==0) {
				this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.WUMPUS);
				this.wumpus=stanzaRandom;
				i=1;			
			}
		}
	}
	
	public void passo(int passo) {
		this.grotta.getStanza(this.giocatore.getStanza()).removeStato(Stanza.Stato.GIOCATORE);
		this.grotta.getStanza(passo).setStato(Stanza.Stato.GIOCATORE);
		this.giocatore.setStanza(passo);
		if(this.grotta.getStanza(passo).cercaStato(Stanza.Stato.PIPISTRELLI)==1) {
			Random rn = new Random(this.seme);
			int stanzaRandom=rn.nextInt(0,20);
			this.grotta.getStanza(this.giocatore.getStanza()).removeStato(Stanza.Stato.GIOCATORE);
			this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.GIOCATORE);
			this.giocatore.setStanza(stanzaRandom);
			System.out.println("PIPISTRELLI! TI HANNO SPOSTATO IN UN'ALTRA CELLA "+stanzaRandom);
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.TUMAEROS)==1) {
			this.giocatore.eliminaFreccia();
			System.out.println("TUMAEROS! TI RIMANGONO "+this.giocatore.getFrecce()+" FRECCE");
		}
		if(this.giocatore.getFrecce()==0) {
			System.out.println("GAME OVER - HAI FINITO LE FRECCE");
			this.finita=1;
			return;
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.WUMPUS)==1) {
			System.out.println("GAME OVER - L'WUMPUS TI HA TROVATO");
			this.finita=1;
			return;
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.POZZO)==1) {
			System.out.println("GAME OVER - SEI CADUTO IN UN POZZO");
			this.finita=1;
			return;
		}
	}
	
	public void cambioCasuale() {
		Random rn = new Random(this.seme);
		int valRandom=rn.nextInt(0,this.probabilità);
		if(valRandom==0) {
			valRandom=rn.nextInt(0,3);
			this.grotta.getStanza(this.wumpus).removeStato(Stanza.Stato.WUMPUS);
			this.wumpus=this.grotta.getTunnel()[this.wumpus*3+valRandom];
			this.grotta.getStanza(this.wumpus).setStato(Stanza.Stato.WUMPUS);
			if (this.giocatore.getStanza()==this.wumpus) {
				System.out.println("GAME OVER");
				this.finita=1;
				return;
			}
		}
		valRandom=rn.nextInt(0,this.probabilità);
		if(valRandom==0) {
			valRandom=rn.nextInt(0,3);
			this.grotta.getStanza(this.tumaeros).removeStato(Stanza.Stato.TUMAEROS);
			this.tumaeros=this.grotta.getTunnel()[this.tumaeros*3+valRandom];
			this.grotta.getStanza(this.tumaeros).setStato(Stanza.Stato.TUMAEROS);
			if (this.giocatore.getStanza()==this.tumaeros) {
				this.giocatore.eliminaFreccia();
				return;
			}
		}
		for(int i=0;i<2;i++) {
			valRandom=rn.nextInt(0,this.probabilità);
			if(valRandom==0) {
				valRandom=rn.nextInt(0,3);
				this.grotta.getStanza(this.pipistrelli[i]).removeStato(Stanza.Stato.PIPISTRELLI);
				this.pipistrelli[i]=this.grotta.getTunnel()[this.pipistrelli[i]*3+valRandom];
				this.grotta.getStanza(this.pipistrelli[i]).setStato(Stanza.Stato.PIPISTRELLI);
				if (this.giocatore.getStanza()==this.pipistrelli[i]) {
					System.out.println("GAME OVER");
					this.finita=1;
					return;
				}
			}		
		}
		for(int i=0;i<2;i++) {
			valRandom=rn.nextInt(0,20);
			if(valRandom==1) {
				valRandom=rn.nextInt(0,3);
				this.grotta.getStanza(this.pozzi[i]).removeStato(Stanza.Stato.POZZO);
				this.pozzi[i]=this.grotta.getTunnel()[this.pozzi[i]*3+valRandom];
				this.grotta.getStanza(this.pozzi[i]).setStato(Stanza.Stato.POZZO);
				if (this.giocatore.getStanza()==this.pozzi[i]) {
					int stanzaRandom=rn.nextInt(0,20);
					this.grotta.getStanza(this.giocatore.getStanza()).removeStato(Stanza.Stato.GIOCATORE);
					this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.GIOCATORE);
					this.giocatore.setStanza(stanzaRandom);
				}
			}		
		}
	}
	
	public String toString() {
		String stringa="";
		stringa=stringa+"TU TI TROVI NELLA STANZA "+this.giocatore.getStanza()+"\n";
		stringa=stringa+"WUMPUS: CELLA  "+this.wumpus+"\n";
		stringa=stringa+"POZZI: CELLA  "+this.pozzi[0]+" E "+this.pozzi[1]+"\n";
		stringa=stringa+"PIPISTRELLI: CELLA  "+this.pipistrelli[0]+" E "+this.pipistrelli[1]+"\n";
		stringa=stringa+"TUMAEROS: CELLA  "+this.tumaeros+"\n";
		return stringa;
	}

}
