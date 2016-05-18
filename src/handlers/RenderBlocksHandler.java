package handlers;

import java.awt.Graphics;
import java.util.LinkedList;
import objects.Block;

// Klasa koja predstavlja listu Block objekata, ali onih koji se samo render-uju, tj. nemaju nikakvu interakciju sa igracem
// Napravljeni su da bi poboljsali performanse igre, jer je lista Block objekata sa kojima igrac ima interakciju manja
public class RenderBlocksHandler
{
	public LinkedList<Block> blocks = new LinkedList<Block>();
	
	public void render(Graphics g)
	{
		// Pozivanje render metoda svih Block-ova koji se samo renderuju
		for(int i = 0; i < blocks.size(); i++)
			blocks.get(i).render(g);
	}
}
