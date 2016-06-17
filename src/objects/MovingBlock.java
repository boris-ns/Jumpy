package objects;

import graphics.Textures;

public class MovingBlock extends Block
{
	private float velX;
	private int timer = 75;
	
	public MovingBlock(float x, float y, Textures t) 
	{
		super(x, y, t);
		
		velX = 0.5f;
	}

	@Override
	public void tick()
	{
		x += velX;
		
		if(--timer == 0)
		{
			velX *= -1;			
			timer = 75;
		}
	}
}
