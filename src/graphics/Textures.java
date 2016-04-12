package graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class Textures 
{
	private SpriteSheet playerSheet, blockSheet;
	private BufferedImage player, blocks;
	private Dimension playerDim, blockDim;
	
	public BufferedImage[] playerTiles = new BufferedImage[2];
	public BufferedImage[] blockTiles = new BufferedImage[2];
	public BufferedImage[] coinTiles = new BufferedImage[6];
	
	
	public Textures()
	{
		this.playerDim = playerDim;
		this.blockDim = blockDim;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		player = loader.loadImage("/player.png");
		blocks = loader.loadImage("/blocks.png");
		
		playerSheet = new SpriteSheet(player);
		blockSheet = new SpriteSheet(blocks);
		
		getTextures();
	}
	
	private void getTextures()
	{
		blockTiles[0] = blockSheet.grabImage(1, 1, 32, 32);
		blockTiles[1] = blockSheet.grabImage(2, 1, 32, 32);
		
		coinTiles[0] = blockSheet.grabImage(1, 2, 32, 32);
		coinTiles[1] = blockSheet.grabImage(2, 2, 32, 32);
		coinTiles[2] = blockSheet.grabImage(3, 2, 32, 32);
		coinTiles[3] = blockSheet.grabImage(4, 2, 32, 32);
		coinTiles[4] = blockSheet.grabImage(5, 2, 32, 32);
		coinTiles[5] = blockSheet.grabImage(6, 2, 32, 32);
		
		playerTiles[0] = playerSheet.grabImage(1, 1, 24, 48);
		
	}
}
