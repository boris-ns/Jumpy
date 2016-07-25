package objects;

import java.awt.Graphics;

import graphics.Textures;
import main.Game;
import sound.AudioPlayer;

public class Fredy 
{
	// Polja
	private final int WIDTH, HEIGHT;
	private float x, y, velX, velY;
	private Textures t;
	private AudioPlayer sfxGhost;
	
	// Pomocna polja
	private int timer = 20;
	private boolean canIPlay = true;
	private final int DISTANCE = 230;
	
	public Fredy(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		velX = 0.9f;
		velY = 0.5f;
		
		WIDTH = 24;
		HEIGHT = 48;
		
		sfxGhost = new AudioPlayer("/ghost.mp3");
	}
	
	public void tick(float playerX, float playerY)
	{
		x += velX;
		y += velY;
		
		// Odbijanje od krajeva levog i desnog zida
		if(x <= 0)
			velX *= -1;
		else if(x >= Game.width * 3)
			velX *= -1;
		
		// Odbijanje od gornjeg zida
		if(y <= 0)
			velY *= -1;
		else if(y >= 3000)
			velY *= -1;	
		
		if(x > playerX - DISTANCE && x < playerX + DISTANCE && y > playerY - DISTANCE && y < playerY + DISTANCE)
		{
			if(canIPlay)
			{
				sfxGhost.loopPlay(-15.0f);
				canIPlay = false;
			}
		}
		else
		{
			canIPlay = true;
			sfxGhost.stop();
		}
	}
	
	public void render(Graphics g)
	{
		if(velX > 0)
			g.drawImage(t.fredyTiles[0], (int)x, (int)y, null);
		else if(velX < 0)
			g.drawImage(t.fredyTiles[1], (int)x, (int)y, null);
		else 
			System.out.println("Doslo je do greske prilikom loadovanja slike Fredy-a");
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
}
