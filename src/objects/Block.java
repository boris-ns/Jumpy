package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import graphics.Textures;

public class Block 
{
	// Polja koja opisuju objekat tipa Block 
	public static final int size = 32;
	protected float x, y;
	protected Textures t;
	
	// Konstruktor
	public Block(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
	}
	
	// Klasa Block nema metodu tick() jer Block objekti nemaju nikakve fizicke dogadjaje
	// Aku tick mora postojati zbog MovingBlock-ova
	public void tick() { }
	
	public void render(Graphics g)
	{
		// Metoda drawImage() iscrtava sliku bloka iz niza tipa BufferedImage[] na koordinatama (X,Y) 
		g.drawImage(t.blockTiles[0], (int)x, (int)y, null);
	}
	
	// Metoda getBounds() vraca kvadrat koji predstavlja okvir jednog bloka i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	// Get metode
	public float getX() { return x;}
	public float getY() { return y;}
}
