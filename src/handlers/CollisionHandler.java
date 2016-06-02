package handlers;

import java.util.HashMap;
import objects.Block;
import objects.Boss;
import objects.Coin;
import objects.Player;
import objects.SmartWall;
import sound.AudioPlayer;

// Klasa koja sluzi za detekciju dodira izmedju svih objekata u igri
public class CollisionHandler 
{
	// Objekti u igri
	private Player p; 
	private Boss boss;
	private BlockHandler bl;
	private CoinsHandler cl;
	private BulletHandler bhl;
	private BossBulletsHandler bbh;
	private SpikeHandler sh;
	private EnemiesHandler eh;
	private SmartWallHandler swh;
	
	// HashMap koji sluzi za skladistenje SFX-a
	private HashMap<String, AudioPlayer> sfx;
	
	// Pomocna polja
	private Block block;
	private Coin coin;
	
	// Konstruktor
	public CollisionHandler(Player p, Boss boss, BlockHandler bl, CoinsHandler cl, BulletHandler bhl,
			 BossBulletsHandler bbh, SpikeHandler sh, EnemiesHandler eh, SmartWallHandler swh)
	{
		this.p = p;
		this.boss = boss;
		this.bl = bl;
		this.cl = cl;
		this.bhl = bhl;
		this.bbh = bbh;
		this.sh = sh;
		this.eh = eh;
		this.swh = swh;		
		
		initSFX();
	}
	
	public void tick()
	{	
		// Sva desavanja koja su uslovljena dodirom sa jednim od Block objekata
		for(int i = 0; i < bl.blocks.size(); i++)
		{
			block = bl.blocks.get(i);
			
			// Ako igrac udari "glavom o neki od Block objekata"
			if(p.getBoundsTop().intersects(block.getBounds()))
				p.setY(block.getY() + Block.size);
			
			// Sprecava da igrac propadne kroz pod
			if(p.getBoundsBottom().intersects(block.getBounds()))
			{
				p.setVelY(0);
				p.setY(block.getY() - p.getHeight());
				p.setFalling(false);
				p.setJumping(false);
			}
			else
				p.setFalling(true);

			// Sprecava igraca da prodje kroz blokove krecuci se u levo
			if(p.getBoundsLeft().intersects(block.getBounds()))
				p.setX(block.getX() + Block.size);
			
			// Sprecava igraca da prodje kroz blokove krecuci se u desno
			if(p.getBoundsRight().intersects(block.getBounds()))
				p.setX(block.getX() - p.getWidth());
			
			// Kada Boss dodirne neki blok njegov velY se menja i tako se dobija efekat kretanja gore-dole
			if(boss.getBounds().intersects(block.getBounds()))
				boss.setVelY(boss.getVelY() * (-1));
			
			// Detekcija dodira igracevih metkova sa blokovima i Boss-om
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				// Ukoliko metak dodirne blok on se unistava
				if(block.getBounds().intersects(bhl.bullets.get(j).getBounds()))
				{
					bhl.bullets.remove(j);
					continue;
				}
				
				// Ukoliko metak dodirne Boss-a , smanjuju mu se helti i metak se unistava
				if(boss.getBoundsFull().intersects(bhl.bullets.get(j).getBounds()))
				{
					boss.setHealth(boss.getHealth() - p.getDamage());
					bhl.bullets.remove(j);
				}
			}
			
			// Detekcija dodira izmedju blokova i Enemy objekata
			for(int j = 0; j < eh.enemies.size(); j++)
			{	
				// Ukoliko dodje do dodira sa blokom menja se smer kretanja Enemy objekta
				if(eh.enemies.get(j).getBoundsRight().intersects(block.getBounds()))
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
				else if(eh.enemies.get(j).getBoundsLeft().intersects(block.getBounds()))
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
			}	
			
			// Detekcija dodira metkova koje ispaljuje Boss sa blokovima i igracem
			for(int j = 0; j < bbh.bullets.size(); j++)
			{
				// Ukoliko metak dodirne blok on se unistava
				if(bbh.bullets.get(j).getBounds().intersects(block.getBounds()))
				{
					bbh.bullets.remove(j);
					continue;
				}
				
				// Ukoliko metak pogodi igraca, smanjuju se helti i metak se unistava
				if(bbh.bullets.get(j).getBounds().intersects(p.getBounds()))
				{
					bbh.bullets.remove(j);
					p.setHealth(p.getHealth() - boss.getDamage());
				}
			}
		}
		
		// Detekcija dodira sa Coin objektima
		for(int i = 0; i < cl.coins.size(); i++)
		{
			coin = cl.coins.get(i);
			
			// Ukoliko igrac dodirne Coin objekat,povecava se coinsCollected i pusta se odredjeni SFX
			if(p.getBounds().intersects(coin.getBounds()) && !coin.getIsCollected())
			{
				sfx.get("Coin").play(-20.0f);
				p.setCoinsCollected(p.getCoinsCollected() + 1);
				coin.setIsCollected(true);
			}
		}
		
