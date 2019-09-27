package robots;

import comportements.ArretUrgence;
import comportements.Avancer;
import environnement.Plan;
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

public class Sauvageon {
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
		Plan p = new Plan();
		System.out.println(p);
		
	}
}
