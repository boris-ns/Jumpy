package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Enemy;

// Klasa koja predstavlja listu Enemy objekata u igri
public class EnemiesHandler 
{
	public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	public void tick()
	{
		// Pozivanje tick metoda svih Enemy objekata u igri
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).tick();
			
			// Ukoliko im je health == 0 oni se brisu iz liste
			if(enemies.get(i).getHealth() == 0)
				enemies.remove(i);
		}
	}
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Enemy objekata u igri
		for(int i = 0; i < enemies.size(); i++)
			enemies.get(i).render(g);
	}
}
