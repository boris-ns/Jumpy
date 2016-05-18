package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Bullet;

// Klasa koja predstavlja listu svih Bullet objekata u igri
public class BulletHandler 
{
	public LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	public void tick()
	{
		// Pozivanje tick metoda svih Bullet objekata u igri
		for(int i = 0; i < bullets.size(); i++)
		{
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Bullet objekata u igri
		for(Bullet bullet : bullets)
			bullet.render(g);
	}
}
