package environnement;

import java.util.ArrayList;

import lejos.hardware.lcd.LCD;

public class Plan {

	private Case[][] carte = new Case[7][5];
	private int[] position = new int[2];
	private int[] villeAdversaire = new int[2];

	public Plan() {
		this.init();
	}

	// initialisation du plan (nom, couleur et valeur de chacune des cases)
	public void init() {
		// camps militaires
		this.carte[0][0] = new Case("Camp militaire", "rouge", 1);
		this.carte[5][3] = new Case("Camp militaire", "rouge", 1);
		// cases de d�part
		this.carte[0][4] = new Case("Case d�part", "blanc", 1);
		this.carte[6][0] = new Case("Case d�part", "blanc", 1);
		// mar�cages
		for (int i = 1; i < 4; i++) {
			this.carte[4][i] = new Case("Mar�cage", "orange", 5);
		}
		this.carte[2][4] = new Case("Mar�cage", "orange", 5);
		// mur
		for (int i = 0; i < 3; i++) {
			this.carte[i][1] = new Case("Mur", "bleu", 10);
		}
		for (int i = 2; i < 4; i++) {
			this.carte[i][2] = new Case("Mur", "bleu", 10);
		}
		for (int i = 5; i < 7; i++) {
			this.carte[i][4] = new Case("Mur", "bleu", 10);
		}
		// prairies
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.carte[i][j] == null) {
					this.carte[i][j] = new Case("Prairie", "vert", 1);
				}
			}
		}
	}

	public String toString() {
		String aff = "";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.position[0] == i && this.position[1] == j) {
					aff = aff + "   moi   ";
				} else if (this.villeAdversaire[0] == i && this.villeAdversaire[1] == j) {
					aff = aff + "   toi   ";
				} else {
					aff = aff + "  " + this.carte[i][j].getCouleur() + "  ";
				}
			}
			aff = aff + "\n";
		}
		return aff;
	}

	// initialisation de la partie connue du plan pour le sauvageon
	// (les bool�ens permettant de dire qu'une case est d�couverte sont � True)
	public void initPlateauSauvageon() {
		this.villeAdversaire[0] = 6;
		this.villeAdversaire[1] = 0;
		this.position[0] = 0;
		this.position[1] = 4;
		for (int i = 0; i < 7; i++) {
			this.carte[i][4].setDecouvert(true);
		}
		for (int i = 0; i < 5; i++) {
			this.carte[i][3].setDecouvert(true);
		}
		for (int i = 0; i < 4; i++) {
			this.carte[i][2].setDecouvert(true);
		}

		for (int i = 0; i < 3; i++) {
			this.carte[i][1].setDecouvert(true);
		}
		this.carte[0][0].setDecouvert(true);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.carte[i][j].getDecouvert() != true) {
					this.carte[i][j].setDecouvert(false);
				}
			}
		}
	}

	// initialisation de la partie connue du plan pour la garde de nuit
	// (les bool�ens permettant de dire qu'une case est d�couverte sont � True)
	public void initPlateauGardeNuit() {
		this.position[0] = 6;
		this.position[1] = 0;
		this.villeAdversaire[0] = 0;
		this.villeAdversaire[1] = 4;
		for (int i = 0; i < 7; i++) {
			this.carte[i][0].setDecouvert(true);
		}
		for (int i = 0; i < 7; i++) {
			this.carte[i][1].setDecouvert(true);
		}
		for (int i = 2; i < 7; i++) {
			this.carte[i][2].setDecouvert(true);
		}

		for (int i = 4; i < 7; i++) {
			this.carte[i][3].setDecouvert(true);
		}
		for (int i = 5; i < 7; i++) {
			this.carte[i][4].setDecouvert(true);
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.carte[i][j].getDecouvert() != true) {
					this.carte[i][j].setDecouvert(false);
				}
			}
		}
	}

	// GETTER
	public int[] getPosition() {
		return this.position;
	}

	public Case[][] getCarte() {
		return this.carte;
	}

	// SETTER
	public void setPosition(int[] p) {
		this.position = p;
	}

	public void affichePlateau() {
		ArrayList<String> line = new ArrayList<String>();
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 5; y++) {
				if (this.carte[x][y].getDecouvert()) {
					line.add("x");
					//LCD.drawString("x", y, x);
				} else {
					line.add(".");
					//LCD.drawString(".", y, x);
				}
			}
			System.out.println(line);
			line.clear();
		}
	}

	public void afficheChemin() {
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 5; y++) {
				if (this.carte[x][y].getChemin()) {
					LCD.drawString("x", y, x);
				} else {
					LCD.drawString(".", y, x);
				}
			}
		}
	}
	
	public boolean verifierCouleur(Couleur couleur) {
		LCD.clear();
		LCD.refresh();	
		String couleurCase = this.getCarte()[this.getPosition()[0]][this.getPosition()[1]].getCouleur();
		LCD.drawString("Je vois du "+ couleur.couleurTrouve(), 0, 3);		
		return couleurCase.equalsIgnoreCase(couleur.couleurTrouve());
	}
	
	public void caseDecouverte(){
		if (!this.carte[this.position[0]][this.position[1]].getDecouvert()){
			this.carte[this.position[0]][this.position[1]].setDecouvert(true);
		}
	}

}
