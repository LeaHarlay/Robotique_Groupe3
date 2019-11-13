package environnement;

import java.util.ArrayList;

public class Noeud {

	private int[] position = new int[2];
	private int[] predecesseur = new int[2];
	private int coutTotal;
	private int valeurDeplacement;
	private boolean traite;

	public Noeud(int x, int y, int val) {
		this.position[0] = x;
		this.position[1] = y;
		this.valeurDeplacement = val;
		this.traite = false;
		this.coutTotal= 0;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}


	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(Noeud n) {
		System.out.println("isTrait : " + this.isTraite());
		System.out.println("cout total sup : " + (this.isTraite() && this.coutTotal > (n.getCoutTotal()+n.getValeurDeplacement())));
		System.out.println("cout total = 0  : " + (this.coutTotal != 0));
		System.out.println("reponse  : " + (!this.isTraite() || ((this.isTraite() && this.coutTotal > (n.getCoutTotal()+n.getValeurDeplacement())) && this.coutTotal != 0)));
		if (!this.isTraite() || ((this.isTraite() && this.coutTotal > (n.getCoutTotal()+n.getValeurDeplacement())) && this.coutTotal != 0)){
			this.traite=true; // Le noeud aura été traité au moins une fois
			this.coutTotal = n.getCoutTotal() + n.getValeurDeplacement(); // Son coût total est ajouté
			this.setPredecesseur(n.getPosition()) ; // Ajout des coordonnées du noeud prédecesseur
		}
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
		return "["+this.position[0] +","+this.position[1] +"] " + this.getCoutTotal() + " " + this.isTraite();
	}
	
	public boolean isEgal(Noeud n){
		return (this.position[0] == n.getPosition()[0]) && (this.position[1] == n.getPosition()[1]);
	}

	public int[] getPredecesseur() {
		return predecesseur;
	}

	public void setPredecesseur(int[] predecesseur) {
		this.predecesseur = predecesseur;
	}
	
}
