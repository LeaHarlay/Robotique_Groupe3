package environnement;

import java.util.ArrayList;

public class Noeud {

	private ArrayList<int[]> chemin;
	private int coutTotal;
	
	public Noeud(int [] c,int co) {
		this.chemin.add(c);
		this.coutTotal=co;
	}
	
	public int getCoutTotal() {
		return this.coutTotal;
	}
	public ArrayList<int[]> getChemin(){
		return this.chemin;
	}
	
}
