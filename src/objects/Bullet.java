package objects;

import java.awt.*;

// Klasa Bullet predstavlja metke koje ispaljuje igrac
public class Bullet 
{
	// Polja koja opisuju objekte tipa Bullet
	protected float x, y;
	protected float velX; // velX-brzina kretanja Bullet-a po X osi
	protected int size;
	
	// Konstruktor
	public Bullet(float x, float y, float playerLastVelX, float playerHeight, float playerWidth)
	{
		this.x = x + playerWidth / 2;
		this.y = y + playerHeight / 2;
		size = 4;
	
		// Provera smera kretanja igraca da bi se znalo u kom smeru se ispaljuje metak
		if(playerLastVelX == 0) // Na pocetku igre metak se ispaljuje u desno
			velX = 6;
		else if(playerLastVelX > 0)
			velX = 6;
		else if(playerLastVelX < 0)
			velX = -6;
	}
	
	public void tick()
	{
		x += velX;
	}
	
	public void render(Graphics g)
	{
		// Postavljanje boje i iscrtavanje na koordinatama X,Y, dimenzija size x size
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, size, size);
	}
	
	// Metoda getBounds() vraca kvadrat koji predstavlja okvir i sluzi za detekciju dodira sa drugim objektima
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, size, size);
	}
}
