package objects;

import java.awt.Graphics;
import graphics.Textures;

public class Lava extends Block
{	
	public Lava(float x, float y, Textures t) 
	{
		super(x, y, t);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(t.lava, (int)x, (int)y, size, size, null);
	}
}
