package environnement;

/**
 * Correspond à une case de 12cm x 12cm du plateau délimité par les bandes
 * noires de 1,5cm
 * 
 * @author lea, amelie
 *
 */
public class Case {

	private String couleur; // Sa couleur
	private int valeur; // Sa valeur
	private boolean decouvert; // Si elle a été découverte = TRUE
	private boolean chemin; // Si elle fait parti du chemin le plus court trouvé
							// par l'IA = TRUE

	public Case(String c, int v) {
		this.couleur = c;
		this.valeur = v;
		this.chemin = false;
	}

	/**
	 * Modifie l'état de découverte d'une case
	 * 
	 * @param d
	 */
	public void setDecouvert(Boolean d) {
		this.decouvert = d;
	}

	/**
	 * Retourne si une case fait partie du chemin le plus court trouvé par
	 * l'intellience artificielle
	 * 
	 * @param c
	 */
	public void setChemin(Boolean c) {
		this.chemin = c;
	}

	/**
	 * Retourne la couleur d'une case
	 * 
	 * @return
	 */
	public String getCouleur() {
		return this.couleur;
	}

	/**
	 * Retourne la valeur d'une case
	 * 
	 * @return
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * Informe de l'état de découverte d'une case
	 * 
	 * @return
	 */
	public boolean getDecouvert() {
		return this.decouvert;
	}

	/**
	 * Informe si une case fait partie du chemin le plus court trouvé par
	 * l'intellience artificielle
	 * 
	 * @return
	 */
	public boolean getChemin() {
		return this.chemin;
	}
}
