package robots;

import comportements.ArretUrgence;
import comportements.Avancer;
import comportements.Emetteur;
import comportements.Recepteur;
import environnement.Plan;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Sauvageon {

	public static void main(String[] args) {
		// LEA
		LCD.drawString("Hello !!", 0,1);
		LCD.drawString("Appuie sur moi :)", 0,4);
		Button.waitForAnyPress();
				
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		
		LCD.clear();
		LCD.refresh();
		
		
		// Initialisation des comportements
		//Behavior bEmetteur = new Emetteur(); 
		Behavior bRecepteur = new Recepteur();
		Behavior bArretUrgence = new ArretUrgence(color); // Arrêt d'urgence
		Behavior[] bComportements = {bRecepteur, bArretUrgence }; // du moins prioritaire au plus prioritaire
		Arbitrator arbitrator = new Arbitrator(bComportements);
		if (bArretUrgence instanceof ArretUrgence){
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arbitrator);
		}
		arbitrator.go();
		
		
		
		
		
		// AMELIE
		/*
		//Plan p = new Plan();
		//System.out.println(p);
		Button.waitForAnyPress();
		
		Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
		MovePilot pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(60.);
		pilot.setAngularSpeed(60.);
		
		pilot.travel(135);
		Delay.msDelay(200);
		pilot.travel(40);
		Delay.msDelay(200);
		while (pilot.isMoving()) Thread.yield();
		pilot.rotate(78.); //rotation du robot de 90�
		LCD.drawInt((int)(pilot.getMovement().getDistanceTraveled()), 0, 0);
		Delay.msDelay(200);
		pilot.travel(-15);

		pilot.stop();
		
		/*
		Button.waitForAnyPress();
		pilot.travel(120);
		Delay.msDelay(1000);
		Motor.B.setSpeed(60);
		Motor.C.setSpeed(60);
		Motor.B.forward();
		Motor.C.forward();
		Delay.msDelay(4000);
		Motor.C.stop(true);
		Motor.B.stop();
		Motor.B.close();
		Motor.C.close();
		pilot.stop();*/
		//Motor.C.rotate(386);
		
	}
}
