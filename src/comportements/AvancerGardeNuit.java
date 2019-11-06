package comportements;

import environnement.Couleur;
import environnement.Plan;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvancerGardeNuit implements Behavior {

	private int compteur = 0; // Utile pour savoir quelle action faire
	private MovePilot pilot;
	Plan plan;
	Couleur couleur;
	String direction;

	public AvancerGardeNuit(MovePilot pi, Plan p, Couleur c, String d) {
		this.pilot = pi;
		this.plan = p;
		this.couleur = c;
		this.direction = d;
	}

	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

	// Permet d'avancer ou de tourner selon � o� en est le robot (objectif 1)
	public void action() {
		if (this.compteur == 0) {
			this.avancer();
		} else if (this.compteur == 1) {
			this.tournerDroite();
		} else if (this.compteur < 5) {
			this.avancer();
		}
		this.compteur = this.compteur + 1;
	}

	// Fait avancer le robot jusqu'� la case suivante
	public void avancer() {
		if (this.verifierCouleur()) {
			LCD.clear();
			LCD.refresh();
			LCD.drawString("Couleur OK", 0, 5);
			Delay.msDelay(2000);
			// Avance
			pilot.setLinearSpeed(60.);
			pilot.travel(135);
			Delay.msDelay(200);
			pilot.stop();
			// Modifie la position du robot (coordonn�es sur le plan)
			this.modifPosition();
			Delay.msDelay(2000);
			// V�rification de la couleur
			LCD.clear();
			LCD.refresh();
		} else {
			LCD.drawString("Couleur Diff�rente", 0, 5);
			Delay.msDelay(2000);
		}
	}

	// Fait tourner le robot � droite
	public void tournerDroite() {
		if (this.verifierCouleur()) {
			LCD.clear();
			LCD.refresh();
			LCD.drawString("Couleur OK", 0, 5);
			Delay.msDelay(2000);
			pilot.setLinearSpeed(60.);
			pilot.setAngularSpeed(60.);
			pilot.travel(40);
			Delay.msDelay(200);
			pilot.rotate(80.); // rotation du robot de environ 90�
			Delay.msDelay(200);
			pilot.travel(-25);
			pilot.stop();
			// modifie la direction du robot (Nord, Est, Sud, Ouest)
			this.modifDirection();
			LCD.clear();
			LCD.refresh();
			LCD.drawString("Direction du robot : ", 0, 6);
			LCD.drawString(this.direction, 0, 7);
			// v�rifie si la couleur de la case correspond � la couleur stock�e
			LCD.clear();
			LCD.refresh();
		} else {
			LCD.drawString("Couleur Diff�rente", 0, 5);
			Delay.msDelay(2000);
		}

	}

	/*
	 * V�rifie si la couleur de la case sur laquelle le robot se trouve correspond �
	 * la couleur indiqu�e sur le plan
	 */
	public boolean verifierCouleur() {
		String couleurCase = this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getCouleur();
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Couleur � trouver : ", 0, 1);
		LCD.drawString(this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getCouleur(), 0, 2);
		LCD.drawString("Couleur trouv�e : ", 0, 3);
		LCD.drawString(couleur.couleurTrouve(), 0, 4);
		return couleurCase.equalsIgnoreCase(couleur.couleurTrouve());
	}

	// Modifie la direction du robot (Nord, Est, Sud, Ouest) dans le cas d'un angle
	// de 90� � droite
	public void modifDirection() {
		if (this.direction.equalsIgnoreCase("Nord")) {
			this.direction = "Est";
		} else if (this.direction.equalsIgnoreCase("Est")) {
			this.direction = "Sud";
		} else if (this.direction.equalsIgnoreCase("Sud")) {
			this.direction = "Ouest";
		} else {
			this.direction = "Nord";
		}
	}

	// Modifie la position du robot (appel� apr�s avoir avanc�) en fonction de sa
	// direction
	public void modifPosition() {
		int[] p = new int[2];
		if (this.direction.equalsIgnoreCase("Nord")) {
			p[0] = this.plan.getPosition()[0] - 1;
			p[1] = this.plan.getPosition()[1];
			this.plan.setPosition(p);
		} else if (this.direction.equalsIgnoreCase("Est")) {
			p[0] = this.plan.getPosition()[0];
			p[1] = this.plan.getPosition()[1] + 1;
			this.plan.setPosition(p);
		} else if (this.direction.equalsIgnoreCase("Sud")) {
			p[0] = this.plan.getPosition()[0] + 1;
			p[1] = this.plan.getPosition()[1];
			this.plan.setPosition(p);
		} else {
			p[0] = this.plan.getPosition()[0];
			p[1] = this.plan.getPosition()[1] - 1;
			this.plan.setPosition(p);
		}
	}
}
