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
				if ((this.plan.getPosition()[0] == x) && (this.plan.getPosition()[1] == y)) {
					this.sMarque = n;
					this.sMarque.setTraite(true);
					lSommetMarque.add(this.sMarque);
				} else {
					lSommetRestant.add(n);
				}
			}
		}
	}

	public void runIA() {
		this.traiteNoeudsAdjacent();
		this.trouveMin();

		while (!this.sMarque.isEgal(sFinal)) {
			this.traiteNoeudsAdjacent();
			this.trouveMin();
		}
		this.afficheCheminPlusCourt();
	}

	public void trouveMin() {
		// Trouver le premier traité
		int cmpt = 0;
		while (cmpt < lSommetRestant.size() && !lSommetRestant.get(cmpt).isTraite()) {
			cmpt++;
		}	
		Noeud min = lSommetRestant.get(cmpt);
		for (int i = (cmpt + 1); i < lSommetRestant.size(); i++) {
			if ((lSommetRestant.get(i).getCoutTotal() < min.getCoutTotal()) && lSommetRestant.get(i).isTraite()) {
				min = lSommetRestant.get(i);
			}
		}
		this.sMarque = min;// Devient le noeud marqué
		lSommetMarque.add(min);	
		lSommetRestant.remove(min);// Supprimer de la liste lSommetRestant
	}

	public void traiteNoeudsAdjacent() {
		ArrayList<String> lesDeplacementsPossibles = this.deplacementPossible();
		if (lesDeplacementsPossibles.contains(HAUT)) {
			if (this.isSommetRestant((this.sMarque.getPosition()[0] - 1), this.sMarque.getPosition()[1])) {
				for (int i = 0; i < lSommetRestant.size(); i++) {
					if ((lSommetRestant.get(i).getPosition()[0] == this.sMarque.getPosition()[0] - 1)
							&& (lSommetRestant.get(i).getPosition()[1] == this.sMarque.getPosition()[1])) {
						lSommetRestant.get(i).setCoutTotal(this.sMarque);
					}
				}
			}
		}
		if (lesDeplacementsPossibles.contains(BAS)) {
			if (this.isSommetRestant((this.sMarque.getPosition()[0] + 1), this.sMarque.getPosition()[1])) {
				for (int i = 0; i < lSommetRestant.size(); i++) {
					if ((lSommetRestant.get(i).getPosition()[0] == this.sMarque.getPosition()[0] + 1)
							&& (lSommetRestant.get(i).getPosition()[1] == this.sMarque.getPosition()[1])) {
						lSommetRestant.get(i).setCoutTotal(this.sMarque);
					}
				}
			}
		}
		if (lesDeplacementsPossibles.contains(GAUCHE)) {
			if (this.isSommetRestant((this.sMarque.getPosition()[0]), this.sMarque.getPosition()[1] - 1)) {
				for (int i = 0; i < lSommetRestant.size(); i++) {
					if ((lSommetRestant.get(i).getPosition()[0] == this.sMarque.getPosition()[0])
							&& (lSommetRestant.get(i).getPosition()[1] == this.sMarque.getPosition()[1] - 1)) {
						lSommetRestant.get(i).setCoutTotal(this.sMarque);
					}
				}
			}
		}
		if (lesDeplacementsPossibles.contains(DROITE)) {
			if (this.isSommetRestant((this.sMarque.getPosition()[0]), this.sMarque.getPosition()[1] + 1)) {
				for (int i = 0; i < lSommetRestant.size(); i++) {
					if ((lSommetRestant.get(i).getPosition()[0] == this.sMarque.getPosition()[0])
							&& (lSommetRestant.get(i).getPosition()[1] == this.sMarque.getPosition()[1] + 1)) {
						lSommetRestant.get(i).setCoutTotal(this.sMarque);
					}
				}
			}
		}
	}

	public ArrayList<String> deplacementPossible() {
		ArrayList<String> lesDeplacementsPossibles = new ArrayList<String>();
		// Ajout de tous les deplacements possibles dans le jeu pour une case
		lesDeplacementsPossibles.add(GAUCHE); // Vers la gauche
		lesDeplacementsPossibles.add(DROITE); // Vers la droite
		lesDeplacementsPossibles.add(BAS); // Vers le bas
		lesDeplacementsPossibles.add(HAUT); // Vers le haut
		if (this.sMarque.getPosition()[0] == 0) {
			// Il n'est pas possible de bouger de case vers le haut
			lesDeplacementsPossibles.remove(HAUT);
		}
		// Si la case vude se trouve sur le bord inferieur
		if (this.sMarque.getPosition()[0] == (LONGUEUR_PLATEAU - 1)) {
			// Il n'est pas possible de bouger de case vers le bas
			lesDeplacementsPossibles.remove(BAS);
		}
		// Si la case vide se trouve sur le bord gauche
		if (this.sMarque.getPosition()[1] == 0) {
			// Il n'est pas possible de bouger de case vers la gauche
			lesDeplacementsPossibles.remove(GAUCHE);
		}
		// Si la case vide se trouve sur le bord droit
		if (this.sMarque.getPosition()[1] == (LARGEUR_PLATEAU - 1)) {
			// Il n'est pas possible de bouger de case vers la droite
			lesDeplacementsPossibles.remove(DROITE);
		}

		return lesDeplacementsPossibles;
	}

	public boolean isSommetRestant(int x, int y) {
		boolean trouve = false;
		for (int i = 0; i < lSommetRestant.size(); i++) {
			if ((lSommetRestant.get(i).getPosition()[0] == x) && (lSommetRestant.get(i).getPosition()[1] == y)) {
				trouve = true;
			}
		}
		return trouve;
	}
	
	public void afficheCheminPlusCourt(){
		ArrayList<Noeud> chemin = new ArrayList<>();	
		int predecesseur_ord = this.lSommetMarque.get(this.lSommetMarque.size()-1).getPredecesseur()[0];
		int predecesseur_abs = this.lSommetMarque.get(this.lSommetMarque.size()-1).getPredecesseur()[1];
		chemin.add(this.lSommetMarque.get(this.lSommetMarque.size()-1));
		this.lSommetMarque.remove(this.lSommetMarque.size()-1);
		
		while ((predecesseur_ord != this.plan.getPosition()[0]) || (predecesseur_abs != this.plan.getPosition()[1])){
			for (int i = 0; i < lSommetMarque.size(); i++) {
				if ((lSommetMarque.get(i).getPosition()[0] == predecesseur_ord) && (lSommetMarque.get(i).getPosition()[1] == predecesseur_abs)) {
					predecesseur_ord = lSommetMarque.get(i).getPredecesseur()[0]; 
					predecesseur_abs = lSommetMarque.get(i).getPredecesseur()[1];
					chemin.add(lSommetMarque.get(i));
					this.lSommetMarque.remove(i);
				}
				
			}
		}
		
		// Changement des booleans pour afficher le nouveau chemin
		// On vide si il y avait un ancien chemin
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 5; y++) {
				this.plan.getCarte()[x][y].setChemin(false);
			}
		}
		// On met les booleans poàur le nouveau chemin
		for(int x=0;x<chemin.size();x++){
			this.plan.getCarte()[chemin.get(x).getPosition()[0]][chemin.get(x).getPosition()[1]].setChemin(true);
		}
		this.plan.afficheChemin();
	}
	
}
