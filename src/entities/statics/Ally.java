package entities.statics;

import java.awt.Color;

import gfx.Animation;

import java.awt.Graphics;

import gfx.Assets;
import handler.Handler;
import tiles.Tile;
import utils.intefaces.healthDisplaying;

public class Ally extends StaticEntity implements healthDisplaying {

	private Animation animFront;
	
	public Ally(Handler handler, float x, float y, int health) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = width/4;
        bounds.y = height/2;
        bounds.width = width/2;
        bounds.height = height/2;
        this.health=health;
        
        animFront=new Animation(500, Assets.ally_front);
	}

	@Override
	public void tick() {
		animFront.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(animFront.getCurrentFrame(), (int) x, (int) y, width, height, null);
	
		displayHealth(g);
		
	}
	
	@Override
	public void die() {
		System.out.println(alive);
		//can spawn items player can collect
	}


	@Override
	public void displayHealth(Graphics g) {

		g.setColor(Color.black);
		g.drawRect((int)x,(int)y-(int)(width/2.5),width,height/4);
		g.setColor(Color.GREEN);
		g.fillRect((int)x,(int)y-(int)(width/2.5),width*health/MAX_HEALTH,height/4);
	}
}
