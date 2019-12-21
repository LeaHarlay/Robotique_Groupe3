package comportements;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
/**
 * Comportement d'arrêt d'urgence du robot
 * @author lea, amelie
 *
 */
public class ArretUrgence implements Behavior {
	private EV3ColorSensor color; // Capteur de couleur
	private Arbitrator abry; // Arbitrateur des comportements

	public ArretUrgence(EV3ColorSensor c) {
		this.color = c;
	}

	public void setArbitrator(Arbitrator a) {
		this.abry = a;
	}

	public boolean takeControl() {
		return Button.LEFT.isDown();
	}

	public void suppress() {
	}

	public void action() {
		// Arret des moteurs
		Motor.B.close();
		Motor.C.close();
		this.color.close(); // Arret du capteur de couleur
		this.abry.stop(); // Arret de l'arbitrateur
		System.exit(0); // Arret propre et sans erreure du programme
	}

}
