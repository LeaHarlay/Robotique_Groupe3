package comportements;

import java.util.ArrayList;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Tourner implements Behavior {

	private ArrayList<String> listActions;
	private MovePilot pilot;
	private ArrayList<String> direction;

	public Tourner(MovePilot pi, ArrayList<String> d, ArrayList<String> actions) {
		this.listActions = actions;
		this.pilot = pi;
		this.direction = d;
	}

	public boolean takeControl() {
		return (!this.listActions.isEmpty() && this.listActions.get(0).equals("Droite"));
	}

	public void suppress() {
		pilot.stop();
	}

	public void action() {
		// Tourner
		Delay.msDelay(2000);
		pilot.setLinearSpeed(60.);
		pilot.setAngularSpeed(60.);
		pilot.travel(40);
		Delay.msDelay(200);
		pilot.rotate(80.); // rotation du robot de environ 90�
		Delay.msDelay(200);
		pilot.travel(-25);

		// Modifie la position du robot (coordonn�es sur le plan)
		this.modifDirection();
		this.listActions.remove(0);
		Delay.msDelay(1000);
	}

	// Modifie la direction + 90 degres
	public void modifDirection() {
		if (this.direction.get(0).equalsIgnoreCase("Nord")) {
			this.direction.set(0, "Est");
		} else if (this.direction.get(0).equalsIgnoreCase("Est")) {
			this.direction.set(0, "Sud");
		} else if (this.direction.get(0).equalsIgnoreCase("Sud")) {
			this.direction.set(0, "Ouest");
		} else {
			this.direction.set(0, "Nord");
		}
	}

}
