package handlers;

import java.util.HashMap;

import objects.Block;
import objects.Boss;
import objects.Coin;
import objects.Player;
import objects.SmartWall;
import sound.AudioPlayer;

public class CollisionHandler 
{
	private Player p;
	private Boss boss;
	private BlockHandler bl;
	private CoinsHandler cl;
	private BulletHandler bhl;
	private BossBulletsHandler bbh;
	private SpikeHandler sh;
	private EnemiesHandler eh;
	private SmartWallHandler swh;
	
	private HashMap<String, AudioPlayer> sfx;
	
	private Block block;
	private Coin coin;
	
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
	
	private void initSFX()
	{
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Coin", new AudioPlayer("/coinflip.mp3"));
		sfx.put("Punch", new AudioPlayer("/punch.mp3"));
		sfx.put("Door", new AudioPlayer("/jail_cell_door.mp3"));
		sfx.put("Hurt", new AudioPlayer("/hurt.mp3"));
	}
	
	public void tick()
	{	
		for(int i = 0; i < bl.blocks.size(); i++)
		{
			block = bl.blocks.get(i);
			
			if(p.getBoundsTop().intersects(block.getBounds()))
				p.setY(block.getY() + Block.size);
			
			if(p.getBoundsBottom().intersects(block.getBounds()))
			{
				p.setVelY(0);
				p.setY(block.getY() - p.getHeight());
				p.setFalling(false);
				p.setJumping(false);
			}
			else
				p.setFalling(true);

			if(p.getBoundsLeft().intersects(block.getBounds()))
				p.setX(block.getX() + Block.size);

			if(p.getBoundsRight().intersects(block.getBounds()))
				p.setX(block.getX() - p.getWidth());
			
			if(boss.getBounds().intersects(block.getBounds()))
				boss.setVelY(boss.getVelY() * (-1));
			
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				if(block.getBounds().intersects(bhl.bullets.get(j).getBounds()))
				{
					bhl.bullets.remove(j);
					continue;
				}
				
				if(boss.getBoundsFull().intersects(bhl.bullets.get(j).getBounds()))
				{
					boss.setHealth(boss.getHealth() - p.getDamage());
					bhl.bullets.remove(j);
				}
			}
			
			for(int j = 0; j < eh.enemies.size(); j++)
			{	
				if(eh.enemies.get(j).getBoundsRight().intersects(block.getBounds()))
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
				else if(eh.enemies.get(j).getBoundsLeft().intersects(block.getBounds()))
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
			}	
			
			for(int j = 0; j < bbh.bullets.size(); j++)
			{
				if(bbh.bullets.get(j).getBounds().intersects(block.getBounds()))
				{
					bbh.bullets.remove(j);
					continue;
				}
				
				if(bbh.bullets.get(j).getBounds().intersects(p.getBounds()))
				{
					bbh.bullets.remove(j);
					p.setHealth(p.getHealth() - boss.getDamage());
				}
			}
		}
		
		for(int i = 0; i < cl.coins.size(); i++)
		{
			coin = cl.coins.get(i);
			
			if(p.getBounds().intersects(coin.getBounds()) && !coin.getIsCollected())
			{
				sfx.get("Coin").play(-20.0f);
				p.setCoinsCollected(p.getCoinsCollected() + 1);
				coin.setIsCollected(true);
			}
		}
		
		for(int i = 0; i < sh.spikes.size(); i++)
		{
			if(sh.spikes.get(i).getBounds().intersects(p.getBounds()))
			{
				sfx.get("Hurt").play(-10.0f);
				p.setHealth(p.getHealth() - sh.spikes.get(i).getDamage());
				p.setVelY(-15);
				p.setJumping(true);
			}
		}
		
		for(int i = 0; i < eh.enemies.size(); i++)
		{
			if(eh.enemies.get(i).getBounds().intersects(p.getBounds()))
			{
				sfx.get("Punch").play(-20.0f);
				p.setHealth(p.getHealth() - eh.enemies.get(i).getDamage());
				p.setJumping(true);
				p.setVelY(-15);
			}
			
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				if(eh.enemies.get(i).getBounds().intersects(bhl.bullets.get(j).getBounds()))
				{
					eh.enemies.get(i).setHealth(eh.enemies.get(i).getHealth() - p.getDamage());
					bhl.bullets.remove(j);
				}
			}
		}
		
		for(int i = 0; i < swh.smartWalls.size(); i++)
		{	
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
			else if(p.getBoundsRight().intersects(swh.smartWalls.get(i).getBounds()))
			{
				p.setX(swh.smartWalls.get(i).getX() - p.getWidth());
			}
			else if(p.getBoundsLeft().intersects(swh.smartWalls.get(i).getBounds()))
			{
				p.setX(swh.smartWalls.get(i).getX() + SmartWall.size);
			}
			
			for(int j = 0; j < bhl.bullets.size(); j++)
			{
				if(bhl.bullets.get(j).getBounds().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(j).getVisible())
					bhl.bullets.remove(j);
			}
			
			for(int j = 0; j < eh.enemies.size(); j++)
			{	
				if(eh.enemies.get(j).getBoundsRight().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(i).getVisible())
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
				else if(eh.enemies.get(j).getBoundsLeft().intersects(swh.smartWalls.get(i).getBounds()) && swh.smartWalls.get(i).getVisible())
					eh.enemies.get(j).setVelX(eh.enemies.get(j).getVelX() * (-1));
			}	
		}
		
		if(p.getBounds().intersects(boss.getBoundsFull()) && boss.getHealth() > 0)
		{
			sfx.get("Punch").play(-20.0f);
			p.setHealth(0);
		}
	}
}
