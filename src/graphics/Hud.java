package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import objects.Player;

// Klasa Heads up Display (HUD) sluzi da obavestava igraca o njegovim heltima i sakupljenim novcicima
public class Hud 
{	
	private int coinsCollected, playerHealth, superPowerTimer;
	private boolean showSuperPowers;
	
	// Dobija trenutne vrednosti sakupljenih novcica i helta
	public void tick(int coins, int health, boolean showSuperPowers, int superPowerTimer)
	{
		coinsCollected = coins;
		playerHealth = health;
		this.showSuperPowers = showSuperPowers;
		this.superPowerTimer = superPowerTimer;
	}
	
	// Iscrtava i ispisuje na ekran dobijene vrednosti iz tick() metode
	public void render(Graphics g)
	{
		// Iscrtavanje Health bar-a
		g.setColor(Color.RED);
		g.fillRect(15, 15, playerHealth, 12);
		
		// Iscrtavanje super power bara
		if(showSuperPowers)
		{
			g.setColor(Color.GREEN);
			g.fillRect(30 + playerHealth, 15, superPowerTimer / 3, 12);
		}
		
		// Coins collected
		g.setFont(new Font("Arial Black", 1, 18));
		g.setColor(Color.BLACK);
		g.drawString("Coins: " + coinsCollected, 17, 52);
		g.setColor(Color.ORANGE);
		g.drawString("Coins: " + coinsCollected, 15, 50);
		
		// Level
		g.setColor(Color.BLACK);
		g.drawString("Level: " + Game.level, 17, 77);
		g.setColor(Color.ORANGE);
		g.drawString("Level: " + Game.level, 15, 75);
	}
}
