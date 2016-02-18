package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block 
{
	private float x, y;
	public static final int size = 32;
	
	public Block(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, size, size);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public Rectangle getBoundsForJump()
	{
		return new Rectangle((int)x, (int)y, size, 5);
	}
	
	public float getY() { return y;}
}
