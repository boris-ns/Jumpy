package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin 
{
	public static final int size = 24;
	private float x, y;
	private boolean isCollected;
	
	public Coin(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		isCollected = false;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, size, size);
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
