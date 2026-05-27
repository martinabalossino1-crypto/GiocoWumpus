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
				int passo;
				do {
					System.out.println("PROSSIMO PASSO: ");
					passo=sc.nextInt();
				} while(passo!=partita.grotta.getTunnel()[partita.giocatore.getStanza()*3]
						&&passo!=partita.grotta.getTunnel()[partita.giocatore.getStanza()*3+1]
								&&passo!=partita.grotta.getTunnel()[partita.giocatore.getStanza()*3+2]);
				partita.passo(passo);
			}
			System.out.println("VUOI FARE UN'ALTRA PARTITA?\n1 - SI'\n0 - NO");
			ripeti=sc.nextInt();
		}
		
		sc.close();
	}

}
