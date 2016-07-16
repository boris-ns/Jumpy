package objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import graphics.Textures;
import main.Game;

public class Torch extends Block
{
	public Torch(float x, float y, Textures t)
	{
		super(x, y, t);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(t.torch, (int)x, (int)y, null);
		
		Graphics2D g2d = (Graphics2D)g;
				
		Point2D center = new Point2D.Float((int)x, (int)y);		
		float radius = 200.0f;
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.black};
		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
		g2d.setPaint(p);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));
		g2d.fillRect(0, 0, Game.width, Game.height);
	}
}
