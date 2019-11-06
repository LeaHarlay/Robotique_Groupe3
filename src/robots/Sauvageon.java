package robots;

import java.util.ArrayList;

import comportements.ArretUrgence;
import comportements.Avancer;
import comportements.Tourner;
import environnement.Couleur;
import environnement.Plan;
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

public class Sauvageon {

	public static void main(String[] args) {

		// Début de sécurité
		LCD.drawString("Appuyer", 0, 0);
		Button.waitForAnyPress();

		// Création du chassis pour piloter le robot
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, 2);
		MovePilot pilot = new MovePilot(chassis);

		// Initialisation du capteur de couleur
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Creer les couleurs ?", 0, 0);
		Button.waitForAnyPress();
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Couleur couleur = new Couleur(cs); // Création des seuils des couleurs

		LCD.clear();
		LCD.refresh();

		// Paramètre de déplacement du garde de nuit
		Plan plan = new Plan(); // Carte
		plan.initPlateauGardeNuit(); // Initialisation du plan
		ArrayList<String> direction = new ArrayList<>();
		direction.add("Ouest");// direction initiale
		ArrayList<String> deplacement = new ArrayList<>();
		deplacement.add("Avancer");
		deplacement.add("Avancer");
		deplacement.add("Avancer");
		deplacement.add("Avancer");

		LCD.clear();
		LCD.refresh();
		LCD.drawString("Loading ... Press", 0, 0);
		Button.waitForAnyPress();

		LCD.clear();
		LCD.refresh();

		// Création des comportements
		Avancer avancer = new Avancer(pilot, plan, couleur, direction, deplacement);
		Tourner tourner = new Tourner(pilot, direction, deplacement);
		ArretUrgence arretUrgence = new ArretUrgence(cs);
		Behavior[] behavior = { avancer, tourner, arretUrgence }; // - vers +
		Arbitrator arby = new Arbitrator(behavior);
		arretUrgence.setArbitrator(arby);
		arby.go();

		// OBJECTIF 2

		/*
		 * LCD.drawString("Hello !!", 0, 1); LCD.drawString("Appuie sur moi :)",
		 * 0, 4); Button.waitForAnyPress();
		 * 
		 * EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		 * 
		 * LCD.clear(); LCD.refresh();
		 * 
		 * // Initialisation des comportements // Behavior bEmetteur = new
		 * Emetteur(); Behavior bRecepteur = new Recepteur(); Behavior
		 * bArretUrgence = new ArretUrgence(color); // Arrêt d'urgence
		 * Behavior[] bComportements = { bRecepteur, bArretUrgence }; // du
		 * moins prioritaire au plus prioritaire Arbitrator arbitrator = new
		 * Arbitrator(bComportements); if (bArretUrgence instanceof
		 * ArretUrgence) { ArretUrgence b = (ArretUrgence) bArretUrgence;
		 * b.setArbitrator(arbitrator); } arbitrator.go();
		 * 
		 */

	}
}
