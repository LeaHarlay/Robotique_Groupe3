package comportements;

import environnement.Couleur;
import environnement.Plan;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class AvancerGardeNuit implements Behavior{
	private int compteur=0;
	private MovePilot pilot;
	Plan plan;
	Couleur couleur;
	String direction;

	public AvancerGardeNuit(MovePilot pi, Plan p, Couleur c, String d) {
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

	public void action() {
		if (this.compteur==0) {
			this.avancer();
		}else if (this.compteur==1) {
			this.tournerDroite();
		}else if (this.compteur<5) {
			this.avancer();
		}
		this.compteur=this.compteur+1;
	}

	public void avancer() {
		pilot.setLinearSpeed(60.);
		pilot.travel(135);
		Delay.msDelay(200);
		pilot.stop();
		LCD.clear();
		LCD.refresh();
		this.modifPosition();
		LCD.drawString("Position du robot",1,0);
		LCD.drawInt(this.plan.getPosition()[0],2,0);
		LCD.drawInt(this.plan.getPosition()[1], 2, 2);
		if (this.verifierCouleur()) {
			LCD.drawString("Couleur OK", 6, 1);
			Delay.msDelay(2000);
		}else{
			LCD.drawString("Couleur Différente", 6, 1);
			Delay.msDelay(2000);
		}
		
	}
	public void tournerDroite() {
		pilot.setLinearSpeed(60.);
		pilot.setAngularSpeed(60.);
		pilot.travel(40);
		Delay.msDelay(200);
		pilot.rotate(80.); //rotation du robot de 90°
		Delay.msDelay(200);
		pilot.travel(-25);
		pilot.stop();
		LCD.clear();
		LCD.refresh();
		this.modifDirection();
		if (this.verifierCouleur()) {
			LCD.drawString("Couleur OK", 6, 1);
			Delay.msDelay(2000);
		}else{
			LCD.drawString("Couleur Différente", 6, 1);
			Delay.msDelay(2000);
		}
		
	}
	public boolean verifierCouleur() {
		String couleurCase = this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getCouleur();
		LCD.drawString("Couleur à trouver",3,0);
		LCD.drawString(this.plan.getCarte()[this.plan.getPosition()[0]][this.plan.getPosition()[1]].getCouleur(),4,0);
		LCD.drawString("Couleur trouvée",4,0);
		LCD.drawString(couleur.couleurTrouve(),5,0);
		return couleurCase.equalsIgnoreCase(couleur.couleurTrouve());
	}
	public void modifDirection() {
		if (this.direction.equalsIgnoreCase("Nord")) {
			this.direction="Est";
		}else if(this.direction.equalsIgnoreCase("Est")) {
			this.direction="Sud";
		}else if(this.direction.equalsIgnoreCase("Sud")) {
			this.direction="Ouest";
		}else{
			this.direction="Nord";
		}
	}
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
