package robots;

import comportements.IA_Algo;
import environnement.Plan;

public class testIA {

	public static void main(String[] args) {
		Plan plan = new Plan(); // Carte
		plan.initPlateauGardeNuit(); // Initialisation du plan
		
		IA_Algo iA = new IA_Algo(plan);
		iA.runIA();
	}

}
