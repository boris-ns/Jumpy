package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;
import objects.Block;

public class BlockHandler 
{
	public LinkedList<Block> blocks = new LinkedList<Block>();
	
	public BlockHandler()
	{
		levelDesign();
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			blocks.get(i).render(g);
		}
	}
	
	public void addBlock(Block block)
	{
		blocks.add(block);
	}
	
	public void removeBlock(int i)
	{
		blocks.remove(i);
	}
	
	public void levelDesign()
	{
		for(int i = 0; i < Game.width; i += Block.size)
			blocks.add(new Block(i, 420));
	}
}
