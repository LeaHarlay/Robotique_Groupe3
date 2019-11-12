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
		ArrayList<Noeud> cheminsFinaux = new ArrayList<>(); //chemins qui m�nent au bon endroit
		
		//Initialisation
		listeNoeuds.add(noeudDepart);
		
		//
		while (!listeNoeuds.isEmpty()) {
			
			//on regarde le chemin le plus court parmi tous les noeuds courants
			int valeurNoeud = listeNoeuds.get(0).getCoutTotal();
			for (int i = 1;i<listeNoeuds.size();i++) {
				if (valeurNoeud>listeNoeuds.get(i).getCoutTotal()) {
					//on met � jour la valeur
					//on stocke/met � jour l'indice
				}
				//on d�veloppe le noeud avec le plus petit cout (indice trouv� pr�c�demment)
				//on supprime donc le noeud qui nous a permis de d�velopper
				//on v�rifie qu'aucun noeud final ne correspond au noeud terminal
				//(si on en trouve un on peut le mettre dans la liste pr�vue � cet
				//effet et le supprimer de cette liste)
				//on v�rifie si deux noeuds (la derni�re case de la liste correspond) sont identiques
				//afin de ne garder que celui avec la plus petit cout
			}
		}
		
		
		
		
	}

}
