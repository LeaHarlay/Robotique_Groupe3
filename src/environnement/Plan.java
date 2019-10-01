package environnement;

public class Plan {
	
	private Case[][] carte = new Case[7][5];
	private int[] position = new int[2];
	private int[] adversaire = new int[2];
	
	public Plan() {
		this.init();
	}
	
	public void init() {
		this.position[0]=0;
		this.position[1]=4;
		this.adversaire[0]=6;
		this.adversaire[1]=0;
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
				if (this.position[0]==i && this.position[1]==j) {
					aff = aff+"   moi   ";
				}else if (this.adversaire[0]==i && this.adversaire[1]==j) {
					aff = aff+"   toi   ";
				}else {
					aff=aff+"  "+this.carte[i][j].getcouleur()+"  ";
				}
				
				
			}
			aff=aff+"\n";
		}
		return aff;
	}
	
	public Plan initPlateauSauvageon() {
		Plan p = new Plan();
		for (int i =0;i<7;i++) {
			p.carte[i][4].setDecouvert(true);
		}
		for (int i=0;i<5;i++) {
			p.carte[i][3].setDecouvert(true);
		}
		for (int i=0;i<4;i++) {
			p.carte[i][2].setDecouvert(true);
		}
		
		for (int i=0;i<3;i++) {
			p.carte[i][1].setDecouvert(true);
		}
		p.carte[0][0].setDecouvert(true);
		for (int i=0;i<7;i++) {
			for (int j=0;j<5;j++) {
				if (p.carte[i][j].getDecouvert()!=true) {
					p.carte[i][j].setDecouvert(false);
				}
			}
		}
		return p;
	}
	
	public Plan initPlateauGardeNuit() {
		Plan p = new Plan();
		
		for (int i =0;i<7;i++) {
			p.carte[i][0].setDecouvert(true);
		}
		for (int i=0;i<7;i++) {
			p.carte[i][1].setDecouvert(true);
		}
		for (int i=2;i<7;i++) {
			p.carte[i][2].setDecouvert(true);
		}
		
		for (int i=4;i<7;i++) {
			p.carte[i][3].setDecouvert(true);
		}
		for (int i=5;i<7;i++) {
			p.carte[i][4].setDecouvert(true);
		}
		for (int i=0;i<7;i++) {
			for (int j=0;j<5;j++) {
				if (p.carte[i][j].getDecouvert()!=true) {
					p.carte[i][j].setDecouvert(false);
				}
			}
		}
		return p;
	}
	
}
