package robots;

import java.util.ArrayList;

import environnement.Noeud;

public class TestIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] caseDepart = new int[2];
		caseDepart[0]=6;
		caseDepart[1]=0;
		ArrayList<Noeud> listeNoeuds = new ArrayList<>(); //Liste des noeuds courants
		Noeud noeudDepart = new Noeud(caseDepart,0);
		ArrayList<Noeud> cheminsFinaux = new ArrayList<>(); //chemins qui mènent au bon endroit
		
		//Initialisation
		listeNoeuds.add(noeudDepart);
		
		//
		while (!listeNoeuds.isEmpty()) {
			
			//on regarde le chemin le plus court parmi tous les noeuds courants
			int valeurNoeud = listeNoeuds.get(0).getCoutTotal();
			for (int i = 1;i<listeNoeuds.size();i++) {
				if (valeurNoeud>listeNoeuds.get(i).getCoutTotal()) {
					//on met à jour la valeur
					//on stocke/met à jour l'indice
				}
				//on développe le noeud avec le plus petit cout (indice trouvé précédemment)
				//on supprime donc le noeud qui nous a permis de développer
				//on vérifie qu'aucun noeud final ne correspond au noeud terminal
				//(si on en trouve un on peut le mettre dans la liste prévue à cet
				//effet et le supprimer de cette liste)
				//on vérifie si deux noeuds (la dernière case de la liste correspond) sont identiques
				//afin de ne garder que celui avec la plus petit cout
			}
		}
		
		
		
		
	}

}
