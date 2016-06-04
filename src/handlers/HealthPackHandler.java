package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.HealthPack;

public class HealthPackHandler 
{
	public LinkedList<HealthPack> healthPack = new LinkedList<HealthPack>();
	
	public void tick()
	{
		for(int i = 0; i < healthPack.size(); i++)
			healthPack.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < healthPack.size(); i++)
			healthPack.get(i).render(g);
	}
}
