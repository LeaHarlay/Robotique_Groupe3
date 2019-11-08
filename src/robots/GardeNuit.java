package robots;

import comportements.AvancerGardeNuit;
import comportements.Emetteur;
import comportements.Recepteur;
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

		LCD.drawString("Hello !!", 0, 1);
		LCD.drawString("Appuie sur moi :)", 0, 4);
		Button.waitForAnyPress();
		/*
		//Essai comportements
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		ArrayList<String> liste = new ArrayList<>();
		liste.add("Avancer");
		liste.add("Avancer");
		liste.add("Pas Avancer");
		Avancer a = new Avancer(liste);
		ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = { a, au }; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();
		*/
		// OBJECTIF 1

		/*
		 * Plan p = new Plan(); p.initPlateauGardeNuit(); //création et initialisation
		 * du plan pour la garde de nuit
		 * 
		 * String direction = "Nord"; //direction dans laquelle se trouve le robot au
		 * départ
		 * 
		 * EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3); 
		 * Couleur c = new Couleur(cs); //initialisation des couleurs
		 * 
		 * LCD.clear(); LCD.refresh(); LCD.drawString("Appuie pour avancer", 0, 0);
		 * Button.waitForAnyPress(); LCD.clear(); LCD.refresh();
		 * 
		 * allerPosteGarde(cs, p, c, direction); //se dirige vers le poste de garde au
		 * Sud (objectif 1)
		 * 
		 */

		// OBJECTIF 2

		
		LCD.clear();
		LCD.refresh();

		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);

		// Initialisation des comportements
		Behavior bEmetteur = new Emetteur();
		Behavior bRecepteur = new Recepteur();
		Behavior bArretUrgence = new ArretUrgence(color); // ArrÃªt d'urgence
		Behavior[] bComportements = {bRecepteur, bEmetteur, bArretUrgence }; // du moins prioritaire au plus prioritaire
		Arbitrator arbitrator = new Arbitrator(bComportements);
		if (bArretUrgence instanceof ArretUrgence) {
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arbitrator);
		}
		arbitrator.go();
		
	}

	public static void allerPosteGarde(EV3ColorSensor cs, Plan p, Couleur c, String d) {

		// Création du chassis pour piloter le robot
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, 2);
		MovePilot pilot = new MovePilot(chassis);

		// Création des comportements pour déplacer le robot et pour l'arrêt d'urgence
		//création liste []
		AvancerGardeNuit a = new AvancerGardeNuit(pilot, p, c, d);
		ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = { a, au }; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();

	}

}
