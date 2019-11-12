package environnement;

import java.util.ArrayList;

public class Noeud {

	private int[] position = new int[2];
	private Noeud predecesseur;
	private int coutTotal;
	private int valeurDeplacement;
	private boolean traite;

	public Noeud(int x, int y, int val) {
		this.position[0] = x;
		this.position[1] = y;
		this.valeurDeplacement = val;
		this.traite = false;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}

	public Noeud getPredecesseur() {
		return predecesseur;
	}

	public void setPredecesseur(Noeud predecesseur) {
		this.predecesseur = predecesseur;
	}

	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}

	public int getValeurDeplacement() {
		return valeurDeplacement;
	}

	public void setValeurDeplacement(int valeurDeplacement) {
		this.valeurDeplacement = valeurDeplacement;
	}

	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}
	
	public String toString(){
		return Integer.toString(this.coutTotal);
	}
	
	public boolean isEgal(Noeud n){
		return (this.position[0] == n.getPosition()[0]) && (this.position[1] == n.getPosition()[1]);
	}

}
