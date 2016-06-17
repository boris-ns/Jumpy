package handlers;

import java.util.HashMap;

import objects.Block;
import objects.Boss;
import objects.Coin;
import objects.MovingBlock;
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
	private HealthPackHandler hph;
	private BatHandler batH;
	
	// HashMap koji sluzi za skladistenje SFX-a
	private HashMap<String, AudioPlayer> sfx;
	
	// Pomocna polja
	private Block block;
	private Coin coin;
	
	// Konstruktor
	public CollisionHandler(Player p, Boss boss, BlockHandler bl, CoinsHandler cl, BulletHandler bhl,
			 BossBulletsHandler bbh, SpikeHandler sh, EnemiesHandler eh, SmartWallHandler swh, HealthPackHandler hph,
			 BatHandler batH)
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
		this.hph = hph;
		this.batH = batH;
		
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
			
				if(block instanceof MovingBlock)
					p.setX(p.getX() + 1.4f * block.getMovingDirection());
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
			
			// Detekcija dodira izmedju blokova i Bat objekata
			for(int j = 0; j < batH.bats.size(); j++)
			{	
				// Ukoliko dodje do dodira sa blokom menja se smer kretanja Enemy objekta
				if(batH.bats.get(j).getBoundsRight().intersects(block.getBounds()))
					batH.bats.get(j).setVelX(batH.bats.get(j).getVelX() * (-1));
				else if(batH.bats.get(j).getBoundsLeft().intersects(block.getBounds()))
					batH.bats.get(j).setVelX(batH.bats.get(j).getVelX() * (-1));
				
//				if(batH.bats.get(j).getBounds().intersects(p.getBounds()))
//				{
//					// TODO: Player se otruje i samnjuje mu HP za 1 svakih 20 tickova i mora da nadje protivotrov
//				}
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
				sfx.get("Coin").play(-10.0f);
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
		
		// Detekcija dodira Bat-ova i igraca i igracevih metkova
		for(int i = 0; i < batH.bats.size(); i++)
		{
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				if(batH.bats.get(i).getBounds().intersects(bhl.bullets.get(j).getBounds()))
				{
					System.out.println("Enemy health: " + batH.bats.get(i).getHealth());
					batH.bats.get(i).setHealth(batH.bats.get(i).getHealth() - p.getDamage());
					bhl.bullets.remove(j);
				}
			}
			
			if(batH.bats.get(i).getBounds().intersects(p.getBounds().getBounds()))
				p.setIsPoisoned(true);
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
		
		// Detekcija dodira izmedju Playera i HealthPack objekata
		for(int i = 0; i < hph.healthPack.size(); i++)
		{
			if(p.getBounds().intersects(hph.healthPack.get(i).getBounds()))
			{
				p.setIsPoisoned(false);
				
				// Ukoliko Player ima full helte i nije zarazen samo preskoci iteraciju i ne dodaje helte
				if(p.getHealth() == 100 && !p.getIsPoisoned())
					continue;
				
				if(p.getHealth() + hph.healthPack.get(i).getHeal() >= 100)
					p.setHealth(100);
				else
					p.setHealth(p.getHealth() + hph.healthPack.get(i).getHeal());
			
				sfx.get("Heal").play(-10.0f);
				hph.healthPack.remove(i);
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
		//sfx.put("Coin", new AudioPlayer("/coinflip.mp3")); // NOTE: Ako koristis ovaj sfx smanji zvuk na -20.0f
		sfx.put("Coin", new AudioPlayer("/coinflipCut.mp3"));
		sfx.put("Punch", new AudioPlayer("/punch.mp3"));
		sfx.put("Door", new AudioPlayer("/jail_cell_door.mp3"));
		sfx.put("Hurt", new AudioPlayer("/hurt.mp3"));
		sfx.put("Heal", new AudioPlayer("/heal.mp3"));
	}
}
