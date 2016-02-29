package handlers;

import objects.Block;
import objects.Player;

public class CollisionHandler 
{
	private Player p;
	private BlockHandler bl;
	private Block block;
	
	public CollisionHandler(Player p, BlockHandler bl)
	{
		this.p = p;
		this.bl = bl;
	}
	
	public void tick()
	{
		for(int i = 0; i < bl.blocks.size(); i++)
		{
			block = bl.blocks.get(i);
			
			if(p.getBoundsTop().intersects(block.getBounds()))
				p.setY(block.getY() + Block.size);
			
			if(p.getBoundsBottom().intersects(bl.blocks.get(i).getBounds()))
			{
				p.setVelY(0);
				p.setY(block.getY() - p.getHeight());
				p.setFalling(false);
				p.setJumping(false);
			}
			else
				p.setFalling(true);

			if(p.getBoundsLeft().intersects(block.getBounds()))
				p.setX(block.getX() + Block.size);

			if(p.getBoundsRight().intersects(block.getBounds()))
				p.setX(block.getX() - p.getWidth());
		}
	}
}
