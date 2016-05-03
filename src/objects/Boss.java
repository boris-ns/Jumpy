package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;
import handlers.BossBulletsHandler;
import main.Game;

public class Boss 
{
	private float x, y, velY;
	private int health, damage;
	private int size;
	private Textures t;
	private Animation animation;
	
	private int timer = 25;
	private BossBulletsHandler bbh;
	
	public Boss(float x, float y, Textures t, BossBulletsHandler bbh)
	{
		this.x = x;
		this.y =y;
		this.t = t;
		this.bbh = bbh;
		
		velY = 1.5f;
		health = 1000;
		damage = 20;
		size = 64;
		
		animation = new Animation(4, t.bossTiles[0], t.bossTiles[1]);
	}
	
	public void tick(int playerX, int playerY)
	{
		if(health > 0)
		{
			y += velY;
			
			if(playerY > 2464)
			{			
				--timer;
				if(timer == 0)
				{
					bbh.bullets.add(new BossBullet((int)x, (int)y, (playerX < x + size / 2) ? -1 : 1, 
							size, size));
					timer = 25;
				}
			}
			
			animation.runAnimation();
		}
		else
			Game.gameFinished = true;
	}
	
	public void render(Graphics g)
	{
		if(health > 0)
		{
			g.setColor(Color.RED);
			g.fillRect((int)x - 20, (int)y - 15, health / 10, 10);
		
			animation.drawAnimation(g, (int)x, (int)y);
		}
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size / 2, size);
	}
	
	public Rectangle getBoundsFull()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}

	public float getVelY() 
	{
		return velY;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getDamage()
	{
		return damage;
	}

	public void setVelY(float velY) 
	{
		this.velY = velY;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
