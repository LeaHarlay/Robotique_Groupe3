package comportements;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Recepteur implements Behavior {
	private NXTConnection btc;
	private BTConnector bt;

	public boolean takeControl() {
		if (this.bt == null) {
			this.bt = new BTConnector();
			this.btc = bt.waitForConnection(1000, NXTConnection.PACKET);
		}
		return this.btc != null;
	}

	public void suppress() {
	}

	public void action() {
		LCD.drawString("Connection", 0, 0);
		LCD.clear();
		LCD.refresh();
		try {
			InputStream reponse = this.btc.openInputStream();
			DataInputStream dReponse = new DataInputStream(reponse);
			int valeur = dReponse.read();

			// Arrêt
			dReponse.close();
			this.btc.close();
			this.bt.cancel();
			
			//Affichage de la réponse
			LCD.clear();
			LCD.refresh();
			System.out.println(valeur);
			
			
			Delay.msDelay(5000);			
			
			LCD.clear();
			LCD.refresh();

		} catch (Exception e) {
		}
	}
}
