package comportements;

import java.util.ArrayList;

import environnement.Couleur;
import environnement.Parametre;
import environnement.Plan;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Avancer implements Behavior, Parametre {

	private ArrayList<String> listActions; // Chemin du robot vers sa
											// destination
	private MovePilot pilot;
	private Plan plan; // Cartographie
	private Couleur couleur; // Seuils de d�tection des couleurs
	private String direction; // Orientation du robot

	public Avancer(MovePilot pi, Plan p, Couleur c, String d, ArrayList<String> actions) {
		this.listActions = actions;
		this.pilot = pi;
		this.plan = p;
		this.couleur = c;
		this.direction = d;
	}

	public boolean takeControl() {
		return (!this.listActions.isEmpty() && this.listActions.get(0).equals(AVANCER));
	}

	public void suppress() {
		pilot.stop();
	}

	public void action() {
		pilot.setLinearSpeed(60.); // Vitesse
		pilot.travel(135); // Distance 1 case + Ligne noire

		this.modifPosition(); // Modifie la position dans le plan

		LCD.clear();
		LCD.refresh();
		if (this.plan.verifierCouleur(this.couleur)) {
			System.out.println("\n\nJe suis sur");
			System.out.println("le bon chemin");
		} else {
			System.out.println("\n\nJe suis PERDU !");
			System.out.println("  0_o  ");
		}
		Delay.msDelay(1000);
		this.listActions.remove(0);
	}

	/**
	 * Modification de la position Change la position du robot sur la
	 * cartographie
	 */
	public void modifPosition() {
		int[] p = new int[2];
		if (this.direction.equalsIgnoreCase(NORD)) {
			p[0] = this.plan.getPosition()[0] - 1;
			p[1] = this.plan.getPosition()[1];
		} else if (this.direction.equalsIgnoreCase(EST)) {
			p[0] = this.plan.getPosition()[0];
			p[1] = this.plan.getPosition()[1] + 1;
		} else if (this.direction.equalsIgnoreCase(SUD)) {
			p[0] = this.plan.getPosition()[0] + 1;
			p[1] = this.plan.getPosition()[1];
		} else {
			p[0] = this.plan.getPosition()[0];
			p[1] = this.plan.getPosition()[1] - 1;
		}
		this.plan.setPosition(p);
		
		// Découverte de la case
		this.plan.caseDecouverte();
	}

}
