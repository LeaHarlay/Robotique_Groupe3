package environnement;

public class Plan {
	
	private Case[][] carte = new Case[7][5];
	private int[] position = new int[2];
	private int[] adversaire = new int[2];
	
	public Plan() {
		this.init();
	}
	
	public void init() {
		//this.carte = new Case[7][5];
		//camps militaires
		this.carte[0][0] = new Case("Camp militaire","rouge",1);
		this.carte[5][3] = new Case ("Camp militaire", "rouge",1);
		//cases de départ
		this.carte[0][4] = new Case("Case départ","blanc",1);
		this.carte[6][0] = new Case("Case départ","blanc",1);
		//marécages
		for (int i=1;i<4;i++) {
			this.carte[4][i] = new Case("Marécage","orange",5);
		}
		this.carte[2][4] = new Case("Marécage","orange",5);
		//mur
		for (int i=0;i<3;i++) {
			this.carte[i][1] = new Case("Mur","bleu",10);
		}
		for (int i=2;i<4;i++) {
			this.carte[i][2] = new Case("Mur","bleu",10);
		}
		for (int i=5;i<7;i++) {
			this.carte[i][4] = new Case("Mur","bleu",10);
		}
		//prairies
		for (int i=0;i<7;i++) {
			for (int j=0;j<5;j++) {
				if (this.carte[i][j]==null) {
					this.carte[i][j] = new Case ("Prairie","vert",1);
				}
			}
		}
	}
	
	public String toString() {
		String aff="";
		for (int i=0;i<7;i++) {
			for (int j=0;j<5;j++) {
				aff=aff+"  "+this.carte[i][j].getcouleur()+"  \n";
				
			}
			aff=aff+"\n";
		}
		return aff;
	}
}
