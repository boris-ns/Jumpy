package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Spike;

public class SpikeHandler 
{
	public LinkedList<Spike> spikes = new LinkedList<Spike>();
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < spikes.size(); i++)
			spikes.get(i).render(g);
	}
}
