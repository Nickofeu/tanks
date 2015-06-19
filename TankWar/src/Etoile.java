
public class Etoile {
	double xEtoile[]=new double[50];
	double yEtoile[]=new double[50];

	public Etoile() {			
		xEtoile[0]=0;
		yEtoile[0]=400;
	}
	
	public  void Init(){

	// Init Tableau position X & Y petites étoiles
	int i;
	for(i=0; i<=49; i++){
		yEtoile[i]=Math.random()*(600-300)+250;
	}
	
	for(i=0; i<=48; i++){		
		xEtoile[i+1]=xEtoile[i]+18;				
	}
	
	}	
	
	public  void trace() {
	int i=0;
	for (i=0; i<=49; i+=2){
				StdDraw.picture(xEtoile[i],yEtoile[i],"etoile100.png");					
			}	
	for (i=1; i<=49; i+=8){
				StdDraw.picture(xEtoile[i],yEtoile[i],"etoile200.png");					
			}  
	
	/*for (i=1; i<=49; i+=8){
				StdDraw.picture(xEtoile[i],yEtoile[i],"etoile200.png");					
			}*/
			int x=800, y=500;
			StdDraw.picture(x,y,"etoile.png");
			StdDraw.picture(250,y,"world.png",60,55);
			StdDraw.picture(300, y, "lune.png", 30, 30);
			StdDraw.picture(450,y-150,"saturn.png",100,100);
			StdDraw.picture(650,y-40,"neptune.png",50,50);
			StdDraw.picture(100,y-100,"Soleil.png",150,150);
			
			
			
	}
	
}// Fin de Class
