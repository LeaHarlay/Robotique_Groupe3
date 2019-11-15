package comportements;


import environnement.Parametre;
import environnement.Plan;
import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class IntelligenceArtificielle implements Behavior, Parametre {

	private Plan plan; // Cartographie


	public IntelligenceArtificielle(Plan p) {
		this.plan = p;
	}

	public boolean takeControl() {
		return Button.DOWN.isDown();
	}

	public void suppress() {
	}

	public void action() {
		IA ia = new IA(plan);
		ia.runIA();
	}
}
