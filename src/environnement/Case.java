package environnement;

/**
 * Correspond à une case de 12cm x 12cm du plateau délimitée par les bandes
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

	public void setDecouvert(Boolean d) {
		this.decouvert = d;
	}

	public void setChemin(Boolean c) {
		this.chemin = c;
	}

	public String getCouleur() {
		return this.couleur;
	}

	public int getValeur() {
		return this.valeur;
	}

	public boolean getDecouvert() {
		return this.decouvert;
	}

	public boolean getChemin() {
		return this.chemin;
	}
}
