package robots;

import comportements.Avancer;
import comportements.Emetteur;
import comportements.Recepteur;
import environnement.Couleur;
import environnement.Plan;
import comportements.ArretUrgence;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class GardeNuit {
	

	public static void main(String[] args) {
		LCD.drawString("Hello !!", 0,1);
		LCD.drawString("Appuie sur moi :)", 0,4);
		Button.waitForAnyPress();
		
		LCD.clear();
		LCD.refresh();
				
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		
		/*
		Plan p = new Plan();
		p.initPlateauGardeNuit();
		p.affichePlateau();
		Delay.msDelay(5000);
		*/
		

        // Initialisation des capteurs
		/*
		
		Couleur c = new Couleur(color);
		
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("TERMINEE", 0, 1);
		Delay.msDelay(3000);
		
		for (int i =0;i<5;i++){
			LCD.clear();
			LCD.refresh();
			LCD.drawString("Couleur "+(i+1)+" ?", 0, 0);
			Button.waitForAnyPress();
			LCD.drawString(c.couleurTrouve(), 0, 1);
			Delay.msDelay(5000);
		}
		*/
				
		// Initialisation des comportements
		Behavior bEmetteur = new Emetteur(); 
		//Behavior bRecepteur = new Recepteur(btc);
		Behavior bArretUrgence = new ArretUrgence(color); // Arrêt d'urgence
		Behavior[] bComportements = { bEmetteur, bArretUrgence }; // du moins prioritaire au plus prioritaire
		Arbitrator arbitrator = new Arbitrator(bComportements);
		if (bArretUrgence instanceof ArretUrgence){
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arbitrator);
		}
		arbitrator.go();
	}

}
