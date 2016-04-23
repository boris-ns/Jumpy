package objects;

import main.Game;

public class Camera 
{
	private float x, y;
	
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX() { return x;}
	public float getY() { return y;}
	public void setX(float x) { this.x = x;}
	public void setY(float y) { this.y = y;}
	
	public void tick(Player player)
	{
		y = -player.getY() + Game.width / 3;
		
		if(player.getX() > Game.width * 2 - Block.size * 2)
			x = -Game.width * 2 + Block.size * 2 + 10;
		else if(player.getX() > Game.width - Block.size)
			x = -Game.width + Block.size + 7;
		else if(player.getX() < Game.width - Block.size)
			x = 0;
		
	}
}
