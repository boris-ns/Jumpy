package handlers;

import objects.Player;

public class CollisionHandler 
{
	private Player p;
	private BlockHandler bl;
	
	public CollisionHandler(Player p, BlockHandler bl)
	{
		this.p = p;
		this.bl = bl;
	}
	
	public void tick()
	{
		for(int i = 0; i < bl.blocks.size(); i++)
		{
			if(p.getBounds().intersects(bl.blocks.get(i).getBounds()))
			{
				p.setY(bl.blocks.get(i).getY() - p.getHeight());
				p.setVelY(0);
				p.setFalling(false);
				p.setJumping(false);
			}
			else
				p.setFalling(true);
		}
	}
}
