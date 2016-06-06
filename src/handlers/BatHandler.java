package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Bat;

public class BatHandler 
{
	public LinkedList<Bat> bats = new LinkedList<Bat>();
	
	public void tick()
	{
		for(int i = 0; i < bats.size(); i++)
		{
			bats.get(i).tick();
			
			if(bats.get(i).getHealth() <= 0)
				bats.remove(i);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < bats.size(); i++)
			bats.get(i).render(g);
	}
}
