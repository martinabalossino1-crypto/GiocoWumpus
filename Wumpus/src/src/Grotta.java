package src;

public class Grotta {
	
	static int[] vTunnels = {/*0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,*/1,4,7,0,2,9,1,3,11,2,4,13,0,3,5,4,6,14,5,7,16,0,6,8,7,9,17,1,8,10,9,11,18,2,10,12,11,13,19,3,12,14,5,13,15,14,16,19,6,15,17,8,16,18,10,17,19,12,15,18};
	Stanza[] vStanze;
	
	public Grotta() {
		this.vStanze = new Stanza[20];
		for (int i=0;i<=19;i++) {
				this.vStanze[i] = new Stanza(i);
		}
	}
	
	public Stanza getStanza(int stanza) {
		return this.vStanze[stanza];
	}
	
	public int[] getTunnel() {
		return vTunnels;
	}
	
	public static int percorsoPossibile(int stanza1, int stanza2) {
		if(vTunnels[stanza1*3]==stanza2||vTunnels[stanza1*3+1]==stanza2||vTunnels[stanza1*3+2]==stanza2) return 1;
		return 0;
	}
}
