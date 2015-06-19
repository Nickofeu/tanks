import java.awt.Font;
import java.awt.event.KeyEvent;


public class Clavier {
double lancerMissile;
boolean tourJoueur;
boolean collision;
boolean canShoot;


	public Clavier(){
		 lancerMissile=0;
		 tourJoueur=true;
		 collision=false;
		 canShoot=true;
	}
	
	public void settourJoueur(boolean ptourJoueur){
		tourJoueur=ptourJoueur;
	}
	public  boolean gettourJoueur(){
		return tourJoueur;
	}
	
	
	// Acquisition Mouvement Tank Droite / Gauche	
	public void moveTank(Tank tankJ, Map map){
		if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)&&tankJ.getX()>15){	
			canShoot=false;
			tankJ.setX(tankJ.getX()-2);
			tankJ.setY(map.getYofX(tankJ.getX())+30);
			tankJ.setEssence(tankJ.getEssence()-1);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)&&tankJ.getX()<900){
			canShoot=false;
			System.out.println(tankJ.getX());
			tankJ.setX(tankJ.getX()+2);
			tankJ.setY(map.getYofX(tankJ.getX())+30);
			tankJ.setEssence(tankJ.getEssence()-1);
		}else {
		}
	}
	
	// Fonction verif essence
	public void verifEssence(Tank tankJ){
		if (tankJ.getEssence()<0){
			tourJoueur=!tourJoueur;
			StdDraw.setFont(new Font("Arial", Font.BOLD, 20));	
			StdDraw.text(450, 400, "Changement");
			StdDraw.show(1000);
		}
	}


	// Acquisition Canon Haut Bas
	public void moveCannon(Tank tankJ){
		if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
			tankJ.setCannon(tankJ.getCannon()+1);
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
			tankJ.setCannon(tankJ.getCannon()-1);
		} else {	
		}			
	}

	// Acquistion lancé+force missile & détection collision
	// Paramètre : fenetre, missile1, tankE, double lancerMissile, double tourJoueur 
	// moveMissile(fenetre, missile1, tankJ, tankE, lancerMissile, tourJoeur);
	public void moveMissile(Fenetre fenetre, Missile missile, Tank tankJ, Tank tankE, Map map ){
		
		// Paramètre : fenetre, missile1, tankE, lancerMissile, tourJoueur 
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){				
				double force = forcelancé(fenetre);		// Acquistion force de lancé( block dedans tant que Space pressed)
				missile.setForce(force);			
				fenetre.affPuissance(force);	// Fction affiche puissance
				lancerMissile=1;
			
				System.out.println("lancé");
		} else if (lancerMissile==1){
			// Lancer du missile
			if (missile.getDammage() == 100){ // Correspond au Missile Vertical
				if(tankJ.getMunition()>0){
					missile.lancerMissileVertical(tankJ,missile,map);	
				}/*else{
					missile.lancerMissile(tankJ,missile);					
				}*/
			} 
			else if (missile.getDammage() == 200){ // Correspond au Missile Atomique
				if(tankJ.getMunitionAtomique()>0){
					missile.lancerMissileAtomique(tankJ,missile,map);	
					
				}/*else{
					missile.lancerMissile(tankJ,missile);					
				}*/
			}
			else if(missile.getDammage() == 50) {// Correspond au Missile Normal 
				missile.lancerMissile(tankJ,missile,map);
			}
			//------------------------------

			testCollision(fenetre, missile, tankJ, tankE, map);
		}
		/*else{
			missile.setYpreced(0);
		}*/
	}
	
	public void testCollision(Fenetre fenetre, Missile missile, Tank tankJ, Tank tankE, Map map ){
		// test collision missile/tank
					if(collision(tankE,missile)){
						tankE.setPv(tankE.getPv()-missile.getDammage());	
						lancerMissile=0;
						tourJoueur=!tourJoueur;
						
						if (missile.getDammage() == 100){ // Correspond au Missile Vertical
							if(tankJ.getMunition()>0){
								tankJ.setMunition(tankJ.getMunition()-1);								
							}
						}
						if (missile.getDammage() == 200){ // Correspond au Missile Vertical
							if(tankJ.getMunitionAtomique()>0){
								tankJ.setMunitionAtomique(tankJ.getMunitionAtomique()-1);								
							}
						}	
						StdAudio.play("explosion7.wav");	// Son exlosion
						if ((int)missile.getDammage()==200){
							StdDraw.picture(missile.getX(), missile.getY()+30, "explosionAtomique.png",150,150);
						}else{

							StdDraw.picture(missile.getX(), missile.getY(), "explosionNormal.png",60,60);
						}
						missile.setYpreced(0);
						//	missile.setX(tankJ.getX());
							missile.setY(tankE.getY());
							missile.seti(0);
							StdDraw.show(400);	
							
						// Collisin Missile fenetre 
					}else if(collisionFenetre(missile,fenetre)==true){					
							if (missile.getDammage() == 100){ // Correspond au Missile Vertical
								if(tankJ.getMunition()>0){
									tankJ.setMunition(tankJ.getMunition()-1);		
								}
							}
							if (missile.getDammage() == 200){ // Correspond au Missile Vertical
								if(tankJ.getMunitionAtomique()>0){
									tankJ.setMunitionAtomique(tankJ.getMunitionAtomique()-1);								
								}
							}	
							
							lancerMissile=0;
							tourJoueur=!tourJoueur;
							missile.seti(0);				
							missile.setYpreced(0);
							missile.setY(tankE.getY());
							
							
					}// COllision missile /mAP 
					else if (collisionMap(missile,map)){
						if (missile.getDammage() == 100){ // Correspond au Missile Vertical
							if(tankJ.getMunition()>0){
								tankJ.setMunition(tankJ.getMunition()-1);		
							}
						}
						if (missile.getDammage() == 200){ // Correspond au Missile Vertical
							if(tankJ.getMunitionAtomique()>0){
								tankJ.setMunitionAtomique(tankJ.getMunitionAtomique()-1);								
							}
						}	
						// destruction terain
						System.out.println(missile.getX());
						if(missile.getX()>=0){
							
							map.setYofX((int)missile.getX(), missile);
						}lancerMissile=0;
						tourJoueur=!tourJoueur;
						//StdAudio.play("Explosion7.wav");
						
						if ((int)missile.getDammage()==200){
							StdDraw.picture(missile.getX(), missile.getY()+30, "explosionAtomique.png",150,150);
						}else{

							StdDraw.picture(missile.getX(), missile.getY(), "explosionNormal.png",60,60);
						}
					
						//	System.out.println(missile.getYpreced());
						missile.seti(0);										
						missile.setYpreced(0);
						missile.setY(tankE.getY());
						StdDraw.show(400);	
					}			
					
						
	}

		
	
	
	
	//fonction collision missile/tank
	public  boolean collision (Tank tank, Missile missile){
		double dist_x=tank.getX()-missile.getX();
		double dist_y=tank.getY()-missile.getY();
		
		// Collision entre Missile et tank 
		if(Math.sqrt(dist_x*dist_x+dist_y*dist_y)< missile.getTaille()+tank.getTaille()){	
			collision=true;
			return true;
		}else{
			//collision=false;
			return false;
		}
	}
	// fonction collision missile.fenetre
	public boolean collisionFenetre (Missile missile, Fenetre fenetre){
		if ( missile.getX()<0 || missile.getX()>fenetre.getxScale() || missile.getY()<0){// || missile.getY()>fenetre.getyScale()
			collision=true;
			return true;
		}	else{
			//collision=false;
			return false;
		}
	}
	
	// FOnction collision missile. map
	public boolean collisionMap (Missile missile, Map map){
		
		//map.getYofX(missile.getX()); // L'ordonné de la map au point de meme abscisse que le missile 
		double dist_y=missile.getY()-map.getYofX(missile.getX());	
		// Collision entre Missile et tank 
		if(dist_y < missile.getTaille()){	
			collision=true;
			return true;
		}else{
			//collision=false;
			return false;
		}
	}
	
	public void affichageTourJoueur(Tank tankJ){
		StdDraw.setPenColor(StdDraw.BLACK);
		if (tankJ.getNom()=="Tank1"){
			StdDraw.text(450, 400, "Tour Joueur 1");
		
		} else if (tankJ.getNom()=="Tank2"){
			StdDraw.text(450, 400, "Tour Joueur 2");
		}
		
	}
	
// -------------- Accquisition force lancé --------------		
	public static double forcelancé (Fenetre fenetre){
		double force=0;
		int monter=1; 

		while(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){					
				if((force<100)&(monter==1)){
					//force =Math.round(force+(100/61)*Math.cos((2*3.14*force)/360));// force =100 : +cos(0=0/100)  force=0 : cos(100/100)
					//force=Math.round(force+0.5);
					force++;
					fenetre.affPuissance(force);							
				} else {
					monter =0;
				}
				
				if((force>0)&(monter==0)){
					//force=Math.round(force-0.5);
					force--;
					fenetre.affPuissance(force);						
				} else {
					monter =1;
				}
			}
		return force;
	}
	
	public boolean getCollision(){
		return collision;
	}
	
	public void setCollision(boolean pcol){
		collision = false;
	}
	public boolean getCanShoot(){
		return canShoot;
	}
	public void setCanShoot(boolean pShoot){
		canShoot=pShoot;
	}
	//-------------------------------- 
	

	
	
}// Fin de classe
