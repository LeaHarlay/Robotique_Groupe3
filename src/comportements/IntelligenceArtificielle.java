package comportements;

import environnement.Plan;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class IntelligenceArtificielle implements Behavior{
	
	private Plan plan; // Cartographie
	
	public IntelligenceArtificielle(Plan p) {
		this.plan = p ;
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
