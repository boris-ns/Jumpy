package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.Block;

public class RenderBlocksHandler
{
	public LinkedList<Block> blocks = new LinkedList<Block>();
	
	public void render(Graphics g)
	{
		for(int i = 0; i < blocks.size(); i++)
			blocks.get(i).render(g);
	}
}
