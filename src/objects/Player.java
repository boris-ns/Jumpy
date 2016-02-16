package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player 
{
	private float x, y, velX, velY;
	private final float MAX_VELY = 15;
	private final int width, height;
	private boolean falling , jumping;
	private float gravity = 0.5f;
	
	public Player(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		width = 24;
		height = 48;
		
		falling = true;
		jumping = false;
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		if(falling || jumping)
		{
			velY += gravity;
			
			if(velY > MAX_VELY)
				velY = MAX_VELY;
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public float getVelX() { return velX;}
	public float getVelY() { return velY;}
	public boolean getFalling() { return falling;}
	public boolean getJumping() { return jumping;}
	public int getHeight() { return height;}
	public void setVelX(float velX) { this.velX = velX;}
	public void setVelY(float velY) { this.velY = velY;}
	public void setFalling(boolean falling) { this.falling = falling;}
	public void setJumping(boolean jumping) { this.jumping = jumping;}
	public void setY(float y) { this.y = y;}
}
