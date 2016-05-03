package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.BossBullet;

public class BossBulletsHandler 
{
	public LinkedList<BossBullet> bullets = new LinkedList<BossBullet>();
	
	public void tick()
	{
		for(int i = 0; i < bullets.size(); i++)
			bullets.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < bullets.size(); i++)
			bullets.get(i).render(g);
	}
}
