import java.awt.Color;

public class Map {
	double lower = 29;
	double higher = 24;
	double random;
	double x=0, y=0;
	double xpoints[]= new double[952];			// Tableau de point pour le remplissage en Polygones
	double ypoints[]= new double[952];
	 
	double vent;
	 public Map (){
		 
	 }
	
	
	public  void Init(){

		vent = Math.random()*30;
		random = ((Math.random() * (higher-lower)) + lower);
		// Couleur terrain.
		StdDraw.setPenColor(new Color(206,61,0));
		int i=0;									// Indice tableau
		for(x=0; x<951; x++) { 			
			// Equation courbe terrain
			y=(Math.cos(x/51)+Math.cos(x/random)+Math.cos(x*x/56265))*50+200;			
				// Implémentation du tableau de points
			xpoints[i]=x;
			ypoints[i]=y;
			i++;
		}		
		
		// Rectification derniers points. 
		xpoints[0]=-50;
		xpoints[1]=-50;	 	// Départ de la courbe colé au bord
		ypoints[0]=-30;  		// Tracage par dessous
		
		xpoints[951]=950;		// FIn de la coubre colé au bord
		ypoints[951]=-50;
		
			// Remplissage dessous la courbe
		StdDraw.filledPolygon(xpoints, ypoints);	
	}
	
	public void traceTerrain (){
		
		StdDraw.setPenColor(new Color(206,61,0));
		StdDraw.filledPolygon(xpoints, ypoints);
	}
	
	
	public double getYofX(double px){
		double y;
		y=ypoints[(int)px];
		//y=(Math.cos(px/51)+Math.cos(px/random)+Math.cos(px*px/56265))*50+200;
		return y;
	}
	
	public void setYofX(int px, Missile missile){
		int i=px;
		/*double r=missile.getTaille();
		double a=missile.getX();
		double b=missile.getY();
		double ycercle=-(r*r-(i-a)*(i-a)-b)/(ypoints[i]-2*b);
				*/
		if (missile.getDammage()==200){//Missile atomique grosse cassure terrain

			int  cercle=(int)missile.getTaille()+100;
			for (i=px; i<px+missile.getTaille()+100;i++){
				ypoints[i]=ypoints[i]-cercle;
				cercle--;
			}
			// on test que i ne soit pas négatif : quand px est très petit c'est possible.
			
			if(px-(int)missile.getTaille()-100>0){
				for (i=px-(int)missile.getTaille()-100;  i<px;i++){
					ypoints[i]=ypoints[i]-cercle;
					cercle++;
				}
			}
		}

		else {

			double cercle=missile.getTaille()+10;
			for (i=px; i<px+missile.getTaille()+10;i++){
				//ycercle=-(r*r-(i-a)*(i-a)-b)/(ypoints[i]-2*b);
				ypoints[i]=ypoints[i]-cercle;
				cercle--;
			}
			//-(int)missile.getTaille()-10
			
			// on test que i ne soit pas négatif : quand px est très petit c'est possible.
			if(px-(int)missile.getTaille()-10>0){
				for (i=px-(int)missile.getTaille()-10;  i<px;i++){
					ypoints[i]=ypoints[i]-cercle;
					cercle++;
				}
			}
				
		}
		
		
		
		
	}
	
	// Fin afficher le terrain 	
	
	public double getX(){
		return x;		
	}
	public double getY(){
		return y;
	}
	public double getVent(){
		return vent;
	}
	public void setVent(double pvent){
		vent=pvent;
	}
	
	public void setX(double px){
		x=px;		
	}
	public void setY(double py){
		y=py;		
	}
	
	
	
	
}// Fin Class Map




//y1=((Math.cos(x1)+Math.cos(2*x1)+Math.cos(3*x1)+Math.cos(4*x1)+Math.cos(5*x1))+2)/11;
//y1=(1+Math.sin(x1/5)/(x1/8)+Math.cos(x1/5)/9)/5; 
//y1=(Math.cos((x1/15)+2)+Math.cos((x1/30)+3)+Math.cos(x1/45))*100+150;
//y1=Math.cos(x1)+Math.cos(2x1)+Math.cos(3x1)+Math.cos(x1)+Math.cos(x1);
//y1=(Math.sin(5*x1-4)+Math.cos(x1)+Math.cos(2*x1)+Math.cos(3*x1)+2)/10;
//sin(5*x-4)/(x-4)+cos(x)
//sin(5*x-4)+cos(x)+cos(2*x)+cos(3*x)+2

//StdDraw.line(x0,y0,x1,y1); //Lignes du terrain line(double x0, double y0, double x1, double y1) 
		

//y1=(Math.cos((x1/(60-random))/2+(Math.sin(x1/1000))+Math.cos((x1/random)+2))*(180-random)+200);
//y1=(Math.cos((x1/35)+6)+Math.cos((x1/random)+7)+Math.cos((x1/(2*random))+random))*60+200;				//Equation du bizu
//(122, 248, 29)
		//(240,104,4) Rouge/orange
		//(172, 247, 34) Vert kaki
		//(6, 208, 57) Vert foncé/bleu
		