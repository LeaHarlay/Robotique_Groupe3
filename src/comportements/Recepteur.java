package comportements;

import java.io.InputStream;
import java.io.ObjectInputStream;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Recepteur implements Behavior {
	private NXTConnection btc;
	private BTConnector bt;
	InputStream reponse;
	ObjectInputStream oReponse;
	Object valeurO ;

	public boolean takeControl() {
			if (this.bt == null) {
				this.bt = new BTConnector();
			}
			this.btc = bt.waitForConnection(1000, NXTConnection.PACKET);
			reponse = this.btc.openInputStream();
			try {
				oReponse = new ObjectInputStream(reponse);
				valeurO = oReponse.readObject();
				return (valeurO != null);
			}catch(Exception e) {
				return false;
			}
			
			//return this.btc != null;
	}
	public void suppress() {
	}

	public void action() {
		LCD.drawString("Connection", 0, 0);
		LCD.clear();
		LCD.refresh();
		try {
			//this.btc = bt.waitForConnection(1000, NXTConnection.PACKET);
			//InputStream reponse = this.btc.openInputStream();
			
			//DataInputStream dReponse = new DataInputStream(reponse);
			//ObjectInputStream oReponse = new ObjectInputStream(reponse);
			
			
			//int valeur = dReponse.read();
			//Object valeurO = oReponse.readObject();

			// ArrÃªt
			//dReponse.close();
			oReponse.close();
			this.btc.close();
			this.bt.cancel();
			
			//Affichage de la rÃ©ponse
			LCD.clear();
			LCD.refresh();
			//System.out.println(valeur);
			
			//System.out.println("Je vais afficher...");
			affichageObjetRecu(valeurO);
			//System.out.println(valeurO);
			
			/*if (valeurO instanceof Case) {
				valeurO = (Case) valeurO;
				System.out.println(((Case) valeurO).getNom());
				
			}else {
				System.out.println("Ca n'a pas marché");
			}*/
			
			Delay.msDelay(5000);
			
			System.out.println("\n\n\n\n\n\n\n");
			
			LCD.clear();
			LCD.refresh();
		} catch (Exception e) {
		}
	}
	
	public static void affichageObjetRecu(Object valeur){
		if (valeur instanceof int[][]) {
			int[][] val = (int[][]) valeur;
			for (int i = 0 ; i<7 ; i++) {
				String[] tab = new String[5];
				for (int j = 0; j<5;j++) {
					if(val[i][j]==0) {
						tab[j]=" x "; //connu
					}else if(val[i][j]==1) {
						tab[j]=" _ "; //inconnu
					}else {
						tab[j]=" o "; //position
					}	
				}
				System.out.println(tab[0]+tab[1]+tab[2]+tab[3]+tab[4]);
				//LCD.drawString(tab[0]+tab[1]+tab[2]+tab[3]+tab[4],0,i);
			}
		}else {
			//LCD.drawString("Pas même objet",0,2);
			System.out.println("Pas même objet");
			
		}

	}
}
