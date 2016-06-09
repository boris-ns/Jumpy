package objects;

import java.awt.Graphics;
import graphics.Animation;
import graphics.Textures;

public class Water extends Block
{	
	public Water(float x, float y, Textures t) 
	{
		super(x, y, t);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(t.water, (int)x, (int)y, size, size, null);
	}
}
