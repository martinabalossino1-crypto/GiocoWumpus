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
	
	/**
	 * Costruttore
	 * @param seme Il valore scelto da usare come della funzione random
	 * @param probabilità Probabilità che gli ostacoli si spostino di stanza
	 * 
	 * @return Ritorna oggetto partita
	 */	
	public Partita(int seme,int probabilità) {
		this.seme=seme;
		Random rn = new Random(this.seme);
		int giocatore = rn.nextInt(0,20);
		this.giocatore = new Giocatore(5,giocatore);
		this.giocatore = new Giocatore(5,giocatore);
		this.grotta = new Grotta();
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
	
	/**
	 * Costruttore 
	 * @param probabilità Probabilità che gli ostacoli si spostino di stanza
	 * 
	 * @return Ritorna oggetto partita
	 */	
	public Partita(int probabilità) {
		Random rn = new Random();
		this.seme=rn.nextInt(0,1000);
		rn = new Random(this.seme);
		int giocatore = rn.nextInt(0,20);
		this.giocatore = new Giocatore(5,giocatore);
		this.grotta = new Grotta();
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
	
	/**
	 * Metodo Setta tutti i tipi di ostacoli prima di iniziare la partita solo se dopo aver fatto setStatoIfEmpty è ci restituisce 1
	 se è stato settato altrimenti 0 se non è stato settato
	 */	
	public void start() {
		Random rn = new Random(this.seme);
		int stanzaRandom;
		int i=0;
		while(i<2) {
			stanzaRandom=rn.nextInt(0,20);
			int inserito = this.grotta.getStanza(stanzaRandom).setStatoIfEmpty(Stanza.Stato.POZZO);
			if(inserito==1) {
				this.pozzi[i]=stanzaRandom;
				i++;
			}
		}
		i=0;
		while(i<2) {
			stanzaRandom=rn.nextInt(0,20);
			int inserito = this.grotta.getStanza(stanzaRandom).setStatoIfEmpty(Stanza.Stato.PIPISTRELLI);
			if (inserito==1) {
				this.pipistrelli[i]=stanzaRandom;
				i++;
			}
		}
		i=0;
		while(i==0) {
			stanzaRandom=rn.nextInt(0,20);
			int inserito = this.grotta.getStanza(stanzaRandom).setStatoIfEmpty(Stanza.Stato.TUMAEROS);
			if (inserito==1) {
				this.tumaeros=stanzaRandom;
				i=1;
			}
		}
		i=0;
		while(i==0) {
			stanzaRandom=rn.nextInt(0,20);
			int inserito = this.grotta.getStanza(stanzaRandom).setStatoIfEmpty(Stanza.Stato.WUMPUS);
			if (inserito==1) {
				this.wumpus=stanzaRandom;
				i=1;			
			}
		}
	}
	
	/**
	 * Metodo Permette all'inizio di verificare se la stanza in cui si vuole muovere è adiacente se si 
	 * il giocatore si può muovere quindi viene aggiornato lo stato della vecchia stanza e anche lo stato della nuova stanza e infine viene aggiornato
	 * il campo stanza del giocatore.
	 * Inoltre verifica se nella stanza in cui si è spostato il giocatore ci sono degli ostacoli.
	 * Se l'ostacolo è letale come WUMPUS e POZZO viene inviato un un messaggio è si perde la partita e si setta il campo finita.
	 * Mentre per l''ostacolo TUMAEROS si elimina una freccia è se il giocatore in quel momento non ne ha allora perde la partita altrimenti no mentre l'ostacolo PIPISTRELLO sposta il giocatore
	 * in un'altra stanza casuale
	 * @param passo
	 * @return Ritorna 1 se è spostato il altrimenti 0 se non è stato spostato
	 */	
	public int passo(int passo) {
		if(Grotta.percorsoPossibile(getGiocatore().getStanza(), passo)==0) return 0;
		this.grotta.getStanza(this.giocatore.getStanza()).removeStato(Stanza.Stato.GIOCATORE);
		this.grotta.getStanza(passo).setStato(Stanza.Stato.GIOCATORE);
		this.giocatore.setStanza(passo);
		if(this.grotta.getStanza(passo).cercaStato(Stanza.Stato.PIPISTRELLI)==1) {
			int stanzaRandom = this.spostaRandom();
			System.out.println("ZAP – SUPER BAT SNATCH! ELSEWHEREVILLE FOR YOU! "+stanzaRandom);
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.TUMAEROS)==1) {
			this.giocatore.eliminaFreccia();
			System.out.println("CHOMP, CHOMP - THAT WAS A TASTY ARROW");
			}
		if(this.giocatore.getFrecce()==0) {
			System.out.println("OOPS! YOU HAVE USED ALL YOUR ARROWS!");
			this.finita=1;
			return 1;
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.WUMPUS)==1) {
			Random rn = new Random(this.seme);
			int randomProbabilità = rn.nextInt(0,4);
			if(randomProbabilità!=0) {
				System.out.println("... OPS! BUMPED A WUMPUS");
				this.finita=1;
			}
		}
		if(this.grotta.getStanza(this.giocatore.getStanza()).cercaStato(Stanza.Stato.POZZO)==1) {
			System.out.println("YYYIIIIEEEE... FELL IN PIT");
			this.finita=1;
		}
		return 1;
	}
		
	/**
	* Metodo Permette al giocatore di scoccare la freccia per un massimo di 5 stanze.
	* Una volta scoccata viene decrementato il numero delle frecce del giocatore.
	* Le frecce scoccate non possono seguire una curva stretta mentre Se la traiettoria indicata dal giocatore non è valida (cioè,
	se la traiettoria richiede l’attraversamento di due stanze non comunicanti tramite un tunnel), la freccia andrà in una delle stanze
	comunicanti con quella attuale scelta a caso.
	* Invece se la freccia colpisce il giocatore perde la partita.
	* Mentre se si riesce a colpire il WUMPUS si vince la partita altrimenti se la freccia passa nella sua stanza è non colpisce il bersaglio ce una probabilità
	* del 75% che il WUMPUS si svegli e si sposti in una delle tre stanze adiacenti e quindi se il WUMPUS si è spostato nella stanza del giocatore perde la partita e infine se il WUMPUS non viene
	* preso viene stampato il messaggio MISSED.
	* 
	*
	 */	
	public void scoccaFreccia(int numStanze) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int stanzaPrecedente=21;
		int stanzaTemp=21;
		int stanzaAttuale=this.giocatore.getStanza();
		int i=1;
		while(i<=numStanze){
			System.out.println("ROOM #"+i+"?\n");
			stanzaTemp=sc.nextInt();
			if(Grotta.percorsoPossibile(stanzaAttuale,stanzaTemp)==1 && stanzaPrecedente!=stanzaTemp) {
				stanzaPrecedente=stanzaAttuale;
				stanzaAttuale=stanzaTemp;
				i++;
			}
			else if (stanzaPrecedente==stanzaTemp){
				System.out.println("ARROWS AREN’T THAT CROOKED - TRY ANOTHER ROOM\n");
				/*Random rn = new Random();
				stanzaPrecedente=stanzaAttuale;
				stanzaAttuale=this.grotta.getTunnel()[stanzaAttuale*3+rn.nextInt(0,3)];*/
			}
			else {
				Random rn = new Random();
				stanzaPrecedente=stanzaAttuale;
				stanzaAttuale=this.grotta.getTunnel()[stanzaAttuale*3+rn.nextInt(0,3)];
				System.out.println("THE ARROW WENT INTO ROOM "+stanzaAttuale+"\n");
				i++;
			}
			if(stanzaAttuale==this.giocatore.getStanza()) {
				System.out.println("OUCH! ARROW GOT YOU!\n");
				this.finita=1;
				return;
			}
			else if(stanzaAttuale==this.wumpus) {
				Random rn = new Random(this.seme);
				int random = rn.nextInt(0,4);
				if(random!=0) {
					this.spostaAdiacente(Stanza.Stato.WUMPUS, this.wumpus);
					if (this.wumpus==this.giocatore.getStanza()) {
						System.out.println("TSK TSK TSK- WUMPUS GOT YOU!\n");
						this.finita=1;
						return;
					}
				}
			}			
		}
		if(stanzaAttuale==this.wumpus) {
			System.out.println("AHA! YOU GOT THE WUMPUS! HEE HEE HEE - THE WUMPUS’LL GETCHA NEXT TIME!!\n");
			this.finita=1;
		}
		else {
			System.out.println("MISSED\n");
		}
		this.giocatore.eliminaFreccia();
		sc.close();
	}
	
	public int spostaAdiacente(Stanza.Stato stato, int personaggio) {
		Random rn = new Random(this.seme);
		int valRandom=rn.nextInt(0,this.probabilità);
		valRandom=rn.nextInt(0,3);
		this.grotta.getStanza(personaggio).removeStato(stato);
		personaggio=this.grotta.getTunnel()[personaggio*3+valRandom];
		this.grotta.getStanza(personaggio).setStato(stato);
		if (this.giocatore.getStanza()==personaggio) {
			return 1;
		}
		return 0;
	}
	
	public int spostaRandom() {
		Random rn = new Random(seme);
		int stanzaRandom=rn.nextInt(0,20);
		this.grotta.getStanza(this.giocatore.getStanza()).removeStato(Stanza.Stato.GIOCATORE);
		this.grotta.getStanza(stanzaRandom).setStato(Stanza.Stato.GIOCATORE);
		this.giocatore.setStanza(stanzaRandom);
		return stanzaRandom;
	}	
	
	public void cambioCasuale() {
		Random rn = new Random(this.seme);
		int valRandom=rn.nextInt(0,this.probabilità);
		if(valRandom==0) {
			int stessaStanzaGiocatore = this.spostaAdiacente(Stanza.Stato.WUMPUS, this.wumpus);
			System.out.println("DON’T BLINK NOW, BUT I HEAR THE WUMPUS SLEEP-WALKING!!!!");
			if (stessaStanzaGiocatore==1) {
				System.out.println("TSK TSK TSK- WUMPUS GOT YOU!");
				this.finita=1;
				return;
			}
		}
		valRandom=rn.nextInt(0,this.probabilità);
		if(valRandom==0) {
			int stessaStanzaGiocatore = this.spostaAdiacente(Stanza.Stato.TUMAEROS, this.tumaeros);
			System.out.println("BUZZ, BUZZ - THE TUMAEROS ARE SWARMING");
			if (stessaStanzaGiocatore==1) {
				System.out.println("CHOMP, CHOMP - THAT WAS A TASTY ARROW");
				this.giocatore.eliminaFreccia();
				return;
			}
		}
		for(int i=0;i<2;i++) {
			valRandom=rn.nextInt(0,this.probabilità);
			if(valRandom==0) {
				int stessaStanzaGiocatore = this.spostaAdiacente(Stanza.Stato.PIPISTRELLI, this.pipistrelli[i]);
				System.out.println("WHAT A FLAP YOU’RE IN... IT’S BAT MIGRATION TIME!!");
				if (stessaStanzaGiocatore==1) {
					int stanzaRandom=this.spostaRandom();
					System.out.println("ZAP – SUPER BAT SNATCH! ELSEWHEREVILLE FOR YOU! "+stanzaRandom);
				}
			}		
		}
		for(int i=0;i<2;i++) {
			valRandom=rn.nextInt(0,20);
			if(valRandom==0) {
				int stessaStanzaGiocatore = this.spostaAdiacente(Stanza.Stato.POZZO, this.pozzi[i]);
				System.out.println("RUMBLE, RUMBLE - YOU’RE STANDING ON SHAKY GROUND... NEW PITS HAVE BEEN FORMED BY THE EARTHQUAKE!!");
				if (stessaStanzaGiocatore==1) {
					System.out.println("YYYIIIIEEEE... FELL IN PIT");
					this.finita=1;
					return;
				}
			}		
		}
	}
	
	public void ostacoliVicini() {
		if(Grotta.percorsoPossibile(this.giocatore.getStanza(), this.wumpus)==1) {
			System.out.println("I SMELL A WUMPUS!");
		}
		if(Grotta.percorsoPossibile(this.giocatore.getStanza(), this.tumaeros)==1) {
			System.out.println("MY ARROWS ARE QUIVERING");
		}
		if(Grotta.percorsoPossibile(this.giocatore.getStanza(), this.pipistrelli[0])==1
				||Grotta.percorsoPossibile(this.giocatore.getStanza(), this.pipistrelli[1])==1) {
			System.out.println("BATS NEARBY");
		}
		if(Grotta.percorsoPossibile(this.giocatore.getStanza(), this.pozzi[0])==1||
				Grotta.percorsoPossibile(this.giocatore.getStanza(), this.pozzi[1])==1) {
			System.out.println("I FEEL A DRAFT");
		}
		return;
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
	
	public void setFinita(int finita) {
		this.finita=finita;
	}
	
	public Grotta getGrotta() {
		return this.grotta;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public int getFinita() {
		return this.finita;
	}

}
