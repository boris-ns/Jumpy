package objects;

import java.awt.Color;
import java.awt.Graphics;

public class BossBullet extends Bullet
{
	public BossBullet(float x, float y, float playerLastVelX, float playerHeight, float playerWidth) 
	{
		super(x, y, playerLastVelX, playerHeight, playerWidth);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(255, 163, 163));
		g.fillRect((int)x, (int)y, size, size);
	}
}
