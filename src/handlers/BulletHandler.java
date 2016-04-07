package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Bullet;

public class BulletHandler 
{
	public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	public void tick()
	{
		for(int i = 0; i < bullets.size(); i++)
		{
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g)
	{
		for(Bullet bullet : bullets)
			bullet.render(g);
	}
}
