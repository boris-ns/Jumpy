package graphics;

import java.awt.image.BufferedImage;

// Klasa koja ucitava veliku sliku(skup svih textura)
public class SpriteSheet 
{
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	// Uz pomoc ove metode iz velike slike se izdvajaju male (pojedinacne) texture 
	// Izdvajaju se preko navodjenja kolona i redova i njihovih dimenzija
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		return image.getSubimage((col * width) - width, (row * height) - height, width, height);
	}
}
