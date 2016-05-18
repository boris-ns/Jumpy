package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Coin;

// Klasa koja predstavlja listu svih Coin objekata u igri
public class CoinsHandler 
{
	public LinkedList<Coin> coins = new LinkedList<Coin>();
	
	public void tick()
	{
		// Pozivanje tick metoda svih Coin objekata u igri
		for(int i = 0; i < coins.size(); i++)
		{
			coins.get(i).tick();
			
			// Ukoliko je igrac pokupio Coin on se brise iz liste
			if(coins.get(i).getIsCollected())
				coins.remove(i);
		}
	}
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Coin objekata u igri
		for(Coin coin : coins)
			coin.render(g);
	}
}
