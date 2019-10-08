package comportements;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.InputStream;

import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Emetteur implements Behavior {
	
	public boolean takeControl() {
		return Button.RIGHT.isDown();
	}

	public void suppress() {
		
	}

	public void action() {
		LCD.clear();
		LCD.refresh();
		EV3 ev = LocalEV3.get();
		System.out.println("--"+ev.getName()+"--");
			
		try {
			BTConnector bt = new BTConnector();
			BTConnection btc = bt.connect("00:16:53:43:AD:EE", NXTConnection.PACKET);//le premier param�tre est l'adresse du r�cepteur affich� sur l'�cra de l'�metteur apr�s association (pair) bluetooth


			LCD.clear();
			LCD.drawString("connexion", 0, 0);
			LCD.refresh();

			InputStream reponse = btc.openInputStream();
			OutputStream requete = btc.openOutputStream();
			DataInputStream dReponse = new DataInputStream(reponse);
			DataOutputStream dRequete = new DataOutputStream(requete);
			System.out.println("\n"+"\n"+"Envoi");
			dRequete.write(12); // Ecrit une valeur dans le flux
			dRequete.flush(); // force l'envoi
			System.out.println("\n"+"Envoyé");
			dReponse.close();
			dRequete.close();
			btc.close();
			
			LCD.clear();
			Delay.msDelay(5000);
			
		} catch (Exception e) {
		}
	}
}
