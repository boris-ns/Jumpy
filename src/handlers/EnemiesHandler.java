package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Enemy;

public class EnemiesHandler 
{
	public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	public void tick()
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).tick();
			
			if(enemies.get(i).getHealth() == 0)
				enemies.remove(i);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < enemies.size(); i++)
			enemies.get(i).render(g);
	}
}
