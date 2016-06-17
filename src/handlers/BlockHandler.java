package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Block;

// Klasa koja predstavlja listu svih Block objekata u igri
public class BlockHandler 
{
	public LinkedList<Block> blocks = new LinkedList<Block>();
	
	public void tick()
	{
		for(int i = 0; i < blocks.size(); ++i)
			blocks.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Block objekata u igri
		for(Block block : blocks)
			block.render(g);
	}
}
