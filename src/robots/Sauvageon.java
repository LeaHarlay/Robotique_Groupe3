package robots;

import comportements.ArretUrgence;
import comportements.AvancerSauvageon;
import environnement.Couleur;
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

public class Sauvageon {

	public static void main(String[] args) {

// OBJECTIF 1

		Plan p = new Plan();
		p.initPlateauSauvageon(); // création et initialisation du plan pour les sauvageons

		String direction = "Ouest"; // direction dans laquelle se trouve le robot au départ

		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Button.waitForAnyPress();
		Couleur c = new Couleur(cs); // initialisation des couleurs

		LCD.clear();
		LCD.refresh();
		LCD.drawString("Appuie pour avancer", 0, 0);
		Button.waitForAnyPress();
		LCD.clear();
		LCD.refresh();

		allerPosteGarde(cs, p, c, direction);// se dirige vers le poste de garde au Nord (objectif 1)

		// OBJECTIF 2
		LCD.drawString("Hello !!", 0, 1);
		LCD.drawString("Appuie sur moi :)", 0, 4);
		Button.waitForAnyPress();

		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);

		LCD.clear();
		LCD.refresh();

		// Initialisation des comportements
		// Behavior bEmetteur = new Emetteur();
		Behavior bRecepteur = new Recepteur();
		Behavior bArretUrgence = new ArretUrgence(color); // ArrÃªt d'urgence
		Behavior[] bComportements = { bRecepteur, bArretUrgence }; // du moins prioritaire au plus prioritaire
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
		AvancerSauvageon a = new AvancerSauvageon(pilot, p, c, d);
		ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = { a, au }; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();

	}
}
