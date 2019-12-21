package comportements;

import java.util.ArrayList;

import environnement.Parametre;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

/**
 * Comportement permettant au robot de tourner
 * @author lea, amelie
 *
 */
public class Tourner implements Behavior, Parametre {

	private ArrayList<String> listActions; // Chemin du robot vers le poste de garde
	private MovePilot pilot;
	private ArrayList<String> direction; // Orientation du robot

	public Tourner(MovePilot pi, ArrayList<String> d, ArrayList<String> actions) {
		this.listActions = actions;
		this.pilot = pi;
		this.direction = d;
	}

	public boolean takeControl() {
		return (!this.listActions.isEmpty() && this.listActions.get(0).equals(DROITE));
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
		pilot.rotate(80.); // rotation du robot de environ 90°
		Delay.msDelay(200);
		pilot.travel(-25);
		// Modifie la position du robot (coordonnées sur le plan)
		this.modifDirection();
		this.listActions.remove(0);
		Delay.msDelay(500);
	}

	/**
	 * Modification de l'orientation Change l'orientation du robot par une
	 * rotation par default de 90°
	 */
	public void modifDirection() {
		if (this.direction.get(0).equalsIgnoreCase(NORD)) {
			this.direction.set(0, EST);
		} else if (this.direction.get(0).equalsIgnoreCase(EST)) {
			this.direction.set(0, SUD);
		} else if (this.direction.get(0).equalsIgnoreCase(SUD)) {
			this.direction.set(0, OUEST);
		} else {
			this.direction.set(0, NORD);
		}
	}

}
