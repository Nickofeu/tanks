import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
public class Fenetre{
	double xScale;
	double yScale;
	double lCadre=20;
	double yCentre=610;
	double xCentre=450;
	
		// Déclaration de note constructeur
		public Fenetre(double pxScale, double pyScale){		
			xScale=pxScale;
			yScale=pyScale;
		}
		public double getxScale(){
			return xScale;
		}
		public double getyScale(){
			return yScale;
		}
		public void setxScale(double pxScale){
			xScale=pxScale;
		}
		public void setyScale(double pyScale){
			yScale=pyScale;
		}
		
		// Fonction d'initialisation de notre fenêtre
		public  void Init(){
			int x= (int)this.xScale;
			int y=(int)this.yScale;
			// Initialise la taille de la fenetre
			StdDraw.setCanvasSize(x, y);
			StdDraw.setXscale(0, this.xScale);
			StdDraw.setYscale(0, this.yScale);
			
		}

		// Paint Ciel
		public  void Fond(){
			double x=450;
			double y=300;
			String img="cielouf900600.jpg";
			StdDraw.picture(x,y,img);
				
		}
		
		// Affichage bandeau Point de vie / Joueur / Essence  
		public void cadre(Tank tank1, Tank tank2, Missile missileVertical, Map map){
			// Cadre 
		    StdDraw.setPenColor(new Color (255,238,149));		// 
			StdDraw.filledRectangle(xCentre,yCentre, 492, lCadre); // Fond du Cadre ( x centre, y centre, Half Width, Half Height)
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.rectangle(xCentre, yCentre, 492, lCadre	); // Contour du Cadre
			StdDraw.rectangle(xCentre, yCentre, 150, lCadre	); // Contour  Essence
			
			StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));
			//StdDraw.textLeft(0, yCentre, "Joueur 1 : ");
			
			// Pv et munitions Joueur 1
			StdDraw.textLeft(0, yCentre, "Joueur 1 PV: "+ Double.toString(tank1.getPv()));	//PV
			StdDraw.picture(170, yCentre+4, "Missile_Bill350.png",50,50); // MISSILE VERICAL 1,12 = coeff image; 313*350.
			StdDraw.textLeft(185, yCentre, Double.toString(Math.round(tank1.getMunition())));	// MUNITIONS
			StdDraw.picture(230, yCentre,"logonuc.png",40,40);//MISSILE ATOMIQUE
			StdDraw.textLeft(250, yCentre, Double.toString(Math.round(tank1.getMunitionAtomique())));	// MUNITIONS
			StdDraw.filledCircle(285, yCentre, 5);
			// coordonné de l'image 
			/* centre : 825 , 610
			 * *****
			 * 		*
			 * 		*
			 * 		*
			 * *****
			 * 
			 */


		    // PV et munitions Joueur 2		    
		    StdDraw.textLeft(650, yCentre, "Joueur 2 PV: "+ Double.toString(tank2.getPv()));
			StdDraw.setFont(new Font("Arial", Font.PLAIN, 16));			
			StdDraw.picture(820, yCentre+4, "Missile_Bill350.png",50,50); //  1..12 : coeff image, 313*350.
			// coordonné de l'image centre : 825 , 610
			StdDraw.textLeft(835, yCentre, Double.toString(Math.round(tank2.getMunition())));
			StdDraw.picture(885, yCentre,"logonuc.png",40,40);//MISSILE ATOMIQUE
			StdDraw.textLeft(905, yCentre, Double.toString(Math.round(tank2.getMunitionAtomique())));	// MUNITIONS
			StdDraw.filledCircle(935 , yCentre, 5);


			StdDraw.picture(850, 561, "fleche2.png", 110, 50,180);
			StdDraw.setFont(new Font("Arial", Font.BOLD, 16));	
		    StdDraw.text(850,560, Double.toString(Math.round(map.getVent()/2))+"km/h");
			

			double yPuissance=570;
			StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));	
		// Affichage puissance dans cadre au milieu
		    StdDraw.setPenColor(new Color(251,236,154));	
			StdDraw.filledRectangle(xCentre, yPuissance, 150, lCadre);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.rectangle(xCentre, yPuissance, 150, lCadre);
			StdDraw.text(xCentre,yPuissance,"Puissance :  %");
		
		
		}
		// Fonction affichage de l'essence du joueur
		public void affEssence(Tank tankJ){
			StdDraw.setPenColor(StdDraw.BLACK);
		    StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));
		    StdDraw.text(450,608,"Essence: "+Double.toString(tankJ.getEssence()));
		}
		
		public void affOK(){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.rectangle(550, yCentre, 15, 15);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(550, yCentre, 15, 15);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(550, yCentre, "OK");    
		}
		
		public void affVent(){
		    StdDraw.text(700,608,"Vent : km/h");
		}
		
		
		
		// fonction affichage puissance de tir
		public void affPuissance (double force){
			double xPuissance=450;
			double yPuissance=570;

		    StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));

			StdDraw.show(10);
		    StdDraw.setPenColor(new Color(251,236,154));	
			StdDraw.filledRectangle(xPuissance, yPuissance, 150, lCadre);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.rectangle(xPuissance, yPuissance, 150, lCadre);
			StdDraw.text(xPuissance,yPuissance,"Puissance :"+Double.toString(force)+"%");
			
		}
		
		public void encadreMissile (Tank tankJ){
			if (tankJ.getNom()=="Tank2"){
				if (tankJ.getMunition()>0){
					StdDraw.rectangle(820, yCentre+4, 15, 24);
					StdDraw.show(100);					
				}		
			} else if(tankJ.getNom()=="Tank1"){
				if (tankJ.getMunition()>0){
					StdDraw.rectangle(170, yCentre+4, 15, 24);					
					StdDraw.show(100);
				}
			}else {
				}
		}
		// Encadre missile atomique 
		public void encadreMissileAtomique (Tank tankJ){
			if (tankJ.getNom()=="Tank2"){
				if (tankJ.getMunition()>0){
					StdDraw.circle(885, yCentre, 21);
					StdDraw.show(100);					
				}		
			} else if(tankJ.getNom()=="Tank1"){
				if (tankJ.getMunition()>0){
					StdDraw.circle(230, yCentre, 21);				
					StdDraw.show(100);
				}
			}else {
				}
		}

		// Encadre missile Normal 
		public void encadreMissileNormal (Tank tankJ){
			if (tankJ.getNom()=="Tank2"){
				if (tankJ.getMunition()>0){
					StdDraw.circle(935, yCentre, 8);
					StdDraw.show(100);					
				}		
			} else if(tankJ.getNom()=="Tank1"){
				if (tankJ.getMunition()>0){
					StdDraw.circle(285, yCentre, 8);				
					StdDraw.show(100);
				}
			}else {
				}
		}
		public void endGame(Tank tankJ, Tank tankE){
			if (tankE.getPv()<=0){		
			}
		}
		
		// Fonction affichage tour du joueur ! 
		public void affichageTourJoueur(boolean tourJoueur){
			// Affichage tour du joueur
			StdDraw.setPenColor(StdDraw.RED);
						if (tourJoueur==true){ // Tour du joueur 1
							StdDraw.filledCircle(-20, yCentre, 7);
						} 
						else if (tourJoueur==false){// tour du joueur 2	
							StdDraw.filledCircle(630, yCentre, 7);
						}else {}
		}
		
		public void findugame(Tank tankE, Tank tankJ, mouse mouse,Map map, Etoile etoile){
			// Fonction qui initialise si c'est la fin du jeu 
			// test fin du jeu : 
			if (tankE.getPv()<=0){
				while(tankE.getPv()<=0){
					StdDraw.clear();
					Fond();					// Ciel			
					//cadre(tank1, tank2, missileVertical,map);	// Cadre affichage Joueur / munitions 
					etoile.trace();					// Grosse étoile fixe
					map.traceTerrain();					// Map
					
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.setFont(new Font("Arial", Font.ITALIC, 25));
					StdDraw.text(xCentre,500,tankJ.getNom()+" a Gagné !!! ");

					StdDraw.setFont(new Font("Arial", Font.ITALIC, 17));
					StdDraw.text(xCentre, 450,"Félicitation tu passe au Niveau 2 ! ");
					StdDraw.text(xCentre, 430,"Tu as maintenant 100 points de vie de plus et  ");
					StdDraw.text(xCentre, 410,"un missile Vertical de plus ! ");
			
					//StdDraw.text(xCentre, 350,"Voulez vous rejouer ? ");
			   // Création du bouton rejouer 
					StdDraw.rectangle(xCentre, 300, 60, 15);				
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledRectangle(xCentre, 300, 60, 20);
					StdDraw.setFont(new Font("Arial", Font.PLAIN, 20));	
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(xCentre, 300, "REJOUER"); 
				// Test bouton rejouer appuyer 
					mouse.clickNumber();
					StdDraw.show(1);
					
					if(mouse.getClick()==4){
					    // on réinitialise le game
						map.Init();	
					    tankE.initGame(map);
					    tankJ.initGameWinner(map);
					   
					}
				}
			}else{
				
			}
		}// fin du game 
		
		public void explication(){
			while(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)==false){// tant que je n'appui pas sur entrée 
				StdDraw.picture(450, 300, "doc.jpg", 1000, 700);
				StdDraw.show(1);
			}
		}
}



