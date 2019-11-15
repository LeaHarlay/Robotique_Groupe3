package comportements;

import java.io.OutputStream;
import environnement.Case;
import environnement.Plan;
import java.io.ObjectOutputStream;
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
	

	private String bluetoothRobot1="00:16:53:43:8E:49";
	private String nomRobot1="Jon7";
	private String bluetoothRobot2="00:16:53:43:96:91";
	//private String nomRobot2="";

	private BTConnector bt;
	
	public boolean takeControl() {
		return Button.UP.isDown();
	}

	public void suppress() {
	}

	public void action() {
		Plan p = new Plan();
		p.initPlateauGardeNuit();
		int[][] aEnvoyer = preparationEnvoi(p.getCarte(), p.getPosition());
		
		LCD.clear();
		LCD.refresh();
		EV3 ev = LocalEV3.get();
		System.out.println("--"+ev.getName()+"--");
		
		try {
			if (bt == null) {
				bt = new BTConnector();
			}
		}catch (Exception e){
			
		}
		
		if ((ev.getName()).equalsIgnoreCase(nomRobot1)) {
			try {
				LCD.clear();
				LCD.drawString("connexion", 0, 0);
				LCD.refresh();
				
				//le premier param�tre est l'adresse du r�cepteur affich� sur l'�cra de l'�metteur apr�s association (pair) bluetooth
				BTConnection btc = bt.connect(bluetoothRobot2, NXTConnection.PACKET);
				
				OutputStream requete = btc.openOutputStream();
				ObjectOutputStream oRequete = new ObjectOutputStream(requete);
				System.out.println("Envoi de la carte");
				oRequete.writeObject(aEnvoyer);
				oRequete.flush();
				System.out.println("Envoy�");
				oRequete.close();
				btc.close();
				LCD.clear();
				Delay.msDelay(2000);
			} catch (Exception e) {
			}
		}else {
			try {
				
				LCD.clear();
				LCD.drawString("connexion", 0, 0);
				LCD.refresh();
				
				//le premier param�tre est l'adresse du r�cepteur affich� sur l'�cra de l'�metteur apr�s association (pair) bluetooth
				BTConnection btc = bt.connect(bluetoothRobot1, NXTConnection.PACKET);
				
				OutputStream requete = btc.openOutputStream();
				ObjectOutputStream oRequete = new ObjectOutputStream(requete);
				System.out.println("Envoi de la carte");
				oRequete.writeObject(aEnvoyer);
				oRequete.flush();
				System.out.println("Envoy�");
				oRequete.close();
				btc.close();
				LCD.clear();
				Delay.msDelay(2000);
			} catch (Exception e) {
			}
		}
			
		
		
		
	}
	/**
	 * Pr�pare le tableau � envoyer
	 * Met des 0,1,2 dans un tableau afin d'envoyer la carte connue et la position du robot
	 * 
	 */
	public static int[][] preparationEnvoi(Case [][] carte, int [] position){
		int [][] resultat = new int[7][5];
		for (int i = 0 ; i<7 ; i++) {
			for (int j = 0; j<5;j++) {
				if (position[0] == i && position[1] == j) {
					resultat[i][j] = 2; //position du joueur
				}else if (carte[i][j].getDecouvert()) {
					resultat[i][j] = 1; //case d�couverte
				}else {
					resultat[i][j] = 0; //case non d�couverte
				}
			}
		}
		return resultat;
	}
}
