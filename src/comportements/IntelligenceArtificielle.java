package comportements;

import java.util.ArrayList;

import environnement.Noeud;
import environnement.Parametre;
import environnement.Plan;
import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;
/**
 * Comportement pour trouver le chemin le plus court entre la position du robot et la ville adverse.
 * @author lea, amelie
 *
 */
public class IntelligenceArtificielle implements Behavior, Parametre {

	private Plan plan; // Cartographie
	private ArrayList<Noeud> lSommetRestant = new ArrayList<Noeud>(); // Liste des noeuds non utilisés pour trouver le chemin le plus court
	private ArrayList<Noeud> lSommetMarque = new ArrayList<Noeud>(); // Liste des noeuds de l'arbre ayant été parcourus au moins une fois (=traité) et utilisés
	private Noeud sMarque; // Dernier somment marqué/utilisé
	private Noeud sFinal; // Sommet final

	public IntelligenceArtificielle(Plan p) {
		this.plan = p;
	}

	public boolean takeControl() {
		return Button.DOWN.isDown();
	}

	public void suppress() {
	}

	public void action() {
		this.initIA();
		this.runIA();
	}

	/**
	 * Initialisation de la ville adversaire a atteindre (noeud final)
	 */
	public void initIA() {
		this.sFinal = new Noeud(this.plan.getVilleAdversaire()[0], this.plan.getVilleAdversaire()[1],
				this.plan.getCarte()[this.plan.getVilleAdversaire()[0]][this.plan.getVilleAdversaire()[1]].getValeur());
		this.init();
	}

	/**
	 * Initialisation de l'ensemble des noeuds de l'abre
	 */
	public void init() {
		for (int x = 0; x < LONGUEUR_PLATEAU; x++) {
			for (int y = 0; y < LARGEUR_PLATEAU; y++) {
				Noeud n = new Noeud(x, y, this.plan.getCarte()[x][y].getValeur());
				// Si la position du noeud créé correspond à la position du robot actuelle, il est ajouté à la liste des noeuds marqués
				if ((this.plan.getPosition()[0] == x) && (this.plan.getPosition()[1] == y)) {
					this.sMarque = n;
					this.sMarque.setTraite(true); 
					lSommetMarque.add(this.sMarque);
				} else {
					lSommetRestant.add(n); // 
				}
			}
		}
	}

	/**
	 * Lancement de l'algorithme Dijkstra
	 */
	public void runIA() {
		this.traiteNoeudsAdjacent(); // Recherche et traitement des noeuds adjacents au dernier noeud marqué
		this.trouveMin(); // Recherche, parmi les noeuds restant, le noeud traité avec la plus faible valeur pour y arriver. Il devient le noeud marqué.
		// L'algorithme continu ainsi de suite tant que le noeud traité est différents du noeud final
		while (!this.sMarque.isEgal(sFinal)) {
			this.traiteNoeudsAdjacent();
			this.trouveMin();
		}
		this.afficheCheminPlusCourt(); // Traitement et affichage du chemin le plus court trouvé
	}

	/**
	 * Recherche, parmi les noeuds restants, le noeud traité avec la plus faible valeur pour y arriver. 
	 */
	public void trouveMin() {
		int cmpt = 0;
		// Recherche du premier noeud traité dans la liste des noeuds restant
		while (cmpt < lSommetRestant.size() && !lSommetRestant.get(cmpt).isTraite()) {
			cmpt++;
		}
		Noeud min = lSommetRestant.get(cmpt);
		// Recherche, à la suite du premier noeud trouvé, s'il y en aurait un autre avec une valeur inférieure
		for (int i = (cmpt + 1); i < lSommetRestant.size(); i++) {
			if ((lSommetRestant.get(i).getCoutTotal() < min.getCoutTotal()) && lSommetRestant.get(i).isTraite()) {
				min = lSommetRestant.get(i);
			}
		}
		this.sMarque = min;// Devient le noeud marqué
		lSommetMarque.add(min); // Ajout à la liste des noeuds marqués
		lSommetRestant.remove(min);// Suppression de la liste des noeuds restants
	}

	/**
	 * Traitement des noeuds adjacents au noeud marqué.
	 */
	public void traiteNoeudsAdjacent() {
		ArrayList<String> lesDeplacementsPossibles = this.deplacementPossible(); // Recherche des déplacements possibles depuis le noeud marqué
		// Si le déplacement est possible, le noeud adjacent est traité et son coût total pour y arriver est mise à jour s'il est inférieur au côut total qu'il avait s'il avait déjà été traité
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

	/**
	 * Retourne l'ensemble des déplacements possibles à partir du noeud marqué
	 * @return
	 */
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

	/**
	 * Retourne si les coordonnées correspondent à celles d'un noeud restant 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isSommetRestant(int x, int y) {
		boolean trouve = false;
		for (int i = 0; i < lSommetRestant.size(); i++) {
			if ((lSommetRestant.get(i).getPosition()[0] == x) && (lSommetRestant.get(i).getPosition()[1] == y)) {
				trouve = true;
			}
		}
		return trouve;
	}

	/**
	 * Affiche le chemin le plus court obtenu par l'algorithme
	 */
	public void afficheCheminPlusCourt() {
		ArrayList<Noeud> chemin = new ArrayList<>();
		int predecesseur_ord = this.lSommetMarque.get(this.lSommetMarque.size() - 1).getPredecesseur()[0];
		int predecesseur_abs = this.lSommetMarque.get(this.lSommetMarque.size() - 1).getPredecesseur()[1];
		chemin.add(this.lSommetMarque.get(this.lSommetMarque.size() - 1));
		this.lSommetMarque.remove(this.lSommetMarque.size() - 1);
		while ((predecesseur_ord != this.plan.getPosition()[0]) || (predecesseur_abs != this.plan.getPosition()[1])) {
			for (int i = 0; i < lSommetMarque.size(); i++) {
				if ((lSommetMarque.get(i).getPosition()[0] == predecesseur_ord)
						&& (lSommetMarque.get(i).getPosition()[1] == predecesseur_abs)) {
					predecesseur_ord = lSommetMarque.get(i).getPredecesseur()[0];
					predecesseur_abs = lSommetMarque.get(i).getPredecesseur()[1];
					chemin.add(lSommetMarque.get(i));
					this.lSommetMarque.remove(i);
				}
			}
		}
		// Changement des booleans pour afficher le nouveau chemin
		// On vide si il y avait un ancien chemin
		for (int x = 0; x < LONGUEUR_PLATEAU; x++) {
			for (int y = 0; y < LARGEUR_PLATEAU; y++) {
				this.plan.getCarte()[x][y].setChemin(false);
			}
		}
		// On met les booleans poàur le nouveau chemin
		for (int x = 0; x < chemin.size(); x++) {
			this.plan.getCarte()[chemin.get(x).getPosition()[0]][chemin.get(x).getPosition()[1]].setChemin(true);
		}
		this.plan.afficheChemin();
	}
}
