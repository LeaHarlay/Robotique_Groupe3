package comportements;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class ArretUrgence implements Behavior {
	private EV3ColorSensor color;
	private EV3UltrasonicSensor ultra;
	private Arbitrator abry;

	public ArretUrgence(EV3ColorSensor c, EV3UltrasonicSensor u) {
		this.color = c;
		this.ultra = u;
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
		Motor.B.stop(true);
		Motor.C.stop(true);
		this.color.close();
		this.ultra.close();
		this.abry.stop();
		System.exit(0);
	}
}
