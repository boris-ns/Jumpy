package objects;

import java.awt.*;
import graphics.Animation;
import graphics.Textures;

// Klasa Coin predstavlja novcice koje igrac moze da sakuplja
public class Coin 
{
	// Polja koja opisuju objekat tipa Coin
	public static final int size = 24;
	private float x, y;
	private boolean isCollected; // Da li je novcic pokupljen, ako jeste onda se unistava
	private Textures t;
	private Animation coinAnimation;
	
	// Konstruktor
	public Coin(float x, float y, Textures t)
	{
		this.x = x + 4;
		this.y = y;
		this.t = t;
		
		// Ubacivanje slika koje ucestvuju u animaciji Coin-a i menjaju se brzinom 3
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
		coinAnimation.drawAnimation(g, (int)x, (int)y, size, size);
	}
	
	// Metoda getBounds() vraca kvadrat koji predstavlja okvir jednog novcica i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds()
	{
		return new Rectangle((int)x + 5, (int)y + 5, size - 5, size - 5);
	}

	// Get metode
	public boolean getIsCollected() 
	{
		return isCollected;
	}

	// Set metode
	public void setIsCollected(boolean isCollected) 
	{
		this.isCollected = isCollected;
	}
}
