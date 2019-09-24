package robots;

import comportements.Avancer;
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
	
	public static void showColorRGB_ex6() {
		EV3ColorSensor capteurCouleur;
		capteurCouleur = new EV3ColorSensor(SensorPort.S3);
		SensorMode valeurRGB = capteurCouleur.getRGBMode();
		float[] sample = new float[valeurRGB.sampleSize()];
		valeurRGB.fetchSample(sample, 0);
		LCD.drawString("Code couleur : ", 0, 0);
		for (int i = 0; i <= 2; i++) {
			float color = sample[i] * 1000;
			LCD.drawInt((int) color, 0, (i + 1));
		}
		Delay.msDelay(5000); // plus besoin du throws Exception
		capteurCouleur.close();
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		
		//Initialisation des moteurs
        Motor.C.setSpeed(90);
        Motor.B.setSpeed(90);
        Motor.C.setAcceleration(2000);
        Motor.B.setAcceleration(2000);
        
        // Initialisation des capteurs
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
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
	}

}
