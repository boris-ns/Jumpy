package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation 
{
	private int speed;
	private BufferedImage[] images;
	
	private int index = 0;
	private int count = 0;
	private int frames;
	private BufferedImage currentImg;

	public Animation(int speed, BufferedImage... args)
	{
		this.speed = speed;
		images = new BufferedImage[args.length];
		
		for(int i = 0; i < args.length; i++)
			images[i] = args[i];
		
		frames = args.length;
	}	
	
	// Izvrsavanje animacije
	public void runAnimation()
	{
		index++;
		if(index > speed)
		{
			index = 0;
			nextFrame();
		}
	}
		
	// Iscrtavanje trenutne slike na ekran bez promene dimenzija
	public void drawAnimation(Graphics g, int x, int y)
	{
		g.drawImage(currentImg, x, y, null);
	}
		
	// Iscrtavanje trenutne slike na ekran sa promenom dimenzija
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY)
	{
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}
		
	// Metoda koja menja vrednost currentImg na sledecu sliku 
	private void nextFrame()
	{
		for(int i = 0; i < frames; i++)
		{
			if(i == count)
				currentImg = images[i];
		}
	
		count++;
		
		if(count > frames)
			count = 0;
	}
}
