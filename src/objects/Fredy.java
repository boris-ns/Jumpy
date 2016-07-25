package objects;

import java.awt.Graphics;

import graphics.Textures;
import sound.AudioPlayer;

public class Fredy 
{
	private final int WIDTH, HEIGHT;
	private float x, y, velX, velY;
	private Textures t;
	private AudioPlayer sfxGhost;
	
	public Fredy(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		WIDTH = 24;
		HEIGHT = 48;
		
		sfxGhost = new AudioPlayer("/ghost.mp3");
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		
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
