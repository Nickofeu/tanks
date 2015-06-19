
public class Tank {
	String 	nomTank;	// Stock le nom du Tank
	double		dammage;	// Stock les dammage
	double		pv;			// Stock les points de vies
	double essence;
	double munition;
	double munitionAtomique;
	double x;
	double y;
	double anglecannon;
	double taille;
	double angletank;
	
	
	// Construteur par default de Tank
	public Tank(String pNom, double pDammage, double pPv, double pMunition, double px, double py){
			nomTank =pNom;
			dammage= pDammage;
			pv = pPv;
			munition=pMunition;
			x=px;
			y=py;	
			anglecannon=0;
			essence = 200;
			taille=38; // représente la moitié de la taille du tank (COllision 2 cercles ) s
			angletank=0;
			munitionAtomique=1;
		}

	
	
	// ******** ACCESSEURS *********
	
	//Retourne le nom d'un tank
	public String getNom(){
		return nomTank;
	}
	// Retourne le nombre de dommage 
	public double getDammage(){
		return dammage;
	}
	// Retourne le nombre de pv
	public double getPv(){
		return pv;
	}
	public double getCannon(){
		return anglecannon;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getEssence(){
		return essence;
	}
	public double getTaille(){
		return taille;
	}
	
	public double getMunition(){
		return munition;
	}
	public double getAngleTank(){
		return angletank;
	}
	public double getMunitionAtomique(){
		return munitionAtomique;
	}
	// ********* MUTATEUR ************
	
	// Definit le nom du tank
	public void setNom(String pNom){
		nomTank=pNom;
	}
	// Definit le nombre de dommage
	public void setDammage(double pDammage){
		dammage=pDammage;
	}
	public void setPv(double pPv){
		pv=pPv;
	}
	public void setCannon(double pangle){
		anglecannon=pangle;
	}

	public void setX(double px){
		x=px;
	}
	public void setY(double py){
		y=py;
	}
	public void setEssence(double pEssence){
		essence=pEssence;
	}
	public void setTaille(double pTaille){
		taille=pTaille;
	}
	public void setMunition(double pMunition){
		munition=pMunition;
	}
	public void setAngleTank(double pAngleTank){
		angletank=pAngleTank;
	}
	public void setMunitionAtomique(double pMunitionAtomique){
		munitionAtomique=pMunitionAtomique;
	}
	
	
	// Fonction qui retourne l'angle du tank au position x,y en degré 
	public void angleTank(Map map){
		double yP=map.getYofX(x+10);
		double yM=map.getYofX(x-10);
		
		double tanA=(yP-yM)/20;
		angletank=Math.toDegrees(Math.atan(tanA));
	}
	

	public void Affiche(Map map){
		this.setY(map.getYofX(this.getX())+30);
		StdDraw.picture( this.x,this.y+10,"TankVertCANNON.png",154/2,174/2,anglecannon); // Taille original :: 755*700
		
	
		StdDraw.picture(this.x,this.y,"TankVertTANK.png",154/2,108/2,this.angletank); // Taille original :: 755*700

		
		}
	public void initGame(Map map){
		// Remise a zéro de tout
		this.setEssence(200);
		this.setMunition(2);
		this.setMunitionAtomique(1);
		this.setPv(400);
		if (this.getNom()=="Tank1"){
			this.setX(200);
			
		}else if (this.getNom()=="Tank2"){
			this.setX(750);	
			
		}
	}
	public void initGameWinner(Map map){
		// Remise a zéro de tout
		this.setEssence(200);
		this.setMunition(3);
		this.setMunitionAtomique(1);
		this.setPv(500);
		if (this.getNom()=="Tank1"){
			this.setX(200);
			
		}else if (this.getNom()=="Tank2"){
			this.setX(750);	
			
		}
	}

	


}