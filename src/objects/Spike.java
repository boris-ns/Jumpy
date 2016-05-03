package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class Spike 
{
	private float x, y;
	private int size;
	private int damage;
	private Textures t;
	
	public Spike(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		size = 32;
		damage = 10;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(t.spikes, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y + size / 2, size, size / 2);
	}

	public int getDamage() 
	{
		return damage;
	}	
}
