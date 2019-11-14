package robots;

import comportements.IA;
import environnement.Plan;

public class TestIA {

	public static void main(String[] args) {
		Plan plan = new Plan();
		plan.initPlateauGardeNuit();
		int [] posTest = new int [2];
		posTest[0] = 3;
		posTest[1] = 1;
		plan.setPosition(posTest);
		IA ia = new IA(plan);
		ia.runIA();
	
	}

}
