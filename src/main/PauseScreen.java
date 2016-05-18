package main;

import java.awt.*;
import input.KeyInput;

public class PauseScreen 
{
	public static enum State {resume, quit}; 		// Enumeracija-u kojim stanjima moze biti pause screen
	public static State pauseState = State.resume;	// Trenutno stanje pause screen-a
	
	private boolean resume, quit;	// Koje stanje je trenutno selektovano
	
	// Konstruktor
	public PauseScreen()
	{
		resume = false;
		quit = false;
	}
	
	public void tick()
	{
		resume = pauseState == State.resume;
		quit = pauseState == State.quit;
		
		if(resume && KeyInput.menuEnter)
			Game.paused = false;
		else if(quit && KeyInput.menuEnter)
			System.exit(-1);
	}
	
	// Iscrtavanje pause screen-a
	public void render(Graphics g)
	{
		g.setFont(new Font("Arial Black", 1, 24));
		g.setColor(Color.ORANGE);
		g.drawString("PAUSE MENU", 70, 70);
		
		g.setFont(new Font("Arial Black", 1, 18));
		
		if(resume)
		{
			g.drawString("> Resume <", 80, 120);
			g.drawString("Quit", 80, 150);
		}
		else if(quit)
		{
			g.drawString("Resume", 80, 120);
			g.drawString("> Quit <", 80, 150);
		}
	}
}
