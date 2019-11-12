package environnement;

public class Case {
	
	private String nom;
	private String couleur;
	private int valeur;
	private boolean decouvert;
	private boolean chemin;
	
	public Case(String n, String c, int v) {
		this.nom=n;
		this.couleur=c;
		this.valeur=v;
		this.chemin = false;
	}
	
	//SETTER
	public void setNom(String n) {
		this.nom=n;
	}
	public void setCouleur(String c) {
		this.couleur=c;
	}
	public void setValeur(int v) {
		this.valeur=v;
	}
	public void setDecouvert(Boolean d) {
		this.decouvert=d;
	}
	public void setChemin(Boolean c) {
		this.chemin=c;
	}
	//GETTER
	public String getNom() {
		return this.nom;
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
