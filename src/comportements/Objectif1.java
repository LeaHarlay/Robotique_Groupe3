package comportements;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class Objectif1 implements Behavior{
	private ArrayList<String> listActions;
	private String nomRobot;

	public Objectif1(ArrayList<String> actions, String nom) {
		this.listActions=actions;
		this.nomRobot=nom;
	}

	public boolean takeControl() {
		return (Button.RIGHT.isDown() && this.listActions.isEmpty());
	}

	public void suppress() {
	}

	public void action() {
		//ajoute les actions que le robot doit faire afin d'atteindre le poste de garde
		if (this.nomRobot.equalsIgnoreCase("GardeNuit")){
			this.listActions.add("Avancer");
			this.listActions.add("Droite");
			this.listActions.add("Avancer");
			this.listActions.add("Avancer");
			this.listActions.add("Avancer");
		}else if(this.nomRobot.equalsIgnoreCase("Sauvageon")) {
			for (int i = 0;i<4;i++) {
				this.listActions.add("Avancer");
			}
		}
	}
}
