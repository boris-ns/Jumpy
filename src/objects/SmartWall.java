package objects;

import java.awt.Graphics;

import graphics.Textures;

// Klasa SmartWall nasledjuje klasu Block 
// SmartWall sluzi kao kapija-kada je igrac prodje ona se zatvara i blokira mu put nazad
public class SmartWall extends Block
{	
	// Dodatno polje koje opisuje SmartWall je visible
	private boolean visible;
	
	// Konstruktor
	public SmartWall(float x, float y, Textures t) 
	{
		super(x, y, t); // Pozivanje konstruktora nadklase
		visible = false;
	}
	
	@Override
	public void render(Graphics g)
	{
		// Ukoliko je igrac prosao kapiju ona postaje vidljiva i pocinje da se render-uje
		if(visible)
			g.drawImage(t.blockTiles[2], (int)x, (int)y, null);
	}
	
//	@Override
//	public Rectangle getBounds()
//	{
//		if(visible)
//			return new Rectangle((int)x - size, (int)y, size, size);
//		else if(!visible)
//			return new Rectangle((int)x, (int)y, size, size);
//		
//		return null;
//	}
	
	// Get metode
	public boolean getVisible()
	{
		return visible;
	}
	
	// Set metode
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
