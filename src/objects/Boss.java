package objects;

import java.awt.*;
import graphics.Animation;
import graphics.Textures;
import handlers.BossBulletsHandler;
import main.Game;
import sound.AudioPlayer;

public class Boss 
{
	// Polja koja opisuju objekat tipa Boss
	public static final int size = 64;
	private float x, y, velY; // velY-brzina kretanja po Y osi
	private int health, damage;
	private Textures t;
	private Animation animation;

	// Pomocna polja 
	private int timer = 25;
	private BossBulletsHandler bbh;
	private AudioPlayer shootingSFX;	
	
	// Konstruktor
	public Boss(float x, float y, Textures t, BossBulletsHandler bbh)
	{
		this.x = x;
		this.y =y;
		this.t = t;
		this.bbh = bbh;
		
		velY = 1.5f;
		health = 1000;
		damage = 20;
		shootingSFX = new AudioPlayer("/pistol.mp3");
		// Ubacivanje slika koje ucestvuju u animaciji Boss-a i menjaju se brzinom 4
		animation = new Animation(4, t.bossTiles[0], t.bossTiles[1]);
	}
	
	public void tick(int playerX, int playerY)
	{
		// Ako je Boss ziv njegova pozicija na Y osi se menja, u suprotnom
		// gameFinished se postavlja na ture i igrac je pobedio
		if(health > 0)
		{
			y += velY;
			
			// Ukoliko je igrac dostigao ovu Y poziciju, timer se smanjuje i na svakih 25 tick-ova
			// Boss ispaljuje po  1 metak ispred ili iza sebe u zavisnosti gde se igrac nalazi
			
// TODO: playerY > 75 je za level2 , nadji neki bolji nacin da realizujes ovo a ne da moras da stavljas || 
			// u svakom levelu gde se ovaj Boss ubaci
			if(playerY > 85 * 32 || playerY > 75 * 32)
			{			
				--timer;
				if(timer == 0)
				{
					shootingSFX.play(-10.0f);
					bbh.bullets.add(new BossBullet((int)x, (int)y, (playerX < x + size / 2) ? -1 : 1, 
							size, size));
					timer = 25;
				}
			}
			animation.runAnimation();
		}
		else
			Game.gameFinished = true;
	}
	
	public void render(Graphics g)
	{
		if(health > 0)
		{
			// Iscrtavanje health bar-a iznad Boss-a
			g.setColor(Color.RED);
			g.fillRect((int)x - 20, (int)y - 15, health / 10, 10);
		
			animation.drawAnimation(g, (int)x, (int)y);
		}
	}
	
	// Metoda getBounds() vraca kvadrat koji predstavlja okvir i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size / 2, size);
	}
	
	public Rectangle getBoundsFull()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}

	// Get metode
	public float getVelY() 
	{
		return velY;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getDamage()
	{
		return damage;
	}

	// Set metode
	public void setVelY(float velY) 
	{
		this.velY = velY;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
}