		// Detekcija dodira sa Spike objektima
		for(int i = 0; i < sh.spikes.size(); i++)
		{
			// Ukoliko igrac dodirne Spike objekat, smanjuju mu se helti,odskace od Spike-a i pusta se odredjeni SFX
			if(sh.spikes.get(i).getBounds().intersects(p.getBounds()))
			{
				sfx.get("Hurt").play(-10.0f);
				p.setHealth(p.getHealth() - sh.spikes.get(i).getDamage());
				p.setVelY(-15);
				p.setJumping(true);
			}
		}
		
		// Detekcija dodira sa Enemy objektima
		for(int i = 0; i < eh.enemies.size(); i++)
		{
			// Ukoliko dodje do dodira igraca i Enemy-a, smanjuju se igracevi helti, odskace od njega i pusta se odredjeni SFX
			if(eh.enemies.get(i).getBounds().intersects(p.getBounds()))
			{
				sfx.get("Punch").play(-20.0f);
				p.setHealth(p.getHealth() - eh.enemies.get(i).getDamage());
				p.setJumping(true);
				p.setVelY(-15);
			}
			
			// Detekcija dodira igracevih metkova i Enemy objekata
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				// Ukoliko dodje do dodira smanjuju se helti neprijatelja i metak se unistava
				if(eh.enemies.get(i).getBounds().intersects(bhl.bullets.get(j).getBounds()))
				{
					System.out.println("Enemy health: " + eh.enemies.get(i).getHealth());
					eh.enemies.get(i).setHealth(eh.enemies.get(i).getHealth() - p.getDamage());
					System.out.println("Enemy health: " + eh.enemies.get(i).getHealth());
					bhl.bullets.remove(j);
				}
			}
		}
		
		// Detekcija dodira igraca i SmartWall objekata
		for(int i = 0; i < swh.smartWalls.size(); i++)
		{	
			// Zatvaranje kapije u zavisnosti od smera dolaska igraca, pustanje odredjenog zvuka
			if(p.getBoundsRight().intersects(swh.smartWalls.get(i).getBounds()) && !swh.smartWalls.get(i).getVisible())
			{
				sfx.get("Door").play(-10.0f);
				p.setX(swh.smartWalls.get(i).getX() + SmartWall.size);
				swh.smartWalls.get(i).setVisible(true);
				swh.smartWalls.get(i + 1).setVisible(true);
			}
			else if(p.getBoundsLeft().intersects(swh.smartWalls.get(i).getBounds()) && !swh.smartWalls.get(i).getVisible())
			{
				sfx.get("Door").play(-10.0f);
				p.setX(swh.smartWalls.get(i).getX() - p.getWidth());
				swh.smartWalls.get(i).setVisible(true);
				swh.smartWalls.get(i + 1).setVisible(true);
			}			
			// Blokiranje igraca od prolazenja kroz zatvorenu kapiju u zavisnosti od smera njegovog dolaska
			else if(p.getBoundsRight().intersects(swh.smartWalls.get(i).getBounds()))
			{
				p.setX(swh.smartWalls.get(i).getX() - p.getWidth());
			}
			else if(p.getBoundsLeft().intersects(swh.smartWalls.get(i).getBounds()))
			{
				p.setX(swh.smartWalls.get(i).getX() + SmartWall.size);
			}
			
			// Detekcija dodira SmartWall objekata i metkova koje ispaljuje igrac
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				if(bhl.bullets.get(j).getBounds().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(j).getVisible())
					bhl.bullets.remove(j);
			}
			
			// Usmeravanje Enemy objekata prilikom dodira sa nekim od SmartWall objekata
			for(int j = 0; j < eh.enemies.size(); j++)
			{	
				if(eh.enemies.get(j).getBoundsRight().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(i).getVisible())
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
				else if(eh.enemies.get(j).getBoundsLeft().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(i).getVisible())
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
			}	
		}
		
		// Ako dodje do dodira izmedju Boss-a i igraca, helti igraca se odma postavljaju na 0 i pusta se odredjeni zvuk
		if(p.getBounds().intersects(boss.getBoundsFull()) && boss.getHealth() > 0)
		{
			sfx.get("Punch").play(-20.0f);
			p.setHealth(0);
		}
	}
	
	// Ubacivanje svih SFX-a u HashMap
	private void initSFX()
	{
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Coin", new AudioPlayer("/coinflip.mp3"));
		sfx.put("Punch", new AudioPlayer("/punch.mp3"));
		sfx.put("Door", new AudioPlayer("/jail_cell_door.mp3"));
		sfx.put("Hurt", new AudioPlayer("/hurt.mp3"));
	}
}
