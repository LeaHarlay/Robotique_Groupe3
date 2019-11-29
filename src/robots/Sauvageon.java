package robots;

import java.util.ArrayList;

import comportements.ArretUrgence;
import environnement.Couleur;
import environnement.Parametre;
import comportements.Avancer;
import comportements.Emetteur;
import comportements.IntelligenceArtificielle;
import comportements.Recepteur;
import comportements.Tourner;
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
import lejos.utility.Delay;

public class Sauvageon implements Parametre {

	public static void main(String[] args) {

		// Début de sécurité
		LCD.drawString("SAUVAGEON - O", 0, 0);
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

		// Paramètre de déplacement du sauvageon
		Plan plan = new Plan(); // Carte
		plan.initPlateauSauvageon();
		String direction = OUEST;// direction initiale
		ArrayList<String> deplacement = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			deplacement.add(AVANCER);
		}

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
		ArretUrgence bArretUrgence = new ArretUrgence(cs);
		Emetteur bEmetteur = new Emetteur(plan);
		Recepteur bRecepteur = new Recepteur();
		IntelligenceArtificielle bIA = new IntelligenceArtificielle(plan);
		Behavior[] behavior = { bAvancer, bTourner, bRecepteur, bEmetteur, bIA, bArretUrgence };
		Arbitrator arby = new Arbitrator(behavior);
		if (bArretUrgence instanceof ArretUrgence) {
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arby);
		}
		arby.go();

	}
}
