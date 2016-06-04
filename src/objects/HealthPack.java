package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Textures;

public class HealthPack 
{
	public static final int size = 24;
	
	private float x, y, velY;
	private Textures t;
	private int heal;
<<<<<<< HEAD
	private int timer = 20;
=======
>>>>>>> f108158a945843a661c7116ef0ae0eb15711905c
	
	public HealthPack(float x, float y, Textures t)
	{
		this.x = x + 4;
		this.y = y;
		this.t = t;
		
<<<<<<< HEAD
		velY = 0.3f;
=======
		velY = 1.0f;
>>>>>>> f108158a945843a661c7116ef0ae0eb15711905c
		heal = 30;
	}
	
	public void tick()
	{
		y += velY;
<<<<<<< HEAD
		
		if(--timer == 0)
		{
			velY *= -1;
			timer = 20;
		}
=======
>>>>>>> f108158a945843a661c7116ef0ae0eb15711905c
	}
	
	public void render(Graphics g)
	{
		g.drawImage(t.healthPack, (int)x, (int)y, size, size, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
<<<<<<< HEAD
	
	public int getHeal()
	{
		return heal;
	}
=======
>>>>>>> f108158a945843a661c7116ef0ae0eb15711905c
}
