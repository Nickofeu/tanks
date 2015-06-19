import java.awt.event.KeyEvent;


public class TankWarz {
	
	
	public static void main(String[] args){
		boolean tourJoueur;
		boolean copieJoueur=true;
		boolean changementMissile=true;

		
		// Constructeur des objets. 

		Map map = new Map();	
		map.Init();
		Etoile etoile = new Etoile();
		Clavier clavier=new Clavier();
		mouse	mouse=new mouse();
		Fenetre fenetre = new Fenetre(900,600); // xScale, yScale 
		double origineY1,  origineY2, origineX1, originex2;
		origineX1=200;
		originex2=750;
		origineY1=map.getYofX(origineX1);
		origineY2=map.getYofX(originex2);
		Tank tank1 = new Tank("Tank1",0,400,2,origineX1,origineY1+30);// (Nom,dammage,pv,munition,x,y,rotation cannon)
		tank1.setAngleTank(tank1.getAngleTank());
		Tank tank2 = new Tank("Tank2",0,400,2,originex2,origineY2+30);
		tank2.setAngleTank(tank2.getAngleTank());
		Tank tankJ, tankE;
		tankJ=tank2;
		tankE=tank1;
		//Map map = new Map();	
		Missile missileJeu; // Fin : dammage / taille missile
		Missile missileNormal = new Missile(50, 5,100,"Normal"); // Fin : dammage / taille missile / Munitions
		Missile missileVertical = new Missile(100,20,2,"Vertical");
		Missile missileAtomique = new  Missile(200,30,1,"Atomique");
		missileJeu=missileNormal;
		//-------------------
		

		
		fenetre.Init();						// Initialisation Fenetre
		fenetre.explication();				// EXplication avant départ du jeu 
		etoile.Init();
		clavier.settourJoueur(false);		// Init pour que joueur 1 commence 
		
		while(true){	
			
			tourJoueur=clavier.gettourJoueur();		//
			if(mouse.getClick()==5){			// Bouton OK appuyé on change de joueur
				tourJoueur=!tourJoueur;
				clavier.settourJoueur(tourJoueur);
			}
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_ENTER)==true){ // Touche ENtrée appuyé on change de joueur
				tourJoueur=!tourJoueur;
				clavier.settourJoueur(tourJoueur);				
				StdDraw.show(200);
			}
				
			
					
			if ((tourJoueur==true)&&(copieJoueur==true)){ // Tour du joueur 1
				clavier.setCanShoot(true);
				changementMissile=true;
				tankJ.setEssence(200);
				tank1=tankE;	// Modif apporté sur le tank ennemie sauvegardé dans le tank 2
				tank2=tankJ;	// Modif du tank joueur dans tank 1		
				tankJ=tank1;	// On copie le tank 1 dans le tank Joueur				
				tankE=tank2;	// On défini le tank Enememi = tank 2
				copieJoueur=false;				// il faut copier que la premiere fois
			
			} 
			else if ((tourJoueur==false)&&(copieJoueur==false)){// tour du joueur 2	
				clavier.setCanShoot(true);
				changementMissile=true;
				tankJ.setEssence(200);
				tank2=tankE; // Modif apporté sur le tank ennemie sauvegardé dans le tank 2
				tank1=tankJ;// Modif du tank joueur dans tank 1
				tankJ=tank2;// On copie le tank2 dans le tank Joueur
				tankE=tank1;// On défini le tank enemie = tank 1
				copieJoueur=true;
				
			}	else {}

			
			StdDraw.clear();
			fenetre.Fond();					// Ciel			
			fenetre.cadre(tank1, tank2, missileVertical,map);	// Cadre affichage Joueur / munitions 
			etoile.trace();					// Grosse étoile fixe
			//etoile.trace();				// Scintillement petites étoiles
			map.traceTerrain();					// Map
			fenetre.affEssence(tankJ);		//Affichage de l'essence du joueur
			fenetre.affOK();
			//tank1.Affiche();
			clavier.moveTank(tankJ,map); //Acquisition touche déplacement
			clavier.moveCannon(tankJ);// Acquisition mouvement cannon 
			mouse.clickNumber();	
			tankJ.angleTank(map);
			tankE.angleTank(map);

			tankJ.Affiche(map);				// Affichage tank Joueur
			tankE.Affiche(map);
			if(clavier.getCanShoot()){
				clavier.moveMissile(fenetre, missileJeu, tankJ, tankE, map);
			}

			tankE.Affiche(map);				// Affichage tank Joueur

			clavier.verifEssence(tankJ); // verif essence sinon changement de joueur 
			
			// Si utilisateur click missile vertical
			if (mouse.getClick()==1 ){			// Ne s'exécute qu'une fois
				fenetre.encadreMissile(tankJ);
				if (tankJ.getMunition() > 0){
					missileJeu=missileVertical;
					System.out.println("click");
				} else {
					StdDraw.text(450, 300, "Plus de Munitions Missile Vertical !");
					missileJeu=missileNormal;	
					//StdDraw.show(100);
				}
			
			// Si utilisateur click missile atomique
			}else if (mouse.getClick()==2){			// Ne s'exécute qu'une fois
					fenetre.encadreMissileAtomique(tankJ);
					if (tankJ.getMunitionAtomique() >0){
						missileJeu=missileAtomique;
						System.out.println("click");
					} else {
						StdDraw.text(450, 300, "Plus de Munitions Missile Atomique !");
						missileJeu=missileNormal;
					}
				// Click missile normal	
			}else if (mouse.getClick()==3 ){			// Ne s'exécute qu'une fois
				fenetre.encadreMissileNormal(tankJ);
				if (tankJ.getMunition() > 0){
					missileJeu=missileNormal;
					System.out.println("click");
				} else {
					StdDraw.text(450, 300, "Plus de Munitions Missile Verticale !");
					missileJeu=missileNormal;	
				}
				
			}
			
		/*	else if (tankJ.getMunition() <= 0){	//  Si plus de munition : Missile Jeu = Missile Normal
					missileJeu=missileNormal;
			} */
			if(clavier.getCollision()&&changementMissile) { // Son détecte une collision on éffectue 1 foi : Missile Jeu=MissileNormal
				missileJeu=missileNormal;
				changementMissile=false;
				
			}
			else{
				clavier.setCollision(false);
			}
			
			fenetre.affichageTourJoueur(tourJoueur);
			fenetre.findugame(tankJ,tankE,mouse,map,etoile);
			
	StdDraw.show(10);				 
	
		}   // Fin de While
	}		// Fin de main

	
	
	
	
	
	
} // FIn de classe
