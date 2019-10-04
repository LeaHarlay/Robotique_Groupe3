package robots;

import comportements.ArretUrgence;
import comportements.Avancer;
import comportements.AvancerSauvageon;
import comportements.Tourner;
import environnement.Couleur;
import environnement.Plan;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Sauvageon {
	
	public static void main(String[] args) {
		//Plan p = new Plan();
		//System.out.println(p);
		Plan p = new Plan();
		p.initPlateauSauvageon();
		
		String direction = "Ouest";
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Button.waitForAnyPress();
		Couleur c = new Couleur (cs);
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Appuie pour avancer", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		LCD.refresh();
		allerPosteGarde(cs, p, c, direction);
	}
	
	public static void allerPosteGarde(EV3ColorSensor cs, Plan p, Couleur c, String d) {
		
		Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
		MovePilot pilot = new MovePilot(chassis);
		
		AvancerSauvageon a = new AvancerSauvageon(pilot, p, c, d);
        ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = {a, au}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();
		
	}
}
