package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import handlers.BulletHandler;
import objects.Bullet;
import objects.Player;

public class KeyInput extends KeyAdapter
{
	private Player player;
	private BulletHandler bHandler;
	
	private boolean[] keyboard = new boolean[150];
	private boolean moveLeft, moveRight, fire, jump;
	
	private int timer = 17;
	
	public KeyInput(Player player, BulletHandler bHandler)
	{
		this.player = player;
		this.bHandler = bHandler;
	}
	
	public void tick()
	{
		moveLeft = keyboard[KeyEvent.VK_LEFT];
		moveRight = keyboard[KeyEvent.VK_RIGHT];
		fire = keyboard[KeyEvent.VK_SPACE];
		jump = keyboard[KeyEvent.VK_UP];
		
		if(keyboard[KeyEvent.VK_ESCAPE])
			System.exit(-1);
		
		if(jump && !player.getJumping())
		{
			player.setJumping(true);
			player.setVelY(-15);
		}
		
		if(moveLeft) 
			player.setVelX(-4);
		else if(moveRight)
			player.setVelX(4);
		else
			player.setVelX(0);
		
		--timer;
		if(fire && timer <= 0)
		{
			bHandler.bullets.add(new Bullet((int)player.getX(), (int)player.getY(), player.getLastVelX(), player.getHeight(), player.getWidth()));
			timer = 17;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		keyboard[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		keyboard[e.getKeyCode()] = false;
	}
}
