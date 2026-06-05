package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int seme;
		String livello;
		String grotta;
		String scelta;
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			
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
				File f = new File("Istruzioni.txt");
		        final BufferedReader reader = new BufferedReader(new FileReader(f));
		        String currentLine;
		        while ((currentLine = reader.readLine())!=null) {
			        System.out.println(currentLine);
		        }
		        reader.close();
			}
			
			
			System.out.println("INSERIRT SEED:");
			seme=sc.nextInt();
			
			do {
				System.out.println("DIFFICULTY: (E)ASY, (N)ORMAL, (H)ARD?");
				livello=sc.next();
				if (livello.compareTo("E")!=0 && livello.compareTo("e")!=0 && livello.compareTo("N")!=0 && livello.compareTo("n")!=0 && livello.compareTo("H")!=0 && livello.compareTo("h")!=0) {
					System.out.println("NOT VALID\n");
				}
			} while (livello.compareTo("E")!=0 && livello.compareTo("e")!=0 && livello.compareTo("N")!=0 && livello.compareTo("n")!=0 && livello.compareTo("H")!=0 && livello.compareTo("h")!=0);
			
			Partita partita;
			if(livello.compareTo("E")==0 || livello.compareTo("e")==0) partita = new Partita(seme,20);
			else if(livello.compareTo("N")==0 || livello.compareTo("n")==0) partita = new Partita(seme,12);
			else partita = new Partita(seme,6);
			
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
						passo=sc.nextInt();
						System.out.println("");
						i = partita.passo(passo);
						if(i==0) {
								System.out.println("NOT POSSIBLE - NO TUNNEL\n");
						}
					} while(i==0);
				partita.passo(passo);
				}
				else if (scelta.compareTo("S")==0 || scelta.compareTo("s")==0){
					do {
						System.out.println("YOU HAVE "+partita.getGiocatore().getFrecce()+" ARROWS\n\nNO. OF ROOMS (1-5)");
						distanza=sc.nextInt();
						System.out.println("");
						if(distanza>5) System.out.println("NOT VALID");
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
			if(nuovaPartita.compareTo("N")==0 || nuovaPartita.compareTo("n")==0) break;
			
		}
		sc.close();
	}

}
