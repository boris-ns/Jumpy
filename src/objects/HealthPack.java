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
	
	public HealthPack(float x, float y, Textures t)
	{
		this.x = x + 4;
		this.y = y;
		this.t = t;
		
		velY = 1.0f;
		heal = 30;
	}
	
	public void tick()
	{
		y += velY;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(t.healthPack, (int)x, (int)y, size, size, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
}
