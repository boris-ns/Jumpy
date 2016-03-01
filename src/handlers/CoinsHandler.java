package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Coin;

public class CoinsHandler 
{
	public LinkedList<Coin> coins = new LinkedList<Coin>();
	
	public void tick()
	{
		for(int i = 0; i < coins.size(); i++)
		{
			if(coins.get(i).getIsCollected())
				coins.remove(i);
		}
	}
	
	public void render(Graphics g)
	{
		for(Coin coin : coins)
			coin.render(g);
	}
}
