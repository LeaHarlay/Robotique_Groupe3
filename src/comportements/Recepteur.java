package comportements;

import java.io.InputStream;
import java.io.ObjectInputStream;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;

public class Recepteur implements Behavior {
	private NXTConnection btc;
	private BTConnector bt;
	InputStream reponse;
	ObjectInputStream oReponse;
	Object valeurO;

	public boolean takeControl() {
		return Button.ENTER.isDown();
	}

	public void suppress() {
	}

	public void action() {
		if (this.bt == null) {
			this.bt = new BTConnector();
		}

		LCD.drawString("Connection", 0, 0);
		LCD.clear();
		LCD.refresh();
		try {
			this.btc = this.bt.waitForConnection(1000, NXTConnection.PACKET);
			this.reponse = this.btc.openInputStream();
			this.oReponse = new ObjectInputStream(reponse);
			this.valeurO = this.oReponse.readObject();
			this.oReponse.close();
			this.btc.close();
			this.bt.cancel();

			// Affichage de la réponse
			LCD.clear();
			LCD.refresh();

			affichageObjetRecu(this.valeurO);
			//Delay.msDelay(5000);

			//System.out.println("\n\n\n\n\n\n\n");

			LCD.clear();
			LCD.refresh();
		} catch (Exception e) {
			//System.out.println("catch recepteur");
		}
	}

	/**
	 * Affiche le tableau re�u afin de voir la carte connue par l'adversaire et sa position
	 * 
	 */
	public static void affichageObjetRecu(Object valeur) {
		if (valeur instanceof int[][]) {
			int[][] val = (int[][]) valeur;
			for (int i = 0; i < 7; i++) {
				String[] tab = new String[5];
				for (int j = 0; j < 5; j++) {
					if (val[i][j] == 0) {
						tab[j] = " x "; // connu
					} else if (val[i][j] == 1) {
						tab[j] = " _ "; // inconnu
					} else {
						tab[j] = " o "; // position
					}
				}
				System.out.println(tab[0] + tab[1] + tab[2] + tab[3] + tab[4]);
			}
		} else {
			System.out.println("Pas même objet");

		}

	}
}
