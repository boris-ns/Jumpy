package handlers;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.SmartWall;

public class SmartWallHandler 
{
	public LinkedList<SmartWall> smartWalls = new LinkedList<SmartWall>();
	
	public void render(Graphics g)
	{
		for(int i = 0; i < smartWalls.size(); i++)
			smartWalls.get(i).render(g);
	}
}
