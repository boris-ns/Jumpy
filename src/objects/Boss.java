package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;

public class Boss 
{
	private float x, y, velY;
	private int health;
	private int size;
	private Textures t;
	private Animation animation;
	
	public Boss(float x, float y, Textures t)
	{
		this.x = x;
		this.y =y;
		this.t = t;
		
		velY = 1.5f;
		health = 1000;
		size = 64;
		
		animation = new Animation(4, t.bossTiles[0], t.bossTiles[1]);
	}
	
	public void tick()
	{
		y += velY;
		
		animation.runAnimation();
	}
	
	public void render(Graphics g)
	{
//		g.setColor(Color.red);
//		g.fillRect((int)x, (int)y, size, size);
		animation.drawAnimation(g, (int)x, (int)y);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size / 2, size);
	}

	public float getVelY() 
	{
		return velY;
	}

	public void setVelY(float velY) 
	{
		this.velY = velY;
	}
}
