package environnement;

public class Case {
	private String nom;
	private String couleur;
	private int valeur;
	private boolean decouvert;
	
	public Case(String n, String c, int v) {
		this.nom=n;
		this.couleur=c;
		this.valeur=v;
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
	//GETTER
	public String getNom() {
		return this.nom;
	}
	public String getcouleur() {
		return this.couleur;
	}
	public int getValeur() {
		return this.valeur;
	}
	public boolean getDecouvert() {
		return this.decouvert;
	}
}
