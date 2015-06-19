
public class Missile {
	String nom;
	double x;
	double y;
	double force;	// Puissance de lancé du missile 
	double dammage;	// Dommage propre au type de missile
	double taille;	// Taille propre au type de missile
	double munition;	// nbr munitions
	int i;
	double ypreced;
	double vo;
	
	public Missile(double pdammage, double ptaille, double pmunition, String pnom){
		x=10;
		y=10;
		force=0;
		i=0;
		dammage=pdammage;
		taille=ptaille;
		munition=pmunition;
		nom=pnom;
		ypreced=0;
	}
	
	public void lancerMissile(Tank tank, Missile missileJeu, Map map){
		if (tank.getNom()=="Tank1"){

			vo=this.getForce()-map.getVent()/2;
		}else if (tank.getNom()=="Tank2"){
			vo=this.getForce()+map.getVent()/2;				
		}
		
		double hausse=tank.getCannon()+84;
		double sinA=Math.sin(Math.toRadians(hausse));
		double cosA=Math.cos(Math.toRadians(hausse));
		y=(-9.81/2)*this.i*this.i+vo*sinA*this.i+tank.getY()+10;// décalage bout du cannon : Ytank+38
		x=vo*cosA*this.i + tank.getX();					
		this.i+=1;
		
		// On dessine le cercle aux coordonnés du missile 
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(this.x,this.y, this.taille);	
		StdDraw.show(5);
	}//  Fin lancerMissile  
	
	
	// Lancé missile atomique
	public void lancerMissileAtomique(Tank tank, Missile missileJeu,Map map){
		double vo=this.getForce()-map.getVent();
		double hausse=tank.getCannon()+90;
		double sinA=Math.sin(Math.toRadians(hausse));
		double cosA=Math.cos(Math.toRadians(hausse));
		y=(-9.81/2)*this.i*this.i+vo*sinA*this.i+tank.getY()+10;// décalage bout du cannon : Ytank+38
		x=vo*cosA*this.i + tank.getX();					
		this.i+=1;
		
		// On dessine le cercle aux coordonnés du missile 
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(this.x,this.y, "logonuc.png",50,50);	
		StdDraw.show(5);
	}//  Fin lancerMissile  
	
	
	public void lancerMissileVertical(Tank tank, Missile missileJeu, Map map){
		double vo=this.getForce()-map.getVent();
		double hausse=tank.getCannon()+90;
		double sinA=Math.sin(Math.toRadians(hausse));
		double cosA=Math.cos(Math.toRadians(hausse));	
		
		if (ypreced>y){ 	// Correspond au cas ou le missile commence à redescendre
			y-=10;			// mouvement vertical
		}
		else {				// Missile sur la monté : Parabolique 
			ypreced=y;
			y=(-9.81/2)*this.i*this.i+vo*sinA*this.i+tank.getY();
			x=vo*cosA*this.i + tank.getX();			
		}
		this.i+=1;
		// On dessine le cercle aux coordonnés du missile 
		StdDraw.picture(this.x, this.y, "Missile_Bill350.png",70,70);
		StdDraw.show(5);
	}//  Fin lancerMissile_Vertical  
	
	
	
	// Getter 
	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}
	public double getDammage(){
		return dammage;
	}
	public double getTaille(){
		return taille;
	}
	public int geti(){
		return i;
	}
	public double getMunition(){
		return munition;
	}
	public String getNom(){
		return nom;
	}
	public double getYpreced(){
		return ypreced;
	}
	
	
	//// SETTERS
	public void setX(double px){
		x=px;
	}
	public void setY(double py){
		y=py;
	}
	public double getForce(){
		return force;
	}
	public void setForce(double pforce){
		force=pforce;
	}
	public void setDammage(double pDammage){
		dammage=pDammage;
	}
	public void setTaille(double pTaille){
		taille=pTaille;
	}
	public void seti(int pi){
		i=pi;
	}
	public void setMunition(double pmunition){
		munition=pmunition;
	}
	public void setNom(String pnom){
		nom=pnom;
	}
	public void setYpreced(double pypreced){
		ypreced=pypreced;
	}
}
	
	
	