/* 	// Affichage bandeau Point de vie / Joueur / Essence / Force missile 
public void cadre(double pEssence, double pPuissance, double pPv){
double lCadre=15;
double yCentre=613;
double xCentre=450;

StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));

StdDraw.setPenColor(StdDraw.WHITE);
StdDraw.filledRectangle(xCentre,yCentre, 492, lCadre); // Cadre ( x centre, y centre, Half Width, Half Height)
StdDraw.setPenColor(StdDraw.BLACK);
StdDraw.rectangle(xCentre, yCentre, 492, lCadre	); // Contour du cadre
StdDraw.rectangle(xCentre, yCentre, 150, lCadre	); // Contour Puissance / Essence
//StdDraw.line(450,588,450,628);
StdDraw.textLeft(0, yCentre, "Joueur 1 : ");
StdDraw.setFont(new Font("Arial", Font.BOLD, 16));
StdDraw.textLeft(110, yCentre, "PV: "+ Double.toString(pPv) +"  M1:  "+"  M2:  ");

StdDraw.setFont(new Font("Arial", Font.ITALIC, 16));

StdDraw.text(450,608,"Essence: "+Double.toString(pEssence));

double yPuissance=583;


StdDraw.setPenColor(StdDraw.WHITE);
StdDraw.filledRectangle(xCentre, yPuissance, 150, 15);
StdDraw.setPenColor(StdDraw.BLACK);
StdDraw.rectangle(xCentre, yPuissance, 150, 15);
StdDraw.text(xCentre,yPuissance,"Puissance :  %");




StdDraw.textLeft(650, yCentre, "Joueur 2 : ");
//StdDraw.show(10);
}
*/