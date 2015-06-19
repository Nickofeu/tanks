import java.awt.event.KeyEvent;

public class mouse {
	int click;
	boolean clickdone=false;
	
	public mouse(){
		click=0;
	}
	
	public void clickNumber() {
		if (StdDraw.mousePressed()){
			clickdone=true;
		}else if (clickdone==true){
				// Click Missile Vertical
				if((805 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=835)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=1;
						clickdone=false;
					}
				} else if((155 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=185)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=1;
						clickdone=false;
					}
				}
				//Click Missile Atomique
				if((865 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=905)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=2;
						clickdone=false;
					}
				} else if((210 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=250)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=2;
						clickdone=false;
					}
				}
				//Click Missile NORMAL
				if((930 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=940)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=3;
						clickdone=false;
					}
				} else if((280 <= StdDraw.mouseX()) && (StdDraw.mouseX() <=290)){
					if ((585 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=635)){
						click=3;
						clickdone=false;
					}
				}
				
				// Click Fin du game 
				if((390 <= StdDraw.mouseX()) && (StdDraw.mouseX() <= 510)){//300-52=248 : L=120;l=40
					if ((188 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=308)){
						click=4;
						clickdone=false;
					}
				} 
				// Click fin de tour
				if((535 <= StdDraw.mouseX()) && (StdDraw.mouseX() <= 565)){//centre : x=550, y=610
					if ((595 <= StdDraw.mouseY()) && (StdDraw.mouseY()<=615)){
						click=5;
						clickdone=false;
					}
				} 
			
				//
		}else if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
				click=4;		
		}else{
			click=0;
		}
	}// Fin clickNumber
	
	public int getClick(){
		return click;
	}
	public void setClick(int pclick){
		click=pclick;
	}
}// Fin classe
