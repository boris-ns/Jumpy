package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import handlers.BulletHandler;
import main.Game;
import main.PauseScreen;
import objects.Bullet;
import objects.Player;
import sound.AudioPlayer;

// Klasa koja sluzi kao listener za tastaturu
public class KeyInput extends KeyAdapter
{
	public static boolean menuUp, menuDown, menuEnter; // Polja za navigaciju kroz pause screen
	
	private boolean[] keyboard = new boolean[150]; // Niz svih tastera na tastaturi
	private boolean moveLeft, moveRight, fire, jump; // Polja za kretanje igraca 

	// Pomocna polja
	private Player player;
	private BulletHandler bHandler;
	private AudioPlayer shootingSFX = new AudioPlayer("/pistol.mp3");
	private int timer = 17;
	
	public void tick(Player player, BulletHandler bHandler)
	{	
		moveLeft = keyboard[KeyEvent.VK_LEFT];
		moveRight = keyboard[KeyEvent.VK_RIGHT];
		fire = keyboard[KeyEvent.VK_SPACE];
		jump = keyboard[KeyEvent.VK_UP];
	
		// Ukoliko je igrac skocio vrsi se postavljanje odredjenih parametara preko kojih se izvrsava skok
		if(jump && !player.getJumping())
		{
			player.setJumping(true);
			player.setVelY(-15);
		}
		
		// Kretanje igraca u odredjenom pravcu u zavisnosti od pritisnutog tastera
		if(moveLeft) 
			player.setVelX(-4);
		else if(moveRight)
			player.setVelX(4);
		else
			player.setVelX(0);
		
		// Deo koji sluzi za ispaljivanje metkova (metkovi se ispaljuju na svakom 17-tom tick-u) i pustanje SFX-a
		--timer; // NOTE: Nemoj stavljati u if dole jer je cudan osecaj kada se puca(Ne reaguje na 1 stisak space-a)
		if(fire && timer <= 0)
		{
			shootingSFX.play(-10.0f);
			bHandler.bullets.add(new Bullet((int)player.getX(), (int)player.getY(), player.getLastVelX(), player.getHeight(), player.getWidth()));
			timer = 17;
		}
	}
	
	// Poziva se prilikom pritiska dugmeta na tastaturi
	@Override
	public void keyPressed(KeyEvent e)
	{
		keyboard[e.getKeyCode()] = true; // Postavlja vrednost tastera koje je pritisnuto na true
		
		// Aktiviranje pause screen-a
		if(keyboard[KeyEvent.VK_ESCAPE] && !Game.paused && !Game.gameOver)
			Game.paused = true;
		
		// Navigacija kroz pause screen
		if(Game.paused)
		{
			menuUp = keyboard[KeyEvent.VK_UP];
			menuDown = keyboard[KeyEvent.VK_DOWN];
			menuEnter = keyboard[KeyEvent.VK_ENTER];
			
			if(PauseScreen.pauseState == PauseScreen.State.resume && menuDown)
				PauseScreen.pauseState = PauseScreen.State.restart;
			else if(PauseScreen.pauseState == PauseScreen.State.restart && menuDown)
				PauseScreen.pauseState = PauseScreen.State.quit;
			else if(PauseScreen.pauseState == PauseScreen.State.quit && menuDown)
				PauseScreen.pauseState = PauseScreen.State.resume;
			
			else if(PauseScreen.pauseState == PauseScreen.State.resume && menuUp)
				PauseScreen.pauseState = PauseScreen.State.quit;
			else if(PauseScreen.pauseState == PauseScreen.State.restart)
				PauseScreen.pauseState = PauseScreen.State.resume;
			else if(PauseScreen.pauseState == PauseScreen.State.quit && menuUp)
				PauseScreen.pauseState = PauseScreen.State.restart;
		}
	}
	
	// Kada je pritisnuti taster pusten njegova vrednost se postavlja na false
	@Override
	public void keyReleased(KeyEvent e)
	{
		keyboard[e.getKeyCode()] = false;
	}
}
