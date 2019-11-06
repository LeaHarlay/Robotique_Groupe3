package comportements;

import java.util.ArrayList;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Avancer implements Behavior {
	
	//==================================
	//Classe non utilisée pour le moment
	//==================================
	
	private ArrayList <String> listActions;
	
	public Avancer (ArrayList<String> a) {
		this.listActions=a;
	}
	
	public boolean takeControl() {
		return (!this.listActions.isEmpty() && (this.listActions.get(0)).equalsIgnoreCase("Avancer"));
		
	}

	public void suppress() {
		
	}

	public void action() {
		Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(60.);
		pilot.travel(50);
		Delay.msDelay(2000);
		pilot.stop();
		this.listActions.remove(0);
		
	}

}
