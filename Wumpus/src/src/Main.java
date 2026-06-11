package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws WumpusGameException {
		// TODO Auto-generated method stub
		
		int seme=0;
		boolean semeDefault = false;
		String livello;
		String grotta;
		String scelta;
		
		while(true) {
				
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			
			System.out.println("-- HUNT THE WUMPUS --\r\n"
					+ "BRAVE DUDE, HUNT THE WUMPUS!\n");
			
			String istruzioni = "c";
			do {
				System.out.println("SHOW INSTRUCTIONS (Y/N)?");
				istruzioni=sc.next();
				if (istruzioni.compareTo("Y")!=0 && istruzioni.compareTo("y")!=0 && istruzioni.compareTo("N")!=0 && istruzioni.compareTo("n")!=0) {
					System.out.println("NOT VALID");
				}
			} while (istruzioni.compareTo("Y")!=0 && istruzioni.compareTo("y")!=0 && istruzioni.compareTo("N")!=0 && istruzioni.compareTo("n")!=0);
				
			if(istruzioni.compareTo("Y")==0 || istruzioni.compareTo("y")==0) {
				BufferedReader reader = null;
				try {
					File f = new File("istruzioni.txt");
			        reader =  new BufferedReader(new FileReader(f));
				}
				catch(Throwable thr) {
					System.out.println(new WumpusGameException("IMPOSSIBLE TO FIND THE SPECIFIED FILE\n").toString());
				}
				
				try {
					String currentLine;
					while ((currentLine = reader.readLine())!=null) {
						System.out.println(currentLine);
					}
					reader.close();
				}
				catch(Throwable thr) {
					System.out.println(new WumpusGameException("IT'S NOT POSSIBLE TO READ THE FILE BECAUSE IT'S EMPTY\n").toString());
				}
			}
			
			
			System.out.println("INSERIRT SEED:");
			try {
				seme=sc.nextInt();
			}
			catch (Throwable thr) {
				sc.nextLine();
				System.out.println(new WumpusGameException("THE VALUE ENTERED DOESN'T MATCH A INTEGER\n").toString());
				System.out.println("THE SEED VALUE WILL BE RANDOM\n");
				semeDefault = true;
			}
			
			do {
				System.out.println("DIFFICULTY: (E)ASY, (N)ORMAL, (H)ARD?");
				livello=sc.next();
				if (livello.compareTo("E")!=0 && livello.compareTo("e")!=0 && livello.compareTo("N")!=0 && livello.compareTo("n")!=0 && livello.compareTo("H")!=0 && livello.compareTo("h")!=0) {
					System.out.println("NOT VALID\n");
				}
			} while (livello.compareTo("E")!=0 && livello.compareTo("e")!=0 && livello.compareTo("N")!=0 && livello.compareTo("n")!=0 && livello.compareTo("H")!=0 && livello.compareTo("h")!=0);
			
			Partita partita;
			if(livello.compareTo("E")==0 || livello.compareTo("e")==0) {
				if (semeDefault == true) partita = new Partita(20);
				else partita = new Partita(seme,20);
			}
			else if(livello.compareTo("N")==0 || livello.compareTo("n")==0) {
				if (semeDefault == true) partita = new Partita(12);
				else partita = new Partita(seme,12);
			}
			else {
				if (semeDefault == true) partita = new Partita(6);
				else partita = new Partita(seme,6);
			}
			
			do {
				System.out.println("CAVE: (D)ODECAHEDRON, (M)OBIUS STRIP?");
				grotta=sc.next();
				System.out.println("");
				if (grotta.compareTo("D")!=0 && grotta.compareTo("d")!=0 && grotta.compareTo("M")!=0 && grotta.compareTo("m")!=0) {
					System.out.println("NOT VALID\n");
				}
			} while (grotta.compareTo("D")!=0 && grotta.compareTo("d")!=0 && grotta.compareTo("M")!=0 && grotta.compareTo("m")!=0);
			
			if(grotta.compareTo("D")==0 || grotta.compareTo("d")==0) partita.grotta = new Dodecaedro();
			else partita.grotta = new Mobius();
			
			partita.start();
			
			while(partita.getFinita()==0) {
				
				sc.nextLine();
				//System.out.println(partita.toString());
				System.out.println("YOU ARE IN ROOM "+partita.getGiocatore().getStanza());
				partita.ostacoliVicini();
				do {
					System.out.println("\n(M)OVE, (S)HOOT, SHOW MA(P), or (Q)UIT?");
					scelta=sc.next();
					System.out.println("");
					if(scelta.compareTo("M")!=0 && scelta.compareTo("m")!=0 && scelta.compareTo("S")!=0 && scelta.compareTo("s")!=0 && scelta.compareTo("P")!=0 
						&& scelta.compareTo("p")!=0 && scelta.compareTo("Q")!=0 && scelta.compareTo("q")!=0) {
						System.out.println("NOT VALID\n");
					}
				}
				while (scelta.compareTo("M")!=0 && scelta.compareTo("m")!=0 && scelta.compareTo("S")!=0 && scelta.compareTo("s")!=0 && scelta.compareTo("P")!=0 
						&& scelta.compareTo("p")!=0 && scelta.compareTo("Q")!=0 && scelta.compareTo("q")!=0);
				
				int distanza=0;
				
				if(scelta.compareTo("M")==0 || scelta.compareTo("m")==0) {
					int passo=-1;
					int i = 0;
					do {
						System.out.println("WHERE TO?");
						try {
							passo=sc.nextInt();
							System.out.println("");
							i = partita.passo(passo);
							if(i==0) {
								System.out.println("NOT POSSIBLE - NO TUNNEL\n");
							}
						}
						catch(Throwable thr) {
							sc.nextLine();
							System.out.println(new WumpusGameException("THE VALUE ENTERED DOESN'T MATCH A INTEGER\n").toString());
						}
					} while(i==0);
				}
				else if (scelta.compareTo("S")==0 || scelta.compareTo("s")==0){
					do {
						System.out.println("YOU HAVE "+partita.getGiocatore().getFrecce()+" ARROWS\n\nNO. OF ROOMS (1-5)");
						try {
							distanza=sc.nextInt();
							System.out.println("");
							if(distanza>5||distanza<1) System.out.println("NOT VALID. VALUE MUST BE BETWEEN 1 AND 5");
						}
						catch(Throwable thr) {
							sc.nextLine();
							System.out.println(new WumpusGameException("THE VALUE ENTERED DOESN'T MATCH A INTEGER\n").toString());
						}
					}
					while(distanza<1||distanza>5);
					partita.scoccaFreccia(distanza);
					if(partita.getGiocatore().getFrecce()==0) {
						System.out.println("OOPS! YOU HAVE USED ALL YOUR ARROWS!");
						partita.setFinita(1);
						sc.close();
						return;
					}
				}	
				else if (scelta.compareTo("P")==0 || scelta.compareTo("p")==0) {
					System.out.println(partita.getGrotta().toString());
				}
				else if (scelta.compareTo("Q")==0 || scelta.compareTo("q")==0) {
					partita.setFinita(1);
					break;
				}
			}
			
			String nuovaPartita;
			do {
				System.out.println("DO YOU WANT TO PLAY AGAIN?\n(Y)ES\n(N)O\n");
				nuovaPartita = sc.next();
				System.out.println("");
				if (nuovaPartita.compareTo("N")!=0 && nuovaPartita.compareTo("n")!=0 
						&& nuovaPartita.compareTo("Y")!=0 && nuovaPartita.compareTo("y")!=0){
					System.out.println("NOT VALID");
				}
			}
			while (nuovaPartita.compareTo("N")!=0 && nuovaPartita.compareTo("n")!=0 
					&& nuovaPartita.compareTo("Y")!=0 && nuovaPartita.compareTo("y")!=0);
			if(nuovaPartita.compareTo("N")==0 || nuovaPartita.compareTo("n")==0) {
				sc.close();
				break;
			}
		}
	}

}
