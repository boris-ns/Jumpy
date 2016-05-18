package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Klasa Heads up Display (HUD) sluzi da obavestava igraca o njegovim heltima i sakupljenim novcicima
public class Hud 
{	
	private int coinsCollected, playerHealth;
	
	// Dobija trenutne vrednosti sakupljenih novcica i helta
	public void tick(int coins, int health)
	{
		coinsCollected = coins;
		playerHealth = health;
	}
	
	// Iscrtava i ispisuje na ekran dobijene vrednosti iz tick() metode
	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(15, 15, playerHealth, 12);
		
		g.setFont(new Font("Arial Black", 1, 18));
		g.setColor(Color.ORANGE);
		g.drawString("Coins: " + coinsCollected, 15, 50);
	}
}
