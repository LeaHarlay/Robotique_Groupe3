package comportements;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class GestionRobot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//========================================
		//Classe test, non utilisée pour le moment
		//========================================
		
		int compteur=0;
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		Button.waitForAnyPress();
		
		//Plan p = new Plan();
		//p.initPlateauSauvageon();	
		
		/*
		Avancer a = new Avancer(compteur);
		Tourner t = new Tourner(compteur); 
        ArretUrgence au = new ArretUrgence(cs);
		Behavior[] bArray = {a, t, au}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		au.setArbitrator(arby);
		arby.go();
		//a.setCompteur(compteur);
		
		*/
		Delay.msDelay(1000);
	}

}
