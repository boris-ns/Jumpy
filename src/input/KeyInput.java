package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import handlers.BulletHandler;
import main.Game;
import main.PauseScreen;
import objects.Bullet;
import objects.Player;

public class KeyInput extends KeyAdapter
{
	public static boolean menuUp, menuDown, menuEnter;
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
		
		if(keyboard[KeyEvent.VK_ESCAPE] && !Game.paused && !Game.gameOver)
			Game.paused = true;
		
		if(Game.paused)
		{
			menuUp = keyboard[KeyEvent.VK_UP];
			menuDown = keyboard[KeyEvent.VK_DOWN];
			menuEnter = keyboard[KeyEvent.VK_ENTER];
			
			if(PauseScreen.pauseState == PauseScreen.State.resume && menuDown)
				PauseScreen.pauseState = PauseScreen.State.quit;
			else if(PauseScreen.pauseState == PauseScreen.State.quit && menuDown)
				PauseScreen.pauseState = PauseScreen.State.resume;
			
			if(PauseScreen.pauseState == PauseScreen.State.resume && menuUp)
				PauseScreen.pauseState = PauseScreen.State.quit;
			else if(PauseScreen.pauseState == PauseScreen.State.quit && menuUp)
				PauseScreen.pauseState = PauseScreen.State.resume;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		keyboard[e.getKeyCode()] = false;
	}
}
