package environnement;

public class Case {

	private String couleur;
	private int valeur;
	private boolean decouvert;
	private boolean chemin;

	public Case(String c, int v) {
		this.couleur = c;
		this.valeur = v;
		this.chemin = false;
	}

	// SETTER
	public void setDecouvert(Boolean d) {
		this.decouvert = d;
	}

	public void setChemin(Boolean c) {
		this.chemin = c;
	}
	// GETTER

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
