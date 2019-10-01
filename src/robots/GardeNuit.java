package robots;

import comportements.Avancer;
import environnement.Couleur;
import comportements.ArretUrgence;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class GardeNuit {
	

	public static void main(String[] args) {
		LCD.drawString("Hello !!", 0,0);
		LCD.drawString("Appuis sur", 0,2);
		LCD.drawString("un bouton :)", 0,3);
		Button.waitForAnyPress();
				
        // Initialisation des capteurs
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		
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
		
		
		/*
		EV3UltrasonicSensor ultra = new EV3UltrasonicSensor(SensorPort.S4);
		float[] captations = new float[4]; // 0..2 Couleurs, 3 ultrason
		
		// Initialisation des comportements
		Behavior bAvancer = new Avancer(); // Avancer
		Behavior bArretUrgence = new ArretUrgence(color, ultra); // Arrêt d'urgence
		Behavior[] bComportements = { bAvancer, bArretUrgence }; // du moins prioritaire au plus prioritaire
		Arbitrator arbitrator = new Arbitrator(bComportements);
		if (bArretUrgence instanceof ArretUrgence){
			ArretUrgence b = (ArretUrgence) bArretUrgence;
			b.setArbitrator(arbitrator);
		}
		arbitrator.go();
		*/
	}

}
