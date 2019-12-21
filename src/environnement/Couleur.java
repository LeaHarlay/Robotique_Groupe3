package environnement;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

/**
 * Gestion et apprentissage des couleurs au robot
 * @author lea, amelie
 *
 */
public class Couleur implements Parametre {

	// Seuil RGB des couleurs
	private ArrayList<int[]> blanc = new ArrayList<>();
	private ArrayList<int[]> rouge = new ArrayList<>();
	private ArrayList<int[]> orange = new ArrayList<>();
	private ArrayList<int[]> bleu = new ArrayList<>();
	private ArrayList<int[]> vert = new ArrayList<>();

	// Capteur de couleur
	private EV3ColorSensor capteur;

	public Couleur(EV3ColorSensor capteurCouleur) {
		this.capteur = capteurCouleur;
		this.init();
	}

	/**
	 * Initialisation des seuils des couleurs
	 */
	public void init() {
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation", 0, 0);
		this.initBlanc();
		this.initRouge();
		this.initOrange();
		this.initBleu();
		this.initVert();
		Delay.msDelay(1000);
	}

	/**
	 * Initialisation des seuils RVB du blanc
	 */
	public void initBlanc() {
		LCD.drawString("Blanc : ", 0, 1);
		Button.waitForAnyPress();
		this.blanc = creationSeuilCouleur();

		// Modification de l'affichage
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("Blanc : OK ", 0, 1);
	}

	/**
	 * Initialisation des seuils RVB du rouge
	 */
	public void initRouge() {
		LCD.drawString("Rouge : ", 0, 2);
		Button.waitForAnyPress();
		this.rouge = creationSeuilCouleur();

		// Modification de l'affichage
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("Blanc : OK ", 0, 1);
		LCD.drawString("Rouge : OK ", 0, 2);
	}

	/**
	 * Initialisation des seuils RVB du orange
	 */
	public void initOrange() {
		LCD.drawString("Orange : ", 0, 3);
		Button.waitForAnyPress();
		this.orange = creationSeuilCouleur();

		// Modification de l'affichage
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("Blanc : OK ", 0, 1);
		LCD.drawString("Rouge : OK ", 0, 2);
		LCD.drawString("Orange : OK ", 0, 3);
	}

	/**
	 * Initialisation des seuils RVB du bleu
	 */
	public void initBleu() {
		LCD.drawString("Bleu : ", 0, 4);
		Button.waitForAnyPress();
		this.bleu = creationSeuilCouleur();

		// Modification de l'affichage
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("Blanc : OK ", 0, 1);
		LCD.drawString("Rouge : OK ", 0, 2);
		LCD.drawString("Orange : OK ", 0, 3);
		LCD.drawString("Bleu : OK ", 0, 4);
	}

	/**
	 * Initialisation des seuils RVB du vert
	 */
	public void initVert() {
		LCD.drawString("Vert : ", 0, 5);
		Button.waitForAnyPress();
		this.vert = creationSeuilCouleur();

		// Modification de l'affichage
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Initialisation ", 0, 0);
		LCD.drawString("Blanc : OK ", 0, 1);
		LCD.drawString("Rouge : OK ", 0, 2);
		LCD.drawString("Orange : OK ", 0, 3);
		LCD.drawString("Bleu : OK ", 0, 4);
		LCD.drawString("Vert : OK ", 0, 5);
	}

	/**
	 * Initialise la liste des seuils des valeurs de la couleur perçue
	 * @return
	 */
	public ArrayList<int[]> creationSeuilCouleur() {
		// Liste des valeurs qui determineront le seuil
		ArrayList<Integer> valR = new ArrayList<Integer>();
		ArrayList<Integer> valV = new ArrayList<Integer>();
		ArrayList<Integer> valB = new ArrayList<Integer>();

		// Prise des mesures
		for (int i = 0; i < NB_MESURE; i++) {
			float[] valeurs = new float[3];
			valeurs = this.colorRVB(); // On récupère les valeurs RVB
			// Séparation des valeurs dans la liste correspondante
			valR.add((int) valeurs[0]); // Valeur 1 : Rouge
			valV.add((int) valeurs[1]); // Valeur 2 : Vert
			valB.add((int) valeurs[2]); // Valeur 3 : Bleu
		}

		// Recherche des valeurs seuils minimales et maximal pour chaque couleur
		// (RVB)
		int[] r = { this.min(valR) - MARGE, this.max(valR) + MARGE };
		int[] v = { this.min(valV) - MARGE, this.max(valV) + MARGE };
		int[] b = { this.min(valB) - MARGE, this.max(valB) + MARGE };

		// Ajout des seuils dans une liste définissant les seuils RVB pour une
		// couleur
		ArrayList<int[]> result = new ArrayList<int[]>();
		// Ajout en queue des différents seuils
		result.add(r);
		result.add(v);
		result.add(b);

		return result;
	}

	/**
	 * Retourne un tableau des valeurs RGB pour une couleur
	 * @return
	 */
	public float[] colorRVB() {
		SensorMode valeurRVB = this.capteur.getRGBMode();
		float[] tabRVB = new float[valeurRVB.sampleSize()];
		valeurRVB.fetchSample(tabRVB, 0);
		for (int i = 0; i < 3; i++) {
			tabRVB[i] = tabRVB[i] * 1000;
		}
		return tabRVB;
	}

	/**
	 * Valeur minimal d'une liste
	 * @param list
	 * @return
	 */
	public int min(ArrayList<Integer> list) {
		int valMin = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) < valMin) {
				valMin = list.get(i);
			}
		}
		return valMin;
	}

	/**
	 * Valeur maximale d'une liste
	 * @param list
	 * @return
	 */
	public int max(ArrayList<Integer> list) {
		int valMax = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > valMax) {
				valMax = list.get(i);
			}
		}
		return valMax;
	}

	/**
	 * Retourne le nom de la couleur détecté par le capteur
	 * @return
	 */
	public String couleurTrouve() {
		float[] valeurs = new float[3];
		valeurs = this.colorRVB(); // On récupère les valeurs RVB de la couleur
		String result = "Inconnue";
		if (correspondanceCouleur(valeurs, this.blanc)) {
			result = BLANC;
		} else if (correspondanceCouleur(valeurs, this.rouge)) {
			result = ROUGE;
		} else if (correspondanceCouleur(valeurs, this.orange)) {
			result = ORANGE;
		} else if (correspondanceCouleur(valeurs, this.bleu)) {
			result = BLEU;
		} else if (correspondanceCouleur(valeurs, this.vert)) {
			result = VERT;
		}
		return result;
	}

	/**
	 * Retourne si la couleur détecté correspond ou non à une couleur que le robot connait
	 * @param valeurs
	 * @param seuil
	 * @return
	 */
	public boolean correspondanceCouleur(float[] valeurs, ArrayList<int[]> seuil) {
		return ((((int) valeurs[0] <= seuil.get(0)[1]) && ((int) valeurs[0] >= seuil.get(0)[0]))
				&& (((int) valeurs[1] <= seuil.get(1)[1]) && ((int) valeurs[1] >= seuil.get(1)[0]))
				&& (((int) valeurs[2] <= seuil.get(2)[1]) && ((int) valeurs[2] >= seuil.get(2)[0])));
	}
}
