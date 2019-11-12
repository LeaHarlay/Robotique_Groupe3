package environnement;

import java.util.ArrayList;

public class Action implements Parametre, Cloneable {
	private Plan plan;
	private int valeur;
	private ArrayList<String> deplacementDepart = new ArrayList<String>() ; // Listes des déplacements pour arriver a ce plateau
	
	public Action(Plan p){
		this.setPlan(p);
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	public ArrayList<String> deplacementsPossibles(){		
		// Creation d'une liste pour l'ensemble des deplacements possibles
        ArrayList<String> lesDeplacementsPossibles = new ArrayList<String>();
        
        // Ajout de tous les deplacements possibles
        lesDeplacementsPossibles.add(GAUCHE); 
        lesDeplacementsPossibles.add(DROITE); 
        lesDeplacementsPossibles.add(BAS); 
        lesDeplacementsPossibles.add(HAUT); 
        // Affinage des deplacements possible en fonction de la localisation du robot
        
        // Si il se trouve sur le bord superieur
        if (this.plan.getPosition()[0] == 0 || (!this.deplacementDepart.isEmpty() &&  this.getDeplacementDepart().get(this.getDeplacementDepart().size()-1) == HAUT)) {
            lesDeplacementsPossibles.remove(HAUT);
        }
        // Si il se trouve sur le bord inferieur
        if (this.plan.getPosition()[0] == LONGUEUR-1 || (!this.deplacementDepart.isEmpty() &&  this.getDeplacementDepart().get(this.getDeplacementDepart().size()-1) == BAS)) {
            lesDeplacementsPossibles.remove(BAS);
        }
        // Si il se trouve sur le bord gauche
        if (this.plan.getPosition()[1] == 0 || (!this.deplacementDepart.isEmpty() &&  this.getDeplacementDepart().get(this.getDeplacementDepart().size()-1) == GAUCHE)) {
            lesDeplacementsPossibles.remove(GAUCHE);
        }
        // Si il se trouve sur le bord droit
        if (this.plan.getPosition()[1] == LARGEUR - 1 || (!this.deplacementDepart.isEmpty() &&  this.getDeplacementDepart().get(this.getDeplacementDepart().size()-1) == DROITE)) {
            lesDeplacementsPossibles.remove(DROITE);
        }
        return lesDeplacementsPossibles; // On renvoie les deplacement possible
	}

	public Action simuler(String deplacement) {
		Action a = (Action) this.clone();
		int[] newPosition = new int[2];
		newPosition[0] = a.getPlan().getPosition()[0];
		newPosition[1] = a.getPlan().getPosition()[1];
		 // Si il est possible de bouger la case
        switch (deplacement) {
            case HAUT:
            	newPosition[0] = newPosition[0] - 1;
                break;
            case BAS:
            	newPosition[0] = newPosition[0] + 1;
                break;
            case GAUCHE:
            	newPosition[1] = newPosition[1] - 1;
                break;
            case DROITE:
            	newPosition[1] = newPosition[1] + 1;
                break;
            default:
                break;
        }
        a.getPlan().setPosition(newPosition);
    	
    	// La position actuelle du robot est une partie du chemin
        a.getPlan().getCarte()[a.getPlan().getPosition()[0]][a.getPlan().getPosition()[1]].setChemin(true);
        
        // Ajout du déplacement
        a.setDeplacementDepart(deplacement);
        return a;
	}

	public ArrayList<String> getDeplacementDepart() {
		return deplacementDepart;
	}

	public void setDeplacementDepart(String deplacement) {
		this.deplacementDepart.add(deplacement);
	}
	
	public Object clone() {
		Action a = null;
		try {
			a = (Action) super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}
		return a;
	}

}
