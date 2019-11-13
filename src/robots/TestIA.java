package robots;

import comportements.IA;
import environnement.Plan;

public class TestIA {

	public static void main(String[] args) {
		Plan plan = new Plan();
		plan.initPlateauGardeNuit();
		IA ia = new IA(plan);
		ia.runIA();
	
	}

}
