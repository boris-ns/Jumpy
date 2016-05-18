package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;

// Klasa Enemy predstavlja neprijatelja kojeg igrac moze da ubije
public class Enemy 
{
	// Polja koja opisuju objekat tipa Enemy
	private float x, y;
	private float velX; // velX-brzina kretanja po X osi
	private int size, damage, health;;
	private Textures t;
	private Animation animWalkLeft, animWalkRight;
	
	// Konstruktor
	public Enemy(float x, float y, int direction, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		// Postoji 2 vrste neprijatelja-oni koji se krecu u levo i u desno
		// Na osnovu direction parametra se postavlja njihov smer kretanja
		if(direction == 1)
			velX = 1.5f;
		else if(direction == 2)
			velX = -1.5f;
		else
			velX = 0;
		
		size = 32;
		damage = 10;
		health = 100;
		
		// Ubacivanje slika koje se koriste za animaciju i smenjuju se brzinom 4
		// Postoje 2 animacije-za kretanje u levo i u desno
		animWalkLeft = new Animation(4, t.enemyTiles[0], t.enemyTiles[1]);
		animWalkRight = new Animation(4, t.enemyTiles[2], t.enemyTiles[3]);
	}
	
	public void tick()
	{
		x += velX;
				
		animWalkLeft.runAnimation();
		animWalkRight.runAnimation();
	}
	
	public void render(Graphics g)
	{
		// U zavisnosti od smera kretanja izvrsava se odredjena animacija
		if(velX < 0)
			animWalkLeft.drawAnimation(g, (int)x, (int)y);
		else if(velX > 0)
			animWalkRight.drawAnimation(g, (int)x, (int)y);
	}
	
	// Metode koje vracaju kvadrat koji predstavlja okvir i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds() // Vraca pun okvir
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
	
	public Rectangle getBoundsLeft() // Vraca okvir koji predstavlja levu stranu 
	{
		return new Rectangle((int)x, (int)y, 10, 10);
	}
	
	public Rectangle getBoundsRight() // Vraca okvir koji predstavlja desnu stranu
	{
		return new Rectangle((int)x + size - 10, (int)y, 10, 10);
	}
	
	// Get metode
	public int getHealth()
	{
		return health;
	}
	
	public float getVelX()
	{
		return velX;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	// Set metode
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
