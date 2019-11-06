package comportements;

import java.util.ArrayList;

import environnement.Couleur;
import environnement.Plan;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Avancer implements Behavior {

	private ArrayList<String> listActions;
	private MovePilot pilot;
	private Plan plan;
	private Couleur couleur;
	private ArrayList<String> direction;

	public Avancer(MovePilot pi, Plan p, Couleur c, ArrayList<String> d, ArrayList<String> actions) {
		this.listActions = actions;
		this.pilot = pi;
		this.plan = p;
		this.couleur = c;
		this.direction = d;
	}

	public boolean takeControl() {
		return (!this.listActions.isEmpty() && this.listActions.get(0).equals("Avancer"));
	}

	public void suppress() {
		pilot.stop();
	}

	public void action() {
		// Avance
		pilot.setLinearSpeed(60.);
		pilot.travel(135);
		Delay.msDelay(200);
		
		// Modifie la position du robot (coordonn�es sur le plan)
		this.modifPosition();
		Delay.msDelay(1000);
		LCD.clear();
		LCD.refresh();
		if (this.plan.verifierCouleur(this.couleur)) {
			LCD.drawString("Je suis sur", 0, 3);
			LCD.drawString("le bon chemin", 0, 4);
		} else {
			LCD.drawString("Je suis PERDU !", 0, 3);
			LCD.drawString("  0_o  ", 0, 4);
		}
		Delay.msDelay(2000);
		this.listActions.remove(0);
	}


	// Modifie la direction +90 degres
	public void modifDirection() {
		if (this.direction.get(0).equalsIgnoreCase("Nord")) {
			this.direction.set(0, "Est");
		} else if (this.direction.get(0).equalsIgnoreCase("Est")) {
			this.direction.set(0, "Sud") ;
		} else if (this.direction.get(0).equalsIgnoreCase("Sud")) {
			this.direction.set(0, "Ouest");
		} else {
			this.direction.set(0, "Nord");
		}
	}

	// Modifie la position du robot 
	public void modifPosition() {
		int[] p = new int[2];
		if (this.direction.get(0).equalsIgnoreCase("Nord")) {
			p[0] = this.plan.getPosition()[0] - 1;
			p[1] = this.plan.getPosition()[1];
			this.plan.setPosition(p);
		} else if (this.direction.get(0).equalsIgnoreCase("Est")) {
			p[0] = this.plan.getPosition()[0];
			p[1] = this.plan.getPosition()[1] + 1;
			this.plan.setPosition(p);
		} else if (this.direction.get(0).equalsIgnoreCase("Sud")) {
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
