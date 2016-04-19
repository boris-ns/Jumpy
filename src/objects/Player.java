package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;

public class Player 
{
	private float x, y, velX, velY, lastVelX;
	private final float MAX_VELY = 10;
	private final int width, height;
	private boolean falling , jumping;
	private float gravity = 1f;
	private int coinsCollected;
	private int health, damage;
	private Textures t = new Textures();
	private Animation walkLeftAnim, walkRightAnim;
	
	public Player(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		lastVelX = 1;
		
		width = 24;
		height = 48;
		
		coinsCollected = 0;
		health = 100;
		damage = 50;
		
		falling = true;
		jumping = false;
		
		walkLeftAnim = new Animation(3, t.playerTiles[2], t.playerTiles[3], t.playerTiles[4],
				t.playerTiles[5], t.playerTiles[6], t.playerTiles[7], t.playerTiles[8], t.playerTiles[9]);
		walkRightAnim = new Animation(3, t.playerTiles[10], t.playerTiles[11], t.playerTiles[12],
				t.playerTiles[13], t.playerTiles[14], t.playerTiles[15], t.playerTiles[16], t.playerTiles[17]);
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
		
		if(velX != 0)
			lastVelX = velX;

		walkLeftAnim.runAnimation();
		walkRightAnim.runAnimation();
	}
	
	public void render(Graphics g)
	{		
		if(velX == 0 && lastVelX > 0)
			g.drawImage(t.playerTiles[0], (int)x, (int)y, null);
		else if(velX == 0 && lastVelX < 0)
			g.drawImage(t.playerTiles[1], (int)x, (int)y, null);
	
		else if(velX > 0)
			walkLeftAnim.drawAnimation(g, (int)x, (int)y);
		else if(velX < 0)
			walkRightAnim.drawAnimation(g, (int)x, (int)y);
		
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
	
	
	public float getX() 
	{
		return x;
	}
	
	public float getY() 
	{
		return y;
	}
	
	public float getVelX()
	{
		return velX;
	}
	
	public float getVelY() 
	{
		return velY;
	}
	
	public float getLastVelX()
	{
		return lastVelX;
	}
	
	public boolean getFalling() 
	{
		return falling;
	}
	
	public boolean getJumping()
	{
		return jumping;
	}
	
	public int getHeight() 
	{
		return height;
	}
	
	public int getWidth() 
	{
		return width;
	}
	
	public int getCoinsCollected() 
	{
		return coinsCollected;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setX(float x)
	{ 
		this.x = x;
	}
	
	public void setY(float y) 
	{ 
		this.y = y;
	}
	
	public void setVelX(float velX) 
	{ 
		this.velX = velX;
	}
	
	public void setVelY(float velY) 
	{
		this.velY = velY;
	}
	
	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}
	
	public void setJumping(boolean jumping) 
	{
		this.jumping = jumping;
	}

	public void setCoinsCollected(int coinsCollected) 
	{
		this.coinsCollected = coinsCollected;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
