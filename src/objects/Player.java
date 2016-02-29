package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player 
{
	private float x, y, velX, velY;
	private final float MAX_VELY = 10;
	private final int width, height;
	private boolean falling , jumping;
	private float gravity = 1f;
	private boolean isColliding;
	
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
		
//		g.setColor(Color.yellow);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.draw(getBoundsBottom());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public Rectangle getBoundsTop()
	{
		return new Rectangle((int)x + 5, (int)y, width - 10, 5);
	}
	
	public Rectangle getBoundsBottom()
	{
		return new Rectangle((int)x + 5, (int)y + height - 5, width - 10, 5);
	}
	
	public Rectangle getBoundsLeft()
	{
		return new Rectangle((int)x, (int)y + 5, 5, height - 10);
	}

	public Rectangle getBoundsRight()
	{
		return new Rectangle((int)x + width - 5, (int)y + 5, 5, height - 10);
	}
	
	
	public float getVelX() { return velX;}
	public float getVelY() { return velY;}
	public float getX() { return x;}
	public float getY() { return y;}
	public boolean getFalling() { return falling;}
	public boolean getJumping() { return jumping;}
	public int getHeight() { return height;}
	public int getWidth() { return width;}
	
	public void setVelX(float velX) { this.velX = velX;}
	public void setVelY(float velY) { this.velY = velY;}
	public void setFalling(boolean falling) { this.falling = falling;}
	public void setJumping(boolean jumping) { this.jumping = jumping;}
	public void setY(float y) { this.y = y;}
	public void setX(float x) { this.x = x;}
	public void setIsColliding(boolean isColliding) { this.isColliding = isColliding;}
}
