package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class SmartWall extends Block
{	
	private boolean visible;
	
	public SmartWall(float x, float y, Textures t) 
	{
		super(x, y, t);
		
		visible = false;
	}
	
	@Override
	public void render(Graphics g)
	{
		if(visible)
			g.drawImage(t.blockTiles[2], (int)x, (int)y, null);
	}
	
//	@Override
//	public Rectangle getBounds()
//	{
//		if(visible)
//			return new Rectangle((int)x - size, (int)y, size, size);
//		else if(!visible)
//			return new Rectangle((int)x, (int)y, size, size);
//		
//		return null;
//	}
	
	public boolean getVisible()
	{
		return visible;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
