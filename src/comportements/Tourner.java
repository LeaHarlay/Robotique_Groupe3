package comportements;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Tourner implements Behavior {
	
	//==================================
	//Classe non utilisée pour le moment
	//==================================
	
	private int compteur;
	
	public Tourner (int c) {
		this.compteur=c;
		c=this.compteur;
	}
	public boolean takeControl() {
		return (this.compteur==4);
	}
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
		Motor.B.close();
		Motor.C.close();
	}
	public void action() {
		System.out.println("dans action de tourner, compteur = ");
		System.out.println(compteur);
		if (this.compteur == 4 ) {
			Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
			Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
			Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
			MovePilot pilot = new MovePilot(chassis);
			pilot.setLinearSpeed(60.);
			pilot.setAngularSpeed(60.);
			pilot.travel(40);
			Delay.msDelay(200);
			pilot.rotate(78.); //rotation du robot de 90°
			Delay.msDelay(200);
			pilot.travel(-15);
			pilot.stop();
			this.compteur=this.compteur+1;
		}
	}

}
