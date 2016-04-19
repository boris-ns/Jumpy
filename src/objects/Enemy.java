package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;

public class Enemy 
{
	private float x, y;
	private float velX;
	private int size, damage, health;;
	private Textures t;
	private Animation animWalkLeft, animWalkRight;
	
	public Enemy(float x, float y, int direction, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		if(direction == 1)
			velX = 1.5f;
		else if(direction == 2)
			velX = -1.5f;
		else
			velX = 0;
		
		size = 32;
		damage = 10;
		health = 100;
		
		animWalkLeft = new Animation(4, t.enemyTiles[0], t.enemyTiles[1]);
		animWalkRight = new Animation(4, t.enemyTiles[2], t.enemyTiles[3]);
	}
	
	public void tick()
	{
		x += velX;
				
		animWalkLeft.runAnimation();
		animWalkRight.runAnimation();
	}
	
	public void render(Graphics g)
	{
		if(velX < 0)
			animWalkLeft.drawAnimation(g, (int)x, (int)y);
		else if(velX > 0)
			animWalkRight.drawAnimation(g, (int)x, (int)y);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public Rectangle getBoundsLeft()
	{
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	public Rectangle getBoundsRight()
	{
		return new Rectangle((int)x + size - 10, (int)y, 10, 10);
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public float getVelX()
	{
		return velX;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
