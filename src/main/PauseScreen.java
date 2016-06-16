package main;

import java.awt.*;
import input.KeyInput;

public class PauseScreen 
{
	public static enum State {resume, restart, quit}; 		// Enumeracija-u kojim stanjima moze biti pause screen
	public static State pauseState = State.resume;	// Trenutno stanje pause screen-a
	
	private boolean resume, restart, quit;	// Koje stanje je trenutno selektovano
	
	// Konstruktor
	public PauseScreen()
	{
		resume = false;
		restart = false;
		quit = false;
	}
	
	public void tick()
	{
		resume = pauseState == State.resume;
		restart = pauseState == State.restart;
		quit = pauseState == State.quit;
		
		System.out.println("Resume " + resume + " restart " + restart + " quit " + quit);
			
		if(resume && KeyInput.menuEnter)
		{
			Game.paused = false;
			System.out.println("Resume " + resume);
		}
		else if(restart && KeyInput.menuEnter)
		{
			System.out.println("Restart " + Game.restart);
			Game.restart = true;
		}
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
			g.drawString("Restart", 80, 150);
			g.drawString("Quit", 80, 180);
		}
		else if(restart)
		{
			g.drawString("Resume", 80, 120);
			g.drawString("> Restart <", 80, 150);
			g.drawString("Quit", 80, 180);
		}
		else if(quit)
		{
			g.drawString("Resume", 80, 120);
			g.drawString("Restart", 80, 150);
			g.drawString("> Quit <", 80, 180);
		}
	}
}
