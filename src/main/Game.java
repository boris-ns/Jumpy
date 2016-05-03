package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.Hud;
import graphics.Textures;
import graphics.Window;
import handlers.BlockHandler;
import handlers.BossBulletsHandler;
import handlers.BulletHandler;
import handlers.CoinsHandler;
import handlers.CollisionHandler;
import handlers.EnemiesHandler;
import handlers.SmartWallHandler;
import handlers.SpikeHandler;
import input.KeyInput;
import objects.Block;
import objects.Boss;
import objects.Camera;
import objects.Coin;
import objects.Enemy;
import objects.Player;
import objects.SmartWall;
import objects.Spike;

public class Game extends Canvas implements Runnable
{
	public static final int width = 646, height = 480;
	public static final int tileSize = 32;
	public static boolean paused = false, gameOver = false, gameFinished = false;
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	private BufferedImageLoader loader;
	private KeyInput keyInput;
	private Player player;
	private Boss boss;
	private BlockHandler blockHandler;
	private CoinsHandler coinsHandler;
	private CollisionHandler collisionHandler;
	private BulletHandler bHandler;
	private BossBulletsHandler bossBHandler;
	private SpikeHandler spikeHandler;
	private EnemiesHandler enemiesHandler;
	private SmartWallHandler smartWallHandler;
	private BufferedImage level1 = null, backgroundTile;
	private Camera camera;
	private Textures textures = new Textures();
	private Hud hud;
	private PauseScreen pScreen;
	private int timer = 70;
	
	
	public Game()
	{
		System.out.println("Konstruktor !");
		
		new Window(width, height, "Jumpy", this);		
		
		keyInput = new KeyInput();
		addKeyListener(keyInput);	
			
		//textures = new Textures();	
		pScreen = new PauseScreen();
		hud = new Hud();		
			
		backgroundTile = textures.blockTiles[1];
	}
	
	private void init()
	{									
		//player = new Player(70, 1250);
		player = new Player(55 * 32, 74 * 32);
		camera = new Camera(0, 0);
		blockHandler = new BlockHandler();
		coinsHandler = new CoinsHandler();
		bHandler = new BulletHandler();
		bossBHandler = new BossBulletsHandler();
		boss = new Boss(44 * 32, 85 * 32, textures, bossBHandler);
		spikeHandler = new SpikeHandler();
		enemiesHandler = new EnemiesHandler();
		smartWallHandler = new SmartWallHandler();
		collisionHandler = new CollisionHandler(player, boss, blockHandler, coinsHandler, bHandler, bossBHandler, 
				spikeHandler, enemiesHandler, smartWallHandler);

		loader = new BufferedImageLoader();		
		level1 = loader.loadImage("/level1.png");
		loadImageLevel(level1);			
	}
	
//	private void initAfterGameOver()
//	{
//		player.setX(70);
//		player.setY(1250);
//		player.setHealth(100);
//		player.setCoinsCollected(0);
//		
//		coinsHandler.coins.removeAll(coinsHandler.coins);
//		bHandler.bullets.removeAll(bHandler.bullets);
//		enemiesHandler.enemies.removeAll(enemiesHandler.enemies);
//		smartWallHandler.smartWalls.removeAll(smartWallHandler.smartWalls);
//
//		loadImageLevel(level1);	
//		
//		//collisionHandler = new CollisionHandler(player, blockHandler, coinsHandler, bHandler, spikeHandler,
//			//	enemiesHandler, smartWallHandler);
//	}
	
//	private void freeLists()
//	{
//		coinsHandler.coins.removeAll(coinsHandler.coins);
//		bHandler.bullets.removeAll(bHandler.bullets);
//		enemiesHandler.enemies.removeAll(enemiesHandler.enemies);
//		smartWallHandler.smartWalls.removeAll(smartWallHandler.smartWalls);
//	}
//			
	private void tick()
	{	
		if(paused)
			pScreen.tick();
		else if(gameOver || gameFinished)
		{
			if(timer-- == 0)
			{		
				//freeLists();
				init();		
				gameOver = false;
				gameFinished = false;
				timer = 70;
			}
		}
		else if(!paused)
		{	
			keyInput.tick(player, bHandler);
			player.tick();
			boss.tick((int)player.getX(), (int)player.getY());
			camera.tick(player);			
			hud.tick(player.getCoinsCollected(), player.getHealth());
			coinsHandler.tick();
			collisionHandler.tick();
			bHandler.tick();
			bossBHandler.tick();
			enemiesHandler.tick();
		}
	
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		for(int i = 0; i < width; i += 32)
			for(int j = 0; j < height; j += 32)
				g.drawImage(backgroundTile, i, j, null);
		
		g2d.translate(camera.getX(), camera.getY());
		
		player.render(g);
		blockHandler.render(g);
		boss.render(g);
		coinsHandler.render(g);
		bHandler.render(g);
		bossBHandler.render(g);
		spikeHandler.render(g);
		enemiesHandler.render(g);
		smartWallHandler.render(g);
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		if(paused)
			pScreen.render(g);
		else
			hud.render(g);
		
		if(gameOver)
		{
			g.setFont(new Font("Arial Black", 1, 32));
			g.setColor(Color.ORANGE);
			g.drawString("GAME OVER", 200, 150);
		}
		
		if(boss.getHealth() <= 0)
		{
			g.setFont(new Font("Arial Black", 1, 40));
			g.drawString("CONGRATULATIONS", 80, 150);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void loadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Width: " + w + " Height: " + h);
		
		int pixel = 0, red = 0, green = 0, blue = 0;
		
		for(int i = 0; i < h; i++)
		{
			for(int j = 0; j < w; j++)
			{
				pixel = image.getRGB(i, j);
				red = (pixel >> 16) & 0xff;
				green = (pixel >> 8) & 0xff;
				blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) 
					blockHandler.blocks.add(new Block(i * tileSize, j * tileSize, textures));
				
				if(red == 255 && green == 255 && blue == 0)
					coinsHandler.coins.add(new Coin(i * tileSize, j * tileSize, textures));
			
				if(red == 255 && green == 0 && blue == 0)
					spikeHandler.spikes.add(new Spike(i * tileSize, j * tileSize, textures));
			
				if(red == 255 && green == 0 && blue == 255)
					enemiesHandler.enemies.add(new Enemy(i * tileSize, j * tileSize, 1, textures));
				
				if(red == 255 && green == 200 && blue == 255)
					enemiesHandler.enemies.add(new Enemy(i * tileSize, j * tileSize, 2, textures));
			
				if(red == 0 && green == 255 && blue == 255)
					smartWallHandler.smartWalls.add(new SmartWall(i * tileSize, j * tileSize, textures));
			}
		}
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		try 
		{
			thread.join();
			running = false;
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() 
	{	
		System.out.println("RUN !");

		init();
		requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			if(running)
				render();
		
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public static void main(String[] args)
	{
		new Game();
	}
}