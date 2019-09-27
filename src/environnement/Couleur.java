package environnement;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Couleur {
	// Constantes
	public final String BLANC = "Blanc";
	public final String ROUGE = "Rouge";
	public final String ORANGE = "Orange";
	public final String BLEU = "Bleu";
	public final String VERT = "Vert";
	public final int MESURE = 3;

	// Seuil RGB des couleurs
	private ArrayList<int[]> blanc = new ArrayList<>();
	private ArrayList<int[]> rouge = new ArrayList<>();
	private ArrayList<int[]> orange = new ArrayList<>();
	private ArrayList<int[]> bleu = new ArrayList<>();
	private ArrayList<int[]> vert = new ArrayList<>();

	// Capteur de couleur
	EV3ColorSensor capteur;

	public Couleur(EV3ColorSensor capteurCouleur) {
		this.capteur = capteurCouleur;
		this.init();
	}

	// Initialisation des seuils des couleurs
	public void init() {
		LCD.drawString("Apprentissage... ", 0, 0);
		this.initBlanc();
	}

	// Initialisation des seuils RGB du blanc
	public void initBlanc() {
		// Liste des valeurs qui determineront le seuil
		ArrayList<Integer> valR = new ArrayList<Integer>();
		ArrayList<Integer> valG = new ArrayList<Integer>();
		ArrayList<Integer> valB = new ArrayList<Integer>();
		

		LCD.drawString("Blanc, OK ?", 0, 1);
		Button.waitForAnyPress();

		// Prise des mesures
		for (int i = 0; i < this.MESURE; i++) {
			float[] valeurs = new float[3];
			valeurs = this.colorRGV();
			valR.add((int) valeurs[0]);
			valG.add((int) valeurs[1]);
			valB.add((int) valeurs[2]);
		}

		int[] r = { this.min(valR), this.max(valR) };
		int[] g = { this.min(valG), this.max(valG) };
		int[] b = { this.min(valB), this.max(valB) };

		this.blanc.add(r);
		this.blanc.add(g);
		this.blanc.add(b);
		
		LCD.clear();
		LCD.refresh();
		LCD.drawString("Blanc : ", 0, 0);
		LCD.drawString("R {"+this.blanc.get(0)[0]+" ; "+this.blanc.get(0)[1]+"}", 0, 0);
		LCD.drawString("G {"+this.blanc.get(1)[0]+" ; "+this.blanc.get(1)[1]+"}", 0, 1);
		LCD.drawString("B {"+this.blanc.get(2)[0]+" ; "+this.blanc.get(2)[1]+"}", 0, 2);
		
		Delay.msDelay(5000);
	}

	// Retourne un tableau des valeurs RGB pour une couleur
	public float[] colorRGV() {
		SensorMode valeurRGB = this.capteur.getRGBMode();
		float[] tabRGB = new float[valeurRGB.sampleSize()];
		valeurRGB.fetchSample(tabRGB, 0);
		for (int i = 0; i <= 2; i++) {
			tabRGB[i] = tabRGB[i] * 1000;
		}
		return tabRGB;
	}

	// Valeur minimal d'une liste
	public int min(ArrayList<Integer> list) {
		int valMin = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) < valMin) {
				valMin = list.get(i);
			}
		}
		return valMin;
	}

	// Valeur maximale d'une liste
	public int max(ArrayList<Integer> list) {
		int valMax = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) > valMax) {
				valMax = list.get(i);
			}
		}
		return valMax;
	}
}
