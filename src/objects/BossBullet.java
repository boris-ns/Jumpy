package objects;

import java.awt.Color;
import java.awt.Graphics;

// Klasa BossBullet predstavlja metke koje ispaljuje Boss
// BossBullet nasledjuje Bullet i ponasa se isto kao ona, osim sto se koristi druga 
// boja za iscrtavanje u metodi render()
public class BossBullet extends Bullet
{
	// Konstruktor
	public BossBullet(float x, float y, float playerLastVelX, float playerHeight, float playerWidth) 
	{
		// Poziv konstruktora nadklase 
		super(x, y, playerLastVelX, playerHeight, playerWidth);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(255, 163, 163));
		g.fillRect((int)x, (int)y, size, size);
	}
}
