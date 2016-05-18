package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.BossBullet;

// Klasa koja predstavlja listu svih BossBullet objekata u igri
public class BossBulletsHandler 
{
	public LinkedList<BossBullet> bullets = new LinkedList<BossBullet>();
	
	public void tick()
	{
		// Pozivanje tick metoda svih BossBullet objekata u igri
		for(int i = 0; i < bullets.size(); i++)
			bullets.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih BossBullet objekata u igri
		for(int i = 0; i < bullets.size(); i++)
			bullets.get(i).render(g);
	}
}
