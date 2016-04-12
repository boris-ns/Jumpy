package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class Block 
{
	public static final int size = 32;
	private float x, y;
	private Textures t;
	
	public Block(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
	}
	
	public void render(Graphics g)
	{
//		g.setColor(Color.red);
//		g.fillRect((int)x, (int)y, size, size);
		g.drawImage(t.blockTiles[0], (int)x, (int)y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public float getX() { return x;}
	public float getY() { return y;}
}
