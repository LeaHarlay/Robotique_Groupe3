package comportements;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class ArretUrgence implements Behavior {
	private EV3ColorSensor color;
	private Arbitrator abry;
	private MovePilot pilot;

	public ArretUrgence(EV3ColorSensor c, MovePilot pi) {
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
		this.pilot.stop();
		this.color.close();
		this.abry.stop();
		System.exit(0);
	}

}
