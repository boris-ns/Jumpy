package graphics;

import java.awt.image.BufferedImage;

public class Textures 
{
	private static SpriteSheet playerSheet, blockSheet;
	private static BufferedImage player = null, blocks = null;
	
	public static BufferedImage[] playerTiles = new BufferedImage[18];	// Niz textura Player-a
	public static BufferedImage[] blockTiles = new BufferedImage[3];	// Niz textura Block-ova
	public static BufferedImage[] coinTiles = new BufferedImage[6];	// Niz textura Coins-a
	public static BufferedImage[] enemyTiles = new BufferedImage[4];	// Niz textura Enemy-a
	public static BufferedImage[] bossTiles = new BufferedImage[2];	// Niz textura Boss-a
	public static BufferedImage[] batTiles = new BufferedImage[4];		// Niz tekstura Bat-a
	public static BufferedImage[] fredyTiles = new BufferedImage[2]; 	// Niz teksutra za Fredy-a
	public static BufferedImage lava = null;							// Tekstura Water-a
	public static BufferedImage spikes = null;							// Textura za Spike
	public static BufferedImage torch = null;							// Textura za Torch
	public static BufferedImage healthPack = null;						// Textura za HealthPack
	
	public Textures() {	
		BufferedImageLoader loader = new BufferedImageLoader();
		
		// Ucitavanje velikih slika
		player = loader.loadImage("/player.png");
		blocks = loader.loadImage("/blocks.png");
		
		// Smestanje velikih slika u SpriteSheet 
		playerSheet = new SpriteSheet(player);
		blockSheet = new SpriteSheet(blocks);
		
		getTextures();
	}
	
	// Uzimanje malih(pojedinacnih) textura iz velikih slika
	private void getTextures() {
		blockTiles[0] = blockSheet.grabImage(1, 1, 32, 32);
		blockTiles[1] = blockSheet.grabImage(2, 1, 32, 32);
		blockTiles[2] = blockSheet.grabImage(3, 1, 32, 32);
		
		spikes = blockSheet.grabImage(1, 3, 32, 32);
		healthPack = blockSheet.grabImage(4, 1, 32, 32);
		lava = blockSheet.grabImage(6, 1, 32, 32);
		torch = blockSheet.grabImage(7, 1, 32, 32);
		
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
		
		batTiles[0] = blockSheet.grabImage(5, 4, 32, 32); // velX < 0
		batTiles[1] = blockSheet.grabImage(6, 4, 32, 32); 
		batTiles[2] = blockSheet.grabImage(7, 4, 32, 32); // velX > 0
		batTiles[3] = blockSheet.grabImage(8, 4, 32, 32); 
		
		bossTiles[0] = blockSheet.grabImage(1, 3, 64, 64);
		bossTiles[1] = blockSheet.grabImage(2, 3, 64, 64);
		
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
		
		fredyTiles[0] = playerSheet.grabImage(1, 4, 24, 48);
		fredyTiles[1] = playerSheet.grabImage(2, 4, 24, 48);
	}
}
