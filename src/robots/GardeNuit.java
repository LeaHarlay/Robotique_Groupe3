package robots;

import comportements.Avancer;
import comportements.Emetteur;
import comportements.Objectif1;
import comportements.Recepteur;
import comportements.Tourner;
import environnement.Couleur;
import environnement.Plan;

import java.util.ArrayList;

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
import lejos.utility.Delay;

public class GardeNuit {

	public static void main(String[] args) {
		// Début de sécurité
		LCD.drawString("GARDE NUIT - N", 0, 0);
		LCD.drawString("Appuyez sur une", 0, 1);
		LCD.drawString("touche", 0, 2);
		
		Button.waitForAnyPress();

		// Création du chassis pour piloter le robot
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60.);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, 2);
		MovePilot pilot = new MovePilot(chassis);

		// Initialisation du capteur de couleur
		LCD.clear();
		LCD.refresh();
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Couleur couleur = new Couleur(cs); // Création des seuils des couleurs

		LCD.clear();
		LCD.refresh();
		

		// Paramètre de déplacement du garde de nuit
		Plan plan = new Plan(); // Carte
		plan.initPlateauGardeNuit(); // Initialisation du plan
		ArrayList<String> direction = new ArrayList<>();
		direction.add("Nord");// direction initiale
		ArrayList<String> deplacement = new ArrayList<>();

		LCD.clear();
		LCD.refresh();
		LCD.drawString("Appuyez sur", 0, 0);
		LCD.drawString("une touche", 0, 1);
		LCD.drawString("pour lancer le", 0, 2);
		LCD.drawString("programme", 0, 3);
		Button.waitForAnyPress();

		Delay.msDelay(2000);
		
		LCD.clear();
		LCD.refresh();

		// Création des comportements
		Avancer bAvancer = new Avancer(pilot, plan, couleur, direction, deplacement);
		Tourner bTourner = new Tourner(pilot, direction, deplacement);
		ArretUrgence bArretUrgence = new ArretUrgence(cs, pilot);
		Emetteur bEmetteur = new Emetteur();
		Objectif1 bObjectif1 = new Objectif1(deplacement,"GardeNuit");
		Recepteur bRecepteur = new Recepteur();
		Behavior[] behavior = {bObjectif1 ,bAvancer, bTourner, bRecepteur, bEmetteur, bArretUrgence }; // - vers +
		Arbitrator arby = new Arbitrator(behavior);
		if (bArretUrgence instanceof ArretUrgence) {
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arby);
		}
		arby.go();
		
	}

}
