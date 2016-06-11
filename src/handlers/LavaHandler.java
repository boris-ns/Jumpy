package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Lava;

public class LavaHandler 
{
	public LinkedList<Lava> lava = new LinkedList<Lava>();
	
	public void render(Graphics g)
	{
		for(int i = 0; i < lava.size(); ++i)
			lava.get(i).render(g);
	}
}
