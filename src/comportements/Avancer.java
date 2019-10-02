package comportements;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Avancer implements Behavior {
	private int compteur;
	
	public Avancer (int c) {
		this.compteur=c;
		c=this.compteur;
	}
	
	public boolean takeControl() {
		return true;
	}


	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

	public void action() {
		if (this.compteur < 4 || (4<this.compteur && this.compteur<9) || (this.compteur>9 && this.compteur<14)) {
			Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
			Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
			Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
			MovePilot pilot = new MovePilot(chassis);
			pilot.setLinearSpeed(60.);
			//pilot.setAngularSpeed(60.);
			pilot.travel(135);
			Delay.msDelay(200);
			
			/*while (pilot.isMoving()) Thread.yield();
			pilot.rotate(78.); //rotation du robot de 90°
			LCD.drawInt((int)(pilot.getMovement().getDistanceTraveled()), 0, 0);
			Delay.msDelay(200);
			pilot.travel(-15);*/
			pilot.stop();
			
			this.compteur=this.compteur+1;
		}else if (this.compteur==4 || this.compteur==9){
			this.tourner();
		}
		
	}
	public void tourner() {
		Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(60.);
		pilot.setAngularSpeed(60.);
		pilot.travel(40);
		Delay.msDelay(200);
		pilot.rotate(80.); //rotation du robot de 90°
		Delay.msDelay(200);
		pilot.travel(-25);
		pilot.stop();
		this.compteur=this.compteur+1;
	}
	
	public void setCompteur (int c) {
		if (this.compteur>c){
			c=this.compteur;
		}else {
			this.compteur=c;
		}
	}

}
