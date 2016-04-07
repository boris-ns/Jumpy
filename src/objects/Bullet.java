package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet 
{
	private float x, y;
	private float velX;
	private int size;
	
	public Bullet(float x, float y, float playerLastVelX, float playerHeight, float playerWidth)
	{
		this.x = x + playerWidth / 2;
		this.y = y + playerHeight / 2;
		size = 4;
		
		if(playerLastVelX == 0)
			velX = 6;
		else if(playerLastVelX > 0)
			velX = 6;
		else if(playerLastVelX < 0)
			velX = -6;
	}
	
	public void tick()
	{
		x += velX;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, size, size);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
}
