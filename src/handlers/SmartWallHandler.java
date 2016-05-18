package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.SmartWall;

// Klasa koja predstavlja listu svih SmartWall objekata u igri
public class SmartWallHandler 
{
	public LinkedList<SmartWall> smartWalls = new LinkedList<SmartWall>();
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih SmartWall objekata u igri
		for(int i = 0; i < smartWalls.size(); i++)
			smartWalls.get(i).render(g);
	}
}
