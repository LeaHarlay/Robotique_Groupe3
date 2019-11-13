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
				} else {
					lSommetRestant.add(n);
				}
			}
		}
	}

	public void runIA() {
		this.traiteNoeudsAdjacent();
		this.trouveMin();
		System.out.println("\n");

		while (!this.sMarque.isEgal(sFinal)) {
			this.traiteNoeudsAdjacent();
			this.trouveMin();
			System.out.println("\n");
		}

		System.out.println(" Valeur des sommets marqué = " + this.lSommetMarque);
		System.out.println(" Nombre des sommets marqué = " + this.lSommetMarque.size());

	}

	public void trouveMin() {
		// Trouver le premier traité
		int cmpt = 0;
		while (cmpt < lSommetRestant.size() && !lSommetRestant.get(cmpt).isTraite()) {
			cmpt++;
		}
		Noeud min = lSommetRestant.get(cmpt);
		for (int i = (cmpt + 1); i < lSommetRestant.size(); i++) {
			if (lSommetRestant.get(i).getCoutTotal() < min.getCoutTotal()) {
				min = lSommetRestant.get(i);
			}
		}
		this.sMarque = min;// Devient le noeud marqué
		lSommetMarque.add(min);
		lSommetRestant.remove(min);// Supprimer de la liste lSommetRestant

		System.out.println("Liste noeuds restants = " + lSommetRestant);
		System.out.println("Nombre noeuds restants = " + lSommetRestant.size());
		System.out.println("Noeud marqué = " + this.sMarque);
	}

	public void traiteNoeudsAdjacent() {
		System.out.println("["+this.sMarque.getPosition()[0]+','+this.sMarque.getPosition()[1]+']');
		ArrayList<String> lesDeplacementsPossibles = this.deplacementPossible();
		System.out.println(lesDeplacementsPossibles);
		System.out.println("Contain HAUT : "+lesDeplacementsPossibles.contains(HAUT));
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
		System.out.println("Contain BAS : "+lesDeplacementsPossibles.contains(BAS));
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
		System.out.println("Contain GAUCHE : "+lesDeplacementsPossibles.contains(GAUCHE));
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
		System.out.println("Contain DROITE : "+lesDeplacementsPossibles.contains(DROITE));
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
		System.out.println(lSommetRestant);
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
}
