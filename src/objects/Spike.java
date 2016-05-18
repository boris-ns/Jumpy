package objects;

import java.awt.*;
import graphics.Textures;

// Klasa Spike predstavlja siljke na koje kada igrac naleti izgubi deo zivota
public class Spike 
{
	// Polja koja opisuju objekat tipa Spike
	private float x, y;
	private int size;
	private int damage;
	private Textures t;
	
	// Konstruktor
	public Spike(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		size = 32;
		damage = 10;
	}
	
	// Metoda tick() ne postoji jer Spike nema nikakvih fizickih dogadjaja
	// public void tick() { }
	
	public void render(Graphics g)
	{
		// Iscrtavanje slike siljka na poziciji (X,Y)
		g.drawImage(t.spikes, (int)x, (int)y, null);
	}
	
	// Metoda koja vraca okvir siljka i sluzi za detekciju dodira sa igracem
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y + size / 2, size, size / 2);
	}

	// Get metode
	public int getDamage() 
	{
		return damage;
	}	
}
