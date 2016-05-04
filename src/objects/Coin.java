package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;

public class Coin 
{
	public static final int size = 24;
	private float x, y;
	private boolean isCollected;
	private Textures t;
	private Animation coinAnimation;
	
	public Coin(float x, float y, Textures t)
	{
		this.x = x + 4;
		this.y = y;
		this.t = t;
		
		coinAnimation = new Animation(3, 
				t.coinTiles[0], t.coinTiles[1], t.coinTiles[2],t.coinTiles[3],
				t.coinTiles[4], t.coinTiles[5], t.coinTiles[4],
				t.coinTiles[3], t.coinTiles[2], t.coinTiles[1]);
		
		isCollected = false;
	}
	
	public void tick()
	{
		coinAnimation.runAnimation();
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.yellow);
		//g.fillRect((int)x, (int)y, size, size);
		//g.drawImage(t.coinTiles[5], (int)x, (int)y, size, size, null);
		coinAnimation.drawAnimation(g, (int)x, (int)y, size, size);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x + 5, (int)y + 5, size - 5, size - 5);
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
