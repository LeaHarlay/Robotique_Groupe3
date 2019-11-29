package environnement;

public interface Parametre {
	// Plateau
	final int LARGEUR_PLATEAU = 5;
	final int LONGUEUR_PLATEAU = 7;

	// Direction
	final String HAUT = "Haut";
	final String BAS = "Bas";
	final String DROITE = "Droite";
	final String GAUCHE = "Gauche";
	final String AVANCER = "Avancer";
	
	// Orientation
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
	
	public final int NB_MESURE = 3;
	public final int MARGE = 20; // 8% de 255 (les couleurs r,v,b vont de 0 �
									// 255)
}
