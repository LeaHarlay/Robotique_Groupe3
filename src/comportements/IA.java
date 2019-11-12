package comportements;

import java.util.ArrayList;

import environnement.Noeud;
import environnement.Parametre;
import environnement.Plan;

public class IA implements Parametre {

	private Plan plan;
	private ArrayList<Noeud> lSommetRestant = new ArrayList<Noeud>();
	private ArrayList<Noeud> lSommetMarque = new ArrayList<Noeud>(); 
	private Noeud sMarque; // Dernier somment marque
	private Noeud sFinal; // Sommet final

	public IA(Plan p) {
		this.setPlan(p);
		this.sMarque = new Noeud(this.plan.getPosition()[0], this.plan.getPosition()[1],
				this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getValeur());
		this.sFinal = new Noeud(this.plan.getVilleAdversaire()[0], this.plan.getVilleAdversaire()[1],
				this.plan.getCarte()[this.plan.getVilleAdversaire()[0]][this.plan.getVilleAdversaire()[1]].getValeur());
		this.init();
	}

	public ArrayList<Noeud> getlSommetRestant() {
		return lSommetRestant;
	}

	public void setlSommetRestant(ArrayList<Noeud> lSommetRestant) {
		this.lSommetRestant = lSommetRestant;
	}	

	public void setlSommetMarque(ArrayList<Noeud> lSommetMarque) {
		this.lSommetMarque = lSommetMarque;
	}
	
	public ArrayList<Noeud> getlSommetMarque() {
		return lSommetMarque;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public void init() {
		// Initialisation des noeuds
		for (int x = 0; x < LONGUEUR_PLATEAU; x++) {
			for (int y = 0; y < LARGEUR_PLATEAU; y++) {
				Noeud n = new Noeud(x, y, this.plan.getCarte()[x][y].getValeur());
				lSommetRestant.add(n);
			}
		}
	}

	public void runIA() {
		while (!this.sMarque.isEgal(sFinal)){
			// Activation + MAJ des noeuds adjacents
			// Ajout du predecesseur (coordonnée ?) + Conservation des noeud marqué
			// Trouve min
			//
		}
	}

	public void trouveMin() {
		// Trouver le premier traité
		int cmpt = 0;
		while (cmpt < lSommetRestant.size() && lSommetRestant.get(cmpt).isTraite()) {
			cmpt++;
		}
		Noeud min = lSommetRestant.get(cmpt);
		for (int i = (cmpt + 1); i < lSommetRestant.size(); i++) {
			if (lSommetRestant.get(i).getCoutTotal() < min.getCoutTotal()) {
				min = lSommetRestant.get(i);
			}
		}
		System.out.println(lSommetRestant);
		System.out.println("Noeud min = " + min);
		// Supprimer de la liste lSommetRestant
		// Devient le noeud marqué
		this.sMarque = min;
	}


}
