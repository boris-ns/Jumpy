package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;
import main.Game;
import sound.AudioPlayer;

public class Player 
{
	// Polja koja opisuju objekat tipa Player
	private float x, y, velX, velY, lastVelX; // velX,Y-brzina kretanja po X i Y osi, lastVelX-poslednja vrednost velX
	private final float MAX_VELY = 10;
	private final int width, height;
	private boolean falling , jumping, isPoisoned;
	private float gravity = 1f;
	private int health, damage, coinsCollected;
	private Textures t;
	private Animation walkLeftAnim, walkRightAnim;
	private AudioPlayer sfxGameOver;
	private int botsKilled;
	
	// Pomocna polja
	private int timer = 0, poisonedTimer;
	private boolean showSuperPowers;
	private int oldDamage;
	private int superPowerTimer = 600;
	
	// Konstruktor
	public Player(float x, float y, Textures t)
	{
		this.x = x;
		this.y = y;
		this.t = t;
		
		lastVelX = 1;
		width = 24;
		height = 48;
		coinsCollected = 0;
		health = 100;
		damage = 25;
		falling = true;
		jumping = false;
		isPoisoned = false;
		botsKilled = 0;
		
		sfxGameOver = new AudioPlayer("/gameOver.mp3");
	
		poisonedTimer = 40;
		
		// Ubacivanje slika za animacije koje se smenjuju brzinom 3
		// Postoje 2 animacije-za kretanje u levo i kretanje u desno
		walkLeftAnim = new Animation(3, t.playerTiles[2], t.playerTiles[3], t.playerTiles[4],
				t.playerTiles[5], t.playerTiles[6], t.playerTiles[7], t.playerTiles[8], t.playerTiles[9]);
		walkRightAnim = new Animation(3, t.playerTiles[10], t.playerTiles[11], t.playerTiles[12],
				t.playerTiles[13], t.playerTiles[14], t.playerTiles[15], t.playerTiles[16], t.playerTiles[17]);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		// Gravitacija koja sluzi da odredi kretanje igraca tokom njegovog padanja
		if(falling || jumping)
		{
			velY += gravity;
			
			if(velY > MAX_VELY)
				velY = MAX_VELY;
		}
		
		// Pamcenje poslednje vrednosti velX da bi se znalo u kom smeru treba ispaljivati metke
		if(velX != 0)
			lastVelX = velX;
		
		// Kada igrac izgubi zivot, status igre se postavlja na gameOver i pusta se zvuk gameOver.mp3
		if(health <= 0)
		{
			sfxGameOver.play(-5.0f);
			health = 0;
			Game.gameOver = true;
		}
		
		// Ukoliko je igrac sakupio vise od 25 novcica njegov damage se povecava
		if(coinsCollected >= 25)
		{
			coinsCollected -= 25;
			damage += 25;
			timer = 70;
			System.out.println("Damage: " + damage);
		}
		
		// Ukoliko se igrac otrovao pri dodiru sa Bat-om
		if(isPoisoned && --poisonedTimer == 0)
		{
			health -= 1;
			poisonedTimer = 40;
		}
		
		handleSuperPowers();
		
		walkLeftAnim.runAnimation();
		walkRightAnim.runAnimation();
	}
	
	private void handleSuperPowers()
	{		
		// TODO: POVECAJ BOTSKILLED == 5-7
		// RAZMOTRI POVECAJE TRAJANJA POWER UP-a
		if(botsKilled != 0 && botsKilled % 10 == 0 && !showSuperPowers)
		{
			showSuperPowers = true;
			superPowerTimer = 600;
			oldDamage = damage;
			damage = 100;
			System.out.println("Damage " + damage);
		}
		
		if(showSuperPowers)
			--superPowerTimer;
		
		if(superPowerTimer == 0 && showSuperPowers)
		{
			damage = oldDamage;
			showSuperPowers = false;
			// Ode se mora povecati botsKilled inace ce stalno raditi powerUp, tako da umesto na svaki 10ti
			// kill, pover up ce biti na svaki 9ti
			++botsKilled;
		}
	}
	
	public void render(Graphics g)
	{		
		// Ukoliko igrac stoji u mestu, iscrtavace se 1 slika u zavisnosti od poslednje vrednosti koju je imao velX
		if(velX == 0 && lastVelX > 0)
			g.drawImage(t.playerTiles[0], (int)x, (int)y, null);
		else if(velX == 0 && lastVelX < 0)
			g.drawImage(t.playerTiles[1], (int)x, (int)y, null);
		// Ukoliko je igrac u pokretnu u zavisnosti od smera kretanja izvrsava se odredjena animacija
		else if(velX > 0)
			walkLeftAnim.drawAnimation(g, (int)x, (int)y);
		else if(velX < 0)
			walkRightAnim.drawAnimation(g, (int)x, (int)y);
		
		// Sluzi za ispisivanje teksta "Damage increased" da bi igrac bio obavesten da mu je damage povecan
		if(timer-- >= 0)
		{
			g.setFont(new Font("Arial Black", 1, 15));
			g.setColor(Color.ORANGE);
			g.drawString("Damage", (int)x - 25, (int)y - 30);
			g.drawString("increased", (int)x - 35, (int)y - 10);
		}
	}
	
	// Metode koje vracaju kvadrat koji predstavlja okvir i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds() // Vraca pun okvir
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public Rectangle getBoundsTop() // Vraca gornji deo okvira 
	{
		return new Rectangle((int)x + 5, (int)y, width - 10, 5);
	}
	
	public Rectangle getBoundsBottom() // Vraca donji deo okvira
	{
		return new Rectangle((int)x + 5, (int)y + height - 5, width - 10, 5);
	}
	
	public Rectangle getBoundsLeft() // Vraca levi deo okvira
	{
		return new Rectangle((int)x, (int)y + 5, 5, height - 10);
	}

	public Rectangle getBoundsRight() // Vraca desni deo okvira
	{
		return new Rectangle((int)x + width - 5, (int)y + 5, 5, height - 10);
	}
	
	// Get metode
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
	
	public boolean getIsPoisoned()
	{
		return isPoisoned;
	}
	
	public int getBotsKilled()
	{
		return botsKilled;
	}
	
	public boolean getShowSuperPowers()
	{
		return showSuperPowers;
	}
	
	public int getSuperPowerTimer()
	{
		return superPowerTimer;
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
	
	public void setIsPoisoned(boolean isPoisoned)
	{
		this.isPoisoned = isPoisoned;
	}
	
	public void setBotsKilled(int botsKilled)
	{
		this.botsKilled = botsKilled;
	}
}
