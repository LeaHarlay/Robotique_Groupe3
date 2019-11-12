package comportements;

import java.util.ArrayList;

import environnement.Action;
import environnement.Plan;

public class IA_Algo {
	private Plan plan; // Cartographie

	public IA_Algo(Plan p) {
		this.plan=p;
	}

	public void runIA() {
		// La position actuelle du robot est une partie du chemin
		//this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].setChemin(true);

		ArrayList<Action> lFils = new ArrayList<>();
		ArrayList<ArrayList<Action>> lNiveaux = new ArrayList<>();
		ArrayList<String> lDeplacement = new ArrayList<>();
		Action aInit = new Action(this.plan);
		lFils.add(aInit); // Creation du premier niveau
		lNiveaux.add(lFils); // Ajout du premier niveau dans l'arbre
		lFils = new ArrayList<Action>();
		int cmpt = 0;
		while (cmpt < 1) {
			lDeplacement = lNiveaux.get(0).get(0).deplacementsPossibles();
			System.out.println(lDeplacement);
			lFils = new ArrayList<Action>();
			for (int i = 0; i < lDeplacement.size(); i++) {
				Action aFils = new Action(lNiveaux.get(0).get(0).getPlan());
				aFils = aFils.simuler(lDeplacement.get(i));
				lFils.add(aFils);
				aFils.getPlan().afficheChemin();
			}
			lDeplacement.clear();
			lNiveaux.get(0).remove(0);
			if (lNiveaux.get(0).isEmpty()) {
				lNiveaux.add(lFils);
				lFils = new ArrayList<Action>();
				lNiveaux.remove(0);
				cmpt++;
			}
		}
		
	}

}
