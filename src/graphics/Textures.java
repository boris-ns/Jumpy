package graphics;

import java.awt.image.BufferedImage;

public class Textures 
{
	private SpriteSheet playerSheet, blockSheet;
	private BufferedImage player, blocks;
	
	public BufferedImage[] playerTiles = new BufferedImage[18];
	public BufferedImage[] blockTiles = new BufferedImage[3];
	public BufferedImage[] coinTiles = new BufferedImage[6];
	public BufferedImage[] enemyTiles = new BufferedImage[4];
	public BufferedImage spikes = null;
	
	
	public Textures()
	{	
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
		blockTiles[2] = blockSheet.grabImage(3, 1, 32, 32);
		
		spikes = blockSheet.grabImage(1, 3, 32, 32);
		
		coinTiles[0] = blockSheet.grabImage(1, 2, 32, 32);
		coinTiles[1] = blockSheet.grabImage(2, 2, 32, 32);
		coinTiles[2] = blockSheet.grabImage(3, 2, 32, 32);
		coinTiles[3] = blockSheet.grabImage(4, 2, 32, 32);
		coinTiles[4] = blockSheet.grabImage(5, 2, 32, 32);
		coinTiles[5] = blockSheet.grabImage(6, 2, 32, 32);
		
		enemyTiles[0] = blockSheet.grabImage(1, 4, 32, 32); // velX < 0
		enemyTiles[1] = blockSheet.grabImage(2, 4, 32, 32);	// velX < 0
		enemyTiles[2] = blockSheet.grabImage(3, 4, 32, 32); // velX > 0
		enemyTiles[3] = blockSheet.grabImage(4, 4, 32, 32); // velX > 0
		
		playerTiles[0] = playerSheet.grabImage(1, 1, 24, 48); // Steady velX > 0
		playerTiles[1] = playerSheet.grabImage(2, 1, 24, 48); // Steady velX < 0
		playerTiles[2] = playerSheet.grabImage(1, 2, 24, 48); // Walk right
		playerTiles[3] = playerSheet.grabImage(2, 2, 24, 48);
		playerTiles[4] = playerSheet.grabImage(3, 2, 24, 48);
		playerTiles[5] = playerSheet.grabImage(4, 2, 24, 48);
		playerTiles[6] = playerSheet.grabImage(5, 2, 24, 48);
		playerTiles[7] = playerSheet.grabImage(6, 2, 24, 48);
		playerTiles[8] = playerSheet.grabImage(7, 2, 24, 48);
		playerTiles[9] = playerSheet.grabImage(8, 2, 24, 48);
		playerTiles[10] = playerSheet.grabImage(1, 3, 24, 48); // Walk left
		playerTiles[11] = playerSheet.grabImage(2, 3, 24, 48);
		playerTiles[12] = playerSheet.grabImage(3, 3, 24, 48);
		playerTiles[13] = playerSheet.grabImage(4, 3, 24, 48);
		playerTiles[14] = playerSheet.grabImage(5, 3, 24, 48);
		playerTiles[15] = playerSheet.grabImage(6, 3, 24, 48);
		playerTiles[16] = playerSheet.grabImage(7, 3, 24, 48);
		playerTiles[17] = playerSheet.grabImage(8, 3, 24, 48);
	}
}
