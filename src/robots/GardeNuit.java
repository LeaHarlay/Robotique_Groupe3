package robots;

import comportements.AvancerGardeNuit;
import environnement.Couleur;
import environnement.Plan;
import comportements.ArretUrgence;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class GardeNuit {
	

	public static void main(String[] args) {
		Plan p = new Plan();
		p.initPlateauGardeNuit(); //création et initialisation du plan pour la garde de nuit
		
		String direction = "Nord"; //direction dans laquelle se trouve le robot au départ
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Button.waitForAnyPress();
		Couleur c = new Couleur(cs); //initialisation des couleurs
		
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Appuie pour avancer", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		LCD.refresh();
		
		allerPosteGarde(cs, p, c, direction); //se dirige vers le poste de garde au Sud (objectif 1)
		
		/*
		LCD.drawString("Hello !!", 0,0);
		LCD.drawString("Appuis sur", 0,2);
		LCD.drawString("un bouton :)", 0,3);
		Button.waitForAnyPress();
				
        // Initialisation des capteurs
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		
		Couleur c = new Couleur(color);
		
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("TERMINEE", 0, 1);
		Delay.msDelay(3000);
		
		for (int i =0;i<5;i++){
			LCD.clear();
			LCD.refresh();
			LCD.drawString("Couleur "+(i+1)+" ?", 0, 0);
			Button.waitForAnyPress();
			LCD.drawString(c.couleurTrouve(), 0, 1);
			Delay.msDelay(5000);
		}
		*/
		
		/*
		EV3UltrasonicSensor ultra = new EV3UltrasonicSensor(SensorPort.S4);
		float[] captations = new float[4]; // 0..2 Couleurs, 3 ultrason
		
		// Initialisation des comportements
		Behavior bAvancer = new Avancer(); // Avancer
		Behavior bArretUrgence = new ArretUrgence(color, ultra); // ArrÃªt d'urgence
		Behavior[] bComportements = { bAvancer, bArretUrgence }; // du moins prioritaire au plus prioritaire
		Arbitrator arbitrator = new Arbitrator(bComportements);
		if (bArretUrgence instanceof ArretUrgence){
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arbitrator);
		}
		arbitrator.go();
		*/
	}
	
	public static void allerPosteGarde(EV3ColorSensor cs, Plan p, Couleur c, String d) {
		
		//Création du chassis pour piloter le robot
		Wheel wheel1=WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C,56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},2);
		MovePilot pilot = new MovePilot(chassis);
		
		//Création des comportements pour déplacer le robot et pour l'arrêt d'urgence
		AvancerGardeNuit a = new AvancerGardeNuit(pilot, p, c, d);
        ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = {a, au}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();
		
	}

}
