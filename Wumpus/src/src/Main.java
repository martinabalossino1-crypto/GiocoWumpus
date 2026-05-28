package src;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int seme;
		int livello;
		int ripeti=1;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("INSERIRE SEME");
		seme=sc.nextInt();
		
		do {
			System.out.println("SCEGLIERE LIVELLO DI DIFFICOLTA'\n1 - FACLIE\n2- NORMALE\n3 - DIFFICILE");
			livello=sc.nextInt();
		} while (livello<1 || livello>3);
		
		Partita partita;
		if(livello==1) partita = new Partita(seme,20);
		if(livello==2) partita = new Partita(seme,12);
		partita = new Partita(seme,6);
		
		partita.start();
		
		while(ripeti==1) {
			while(partita.getFinita()==0) {
				System.out.println(partita.toString());
				partita.ostacoliVicini();
				int scelta;
				System.out.println("COSA VUOI FARE?\n1 - AVANZA\n2 - SCOCCA UNA FRECCIA");
				scelta=sc.nextInt();
				int distanza=0;
				if(scelta==2) {
					do {
						System.out.println("A QUALE DISTANZA VUOI SCOCCARE LA FRECCIA?");
						distanza=sc.nextInt();
						if(distanza>5) System.out.println("LA DISTANZA ESSERE COMPRESA TRA 1 E 5");
					}
					while(distanza<1||distanza>5);
					partita.scoccaFreccia(distanza);
				}
				else {
					int passo=-1;
					partita.getGrotta();
					do {
						if(scelta==1) {
							System.out.println("INSERIRE CELLA NELLA QUALE VUOI SPOSTARTI\n");
							passo=sc.nextInt();
							if(Grotta.percorsoPossibile(partita.getGiocatore().getStanza(), passo)==0) {
								System.out.println("NOT POSSIBLE - NO TUNNEL");
							}
						}
					} while(Grotta.percorsoPossibile(partita.getGiocatore().getStanza(), passo)==0);
				partita.passo(passo);
				}
				if(partita.getGiocatore().getFrecce()==0) {
					System.out.println("OOPS! YOU HAVE USED ALL YOUR ARROWS!");
				    partita.setFinita(1);
				    return;
				}				
			}
			System.out.println("VUOI FARE UN'ALTRA PARTITA?\n1 - SI'\n0 - NO");
			ripeti=sc.nextInt();
		}
		
		sc.close();
	}

}
