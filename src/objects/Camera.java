package objects;

import main.Game;

public class Camera 
{
	// Polja koja opisuju objekat tipa Camera
	private float x, y;
	
	// Konstruktor
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void tick(Player player)
	{
		y = -player.getY() + Game.width / 3;

		// Postavljanje kamere na jednu od tri pozicija u zavisnosti X pozicije igraca
		if(player.getX() > Game.width * 2 - Block.size * 2)
			x = -Game.width * 2 + Block.size * 2 + 10;
		else if(player.getX() > Game.width - Block.size)
			x = -Game.width + Block.size + 7;
		else if(player.getX() < Game.width - Block.size)
			x = 0;		
	}
	
	// Klasa Camera nema metodu render() zato sto nema sta da se iscrtava na ekranu
	
	// Get metode
	public float getX() 
	{ 
		return x;
	}
	
	public float getY() 
	{ 
		return y;
	}
	
	// Set metode
	public void setX(float x) 
	{
		this.x = x;
	}
	
	public void setY(float y) 
	{ 
		this.y = y;
	}
}
