package environnement;

/**
 * Gestion des constantes/paramètres du programme
 * @author lea, amelie
 *
 */
public interface Parametre {
	// Bluetooth
	final String ROBOT1 = "00:16:53:43:96:91";	
	final String ROBOT1_NAME = "Sansa2";
	final String ROBOT2 = "00:16:53:43:B3:FB";
	
	// Dimension de la carte
	final int LARGEUR_PLATEAU = 5;
	final int LONGUEUR_PLATEAU = 7;

	// Direction
	final String HAUT = "Haut";
	final String BAS = "Bas";
	final String DROITE = "Droite";
	final String GAUCHE = "Gauche";
	final String AVANCER = "Avancer";
	
	// Orientation du robot sur la carte
	final String NORD = "Nord";
	final String SUD = "Sud";
	final String EST = "Est";
	final String OUEST = "Ouest";

	// Couleur
	final String ROUGE = "Rouge";
	final String VERT = "Vert";
	final String BLEU = "Bleu";
	final String ORANGE = "Orange";
	final String BLANC = "Blanc";
	final int NB_MESURE = 3;
	final int MARGE = 20; // Seuil avec marge de 8% (0 à 255)
}
