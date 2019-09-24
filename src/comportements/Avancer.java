package comportements;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Avancer implements Behavior {
	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

	public void action() {
		Motor.B.forward();
		Motor.C.forward();
		Delay.msDelay(1000); // Avancer en fonction de la taille d'une case
		Motor.B.stop(true);
		Motor.C.stop();
	}
}
