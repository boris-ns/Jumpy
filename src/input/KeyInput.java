package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Player;

public class KeyInput extends KeyAdapter
{
	private Player player;
	
	private boolean[] keyboard = new boolean[150];
	private boolean moveLeft, moveRight, fire, jump;
	
	public KeyInput(Player player)
	{
		this.player = player;
	}
	
	public void tick()
	{
		moveLeft = keyboard[KeyEvent.VK_LEFT];
		moveRight = keyboard[KeyEvent.VK_RIGHT];
		fire = keyboard[KeyEvent.VK_CONTROL];
		jump = keyboard[KeyEvent.VK_SPACE];
		
		if(keyboard[KeyEvent.VK_ESCAPE])
			System.exit(-1);
		
		if(jump && !player.getJumping())
		{
			player.setJumping(true);
			player.setVelY(-15);
		}
		
		if(moveLeft) 
			player.setVelX(-5);
		else if(moveRight)
			player.setVelX(5);
		else
			player.setVelX(0);
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
