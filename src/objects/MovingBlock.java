package objects;

import graphics.Textures;

public class MovingBlock extends Block
{
	private int timer = 200;
	
	public MovingBlock(float x, float y, Textures t) 
	{
		super(x, y, t);
		
		velX = 0.7f;
	}

	@Override
	public void tick()
	{
		x += velX;
		
		if(--timer == 0)
		{
			velX *= -1;			
			timer = 200;
		}
	}
}