package environnement;

/**
 * Correspond au noeud (sommet) du graphe de l'algorithme Dijkstra. Chaque case devient un noeud
 * @author lea, amelie
 *
 */
public class Noeud {

	private int[] position = new int[2]; // Position du noeud correspondant à la position de la case
	private int[] predecesseur = new int[2]; // Position du noeud précédant ce dernier 
	private int coutTotal; // Coût total du déplacement pour arriver jusqu'à lui
	private int valeurDeplacement; // Valeur qu'il additionnera leur du déplement vers un neoud adjacent
	private boolean traite; // Si le noeud à été traité au moins une fois

	public Noeud(int x, int y, int val) {
		this.position[0] = x;
		this.position[1] = y;
		this.valeurDeplacement = val;
		this.traite = false;
		this.coutTotal = 0;
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
		if (!this.isTraite() || ((this.isTraite() && this.coutTotal > (n.getCoutTotal() + n.getValeurDeplacement()))
				&& this.coutTotal != 0)) {
			this.traite = true; // Le noeud aura été traité au moins une fois
			this.coutTotal = n.getCoutTotal() + n.getValeurDeplacement(); // Son coût total est ajouté
			this.setPredecesseur(n.getPosition()); // Ajout des coordonnées du noeud prédecesseur
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

	public String toString() {
		return "[" + this.position[0] + "," + this.position[1] + "]  / [" + this.predecesseur[0] + ","
				+ this.predecesseur[1] + "] " + this.getCoutTotal() + " " + this.isTraite();
	}

	public boolean isEgal(Noeud n) {
		return (this.position[0] == n.getPosition()[0]) && (this.position[1] == n.getPosition()[1]);
	}

	public int[] getPredecesseur() {
		return predecesseur;
	}

	public void setPredecesseur(int[] predecesseur) {
		this.predecesseur = predecesseur;
	}

}
