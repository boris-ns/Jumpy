package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class HealthPack 
{
	public static final int size = 24;
	
	private float x, y, velY;
	private Textures t;
	private int heal;
	private int timer = 20;
	
	public HealthPack(float x, float y, Textures t)
	{
		this.x = x + 4;
		this.y = y;
		this.t = t;
		
		velY = 0.3f;
		heal = 30;
	}
	
	public void tick()
	{
		y += velY;
		
		if(--timer == 0)
		{
			velY *= -1;
			timer = 20;
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(t.healthPack, (int)x, (int)y, size, size, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public int getHeal()
	{
		return heal;
	}
}
