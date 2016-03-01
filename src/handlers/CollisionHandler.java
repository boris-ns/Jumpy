package handlers;

import objects.Block;
import objects.Coin;
import objects.Player;

public class CollisionHandler 
{
	private Player p;
	private BlockHandler bl;
	private CoinsHandler cl;
	
	private Block block;
	private Coin coin;
	
	public CollisionHandler(Player p, BlockHandler bl, CoinsHandler cl)
	{
		this.p = p;
		this.bl = bl;
		this.cl = cl;
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
		
		for(int i = 0; i < cl.coins.size(); i++)
		{
			coin = cl.coins.get(i);
			
			if(p.getBounds().intersects(coin.getBounds()))
			{
				p.setCoinsCollected(p.getCoinsCollected() + 1);
				coin.setIsCollected(true);
			}
		}
	}
}
