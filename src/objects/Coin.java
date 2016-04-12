package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class Coin 
{
	public static final int size = 24;
	private float x, y;
	private boolean isCollected;
	private Textures t;
	
	public Coin(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		isCollected = false;
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.yellow);
		//g.fillRect((int)x, (int)y, size, size);
		g.drawImage(t.coinTiles[5], (int)x, (int)y, size, size, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}

	public boolean getIsCollected() 
	{
		return isCollected;
	}

	public void setIsCollected(boolean isCollected) 
	{
		this.isCollected = isCollected;
	}
}
