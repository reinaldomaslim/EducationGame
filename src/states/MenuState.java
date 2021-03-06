package states;


import handler.Handler;

import java.awt.*;

import gfx.Assets;

/**
 * Created by adrien on 12/05/16.
 * EducationGame project class
 */
public class MenuState extends State{

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

		//game A is launched if game B button is pressed
		if(handler.getMouseManager().isLeftPressed() && (186 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 586) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
    		CurrentState.setState(handler.getGame().gameState = new GameAState(handler,1));
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

		//Game B launched if B button is pressed
		if(handler.getMouseManager().isLeftPressed() && (772 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 1172) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
    		CurrentState.setState(handler.getGame().gameState = new GameBState(handler,1));
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}

//		//Game C launched if C button is pressed
//		if(handler.getMouseManager().isLeftPressed() && (920 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 1320) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
//    		CurrentState.setState(handler.getGame().gameState = new GameCState(handler));
//    		try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//    	}
//
//		//Game
//		if(handler.getMouseManager().isLeftPressed() && (900 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 964) && (500 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 564)){
//    		CurrentState.setState(handler.getGame().gameState = new GameCState(handler));
//    		try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//    	}
    }

    @Override
    public void render(Graphics g) {
    	//First game
    	g.drawImage(Assets.menuBackground, 0, 0, null);
    	if ((186 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 586) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
    		g.drawImage(Assets.gameA1, 186, 400, null);
    	}
    	else{
    		g.drawImage(Assets.gameA2, 186, 400, null);
    	}
		
		//2nd game
    	if((772 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 1172) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
    		g.drawImage(Assets.gameB1, 772, 400, null);
    	}
    	else{
    		g.drawImage(Assets.gameB2, 772, 400, null);
    	}
		
		//3rd game
//    	if((920 < handler.getMouseManager().getMouseX() && handler.getMouseManager().getMouseX() < 1320) && (400 < handler.getMouseManager().getMouseY() && handler.getMouseManager().getMouseY() < 600)){
//    		g.drawImage(Assets.gameC1, 920, 400, null);
//    	}
//    	else{
//    		g.drawImage(Assets.gameC2, 920, 400, null);
//    	}
		

    }
}
