package objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import graphics.Textures;

public class Torch extends Block
{
	float radius = 200.0f;
	float[] dist = {0.0f, 1.0f};
	Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.black};
	RadialGradientPaint p;
	Point2D center;
	
	public Torch(float x, float y, Textures t)
	{
		super(x, y, t);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(t.torch, (int)x, (int)y, null);
		
		Graphics2D g2d = (Graphics2D)g;
				
		center = new Point2D.Float((int)x, (int)y);				
		p = new RadialGradientPaint(center, radius, dist, colors);
		g2d.setPaint(p);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));
	}
}
