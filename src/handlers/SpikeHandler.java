package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Spike;

// Klasa koja predstavlja listu svih Spike objekata u igri
public class SpikeHandler 
{
	public LinkedList<Spike> spikes = new LinkedList<Spike>();

	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Spike objekata u igri
		for(int i = 0; i < spikes.size(); i++)
			spikes.get(i).render(g);
	}
}
