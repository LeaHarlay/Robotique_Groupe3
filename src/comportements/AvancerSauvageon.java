package comportements;

import environnement.Couleur;
import environnement.Plan;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvancerSauvageon implements Behavior{
	
	private int compteur=0; // Utile pour savoir quelle action faire
	private MovePilot pilot;
	Plan plan;
	Couleur couleur;
	String direction;

	public AvancerSauvageon(MovePilot pi, Plan p, Couleur c, String d) {
		this.pilot=pi;
		this.plan=p;
		this.couleur=c;
		this.direction=d;
	}
	
	public boolean takeControl() {
		return true;
	}

	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

	// Permet d'avancer du bon nombre de cases (objectif 1)
	public void action() {
		if (this.compteur<4) {
			if (this.verifierCouleur()) {
				LCD.drawString("Couleur OK", 0, 1);
				Delay.msDelay(2000);
				pilot.setLinearSpeed(60.);
				pilot.travel(135);
				Delay.msDelay(200);
				this.compteur=this.compteur+1;
				// Modifie la position du robot (coordonnées sur le plan)
				this.modifPosition();
				// Vérification de la couleur
				LCD.clear();
				LCD.refresh();
			
			}else{
				LCD.drawString("Couleur Différente", 0, 1);
				Delay.msDelay(2000);
			}
		}
	}
	
	/* Vérifie si la couleur de la case sur laquelle le robot se trouve 
	correspond à la couleur indiquée sur le plan */
	public boolean verifierCouleur() {
		String couleurCase = this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getCouleur();
		return couleurCase.equalsIgnoreCase(couleur.couleurTrouve());
	}
	
	// Modifie la position du robot (appelé après avoir avancé) en fonction de sa direction
	public void modifPosition() {
		int[] p = new int [2];
		if (this.direction.equalsIgnoreCase("Nord")) {
			p[0]=this.plan.getPosition()[0]-1;
			p[1]=this.plan.getPosition()[1];
			this.plan.setPosition(p);
		}else if(this.direction.equalsIgnoreCase("Est")) {
			p[0]=this.plan.getPosition()[0];
			p[1]=this.plan.getPosition()[1]+1;
			this.plan.setPosition(p);
		}else if(this.direction.equalsIgnoreCase("Sud")) {
			p[0]=this.plan.getPosition()[0]+1;
			p[1]=this.plan.getPosition()[1];
			this.plan.setPosition(p);
		}else{
			p[0]=this.plan.getPosition()[0];
			p[1]=this.plan.getPosition()[1]-1;
			this.plan.setPosition(p);
		} 
	}
}
