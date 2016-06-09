package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Water;

public class WaterHandler 
{
	public LinkedList<Water> water = new LinkedList<Water>();
	
	public void render(Graphics g)
	{
		for(int i = 0; i < water.size(); ++i)
			water.get(i).render(g);
	}
}
