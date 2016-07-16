package main;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.Hud;
import graphics.Textures;
import graphics.Window;
import handlers.BatHandler;
import handlers.BlockHandler;
import handlers.BossBulletsHandler;
import handlers.BulletHandler;
import handlers.CoinsHandler;
import handlers.CollisionHandler;
import handlers.EnemiesHandler;
import handlers.HealthPackHandler;
import handlers.LavaHandler;
import handlers.RenderBlocksHandler;
import handlers.SmartWallHandler;
import handlers.SpikeHandler;
import input.KeyInput;
import objects.Bat;
import objects.Block;
import objects.Boss;
import objects.Camera;
import objects.Coin;
import objects.Enemy;
import objects.HealthPack;
import objects.Lava;
import objects.MovingBlock;
import objects.Player;
import objects.SmartWall;
import objects.Spike;
import sound.AudioPlayer;

public class Game extends Canvas implements Runnable
{
	// Polja klase
	public static final int width = 646, height = 480;
	public static final int tileSize = 32;
	public static boolean paused = false, gameOver = false, gameFinished = false, restart = false;
	public static int level;
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	private BufferedImageLoader loader;
	private KeyInput keyInput;
	private Player player;
	private Boss boss;
	private BlockHandler blockHandler;
	private RenderBlocksHandler rBlocksHandler;
	private CoinsHandler coinsHandler;
	private CollisionHandler collisionHandler;
	private BulletHandler bHandler;
	private BossBulletsHandler bossBHandler;
	private SpikeHandler spikeHandler;
	private EnemiesHandler enemiesHandler;
	private SmartWallHandler smartWallHandler;
	private HealthPackHandler hpHandler;
	private BatHandler batHandler;
	private LavaHandler lHandler;
	private BufferedImage levelImg = null, backgroundTile;
	private Camera camera;
	private Textures textures = new Textures();
	private Hud hud;
	private PauseScreen pScreen;
	private AudioPlayer bgMusic; // background music
	private int timer = 150;
	
	// Konstruktor
	public Game()
	{
		level = 1;
		
		System.out.println("Konstruktor !");
		
		new Window(width, height, "Jumpy", this);		
		
		loader = new BufferedImageLoader();		
		keyInput = new KeyInput();
		addKeyListener(keyInput);	
			
		//textures = new Textures();	
		pScreen = new PauseScreen();
		hud = new Hud();		
		bgMusic = new AudioPlayer("/bgMusic.mp3");	
		bgMusic.loopPlay(-20.0f);
		
		backgroundTile = textures.blockTiles[1];
	}
	
	private void init()
	{		
		if(level == 1)
		{
			player = new Player(70, 1450);	// Pozicija igraca na pocetnoj poziciji level1
			//player = new Player(55 * 32, 80 * 32);	// Pozicija igraca na poziciji pred Boss fight
			//player = new Player(45 * 32, 60*32);			
			bossBHandler = new BossBulletsHandler();
			boss = new Boss(44 * 32, 90 * 32, textures, bossBHandler);
			
			loadResObjects();
			levelImg = loader.loadImage("/level1.png");
		}
		else if(level == 2)
		{
			player = new Player(2 * 32, 12 * 32); // Pozicija igraca za pocetak level2
			//player = new Player(56 * 32, 26 * 32); // Pozicija igraca pred lavirint u level2
			//player = new Player(36 * 32, 72 * 32); // Pozicija igraca pred Boss fight u level2
			//player = new Player(56 * 32, 92 * 32);
			bossBHandler = new BossBulletsHandler();
			boss = new Boss(23 * 32, 82 * 32, textures, bossBHandler);
			
			loadResObjects();
			levelImg = loader.loadImage("/level2.png");
		}
		
		loadImageLevel(levelImg);
	}
	
	private void loadResObjects()
	{
		camera = new Camera(0, 0);
		blockHandler = new BlockHandler();
		rBlocksHandler = new RenderBlocksHandler();
		coinsHandler = new CoinsHandler();
		bHandler = new BulletHandler();
		spikeHandler = new SpikeHandler();
		enemiesHandler = new EnemiesHandler();
		smartWallHandler = new SmartWallHandler();
		hpHandler = new HealthPackHandler();
		batHandler = new BatHandler();
		lHandler = new LavaHandler();
		collisionHandler = new CollisionHandler(player, boss, blockHandler, coinsHandler, bHandler, bossBHandler, 
				spikeHandler, enemiesHandler, smartWallHandler, hpHandler, batHandler);
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
		{
			pScreen.tick();
			
			if(restart)
			{
				init();
				System.out.println("restart " + restart);
				restart = false;
				paused = false;
			}
		}
		else if(gameOver || gameFinished)
		{
			if(timer-- == 0)
			{		
				//freeLists();
				init();		
				gameOver = false;
				gameFinished = false;
				timer = 150;
			}
		}
		else if(!paused)
		{	
			keyInput.tick(player, bHandler);
			player.tick();
			blockHandler.tick();
			boss.tick((int)player.getX(), (int)player.getY());
			camera.tick(player);			
			hud.tick(player.getCoinsCollected(), player.getHealth());
			coinsHandler.tick();
			collisionHandler.tick();
			bHandler.tick();
			bossBHandler.tick();
			enemiesHandler.tick();
			hpHandler.tick();
			batHandler.tick();
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
		
		for(int i = 0; i < width; i += 32)
			for(int j = 0; j < height; j += 32)
				g.drawImage(backgroundTile, i, j, null);
		
		g2d.translate(camera.getX(), camera.getY());
		
		rBlocksHandler.render(g);
		coinsHandler.render(g);
		bHandler.render(g);
		bossBHandler.render(g);
		blockHandler.render(g);
		smartWallHandler.render(g);
		hpHandler.render(g);
		enemiesHandler.render(g);
		boss.render(g);
		player.render(g);
		spikeHandler.render(g);
		hpHandler.render(g);
		batHandler.render(g);
		lHandler.render(g);	
		
		g2d.translate(-camera.getX(), -camera.getY());	
		
		// Lamp effect
		if(level == 2)
		{
			Point2D center = new Point2D.Float((int)(camera.getX() + player.getX() - player.getWidth()), (int)(camera.getY() + player.getY() - player.getHeight() / 2));
			float radius = 200.0f;
			float[] dist = {0.0f, 1.0f};
			Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.black};
			RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
			g2d.setPaint(p);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));
			g2d.fillRect(0, 0, width, height);
		}
		
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
		
		if(boss.getHealth() <= 0 && level == 1)
		{
			g.setFont(new Font("Arial Black", 1, 40));
			g.drawString("CONGRATULATIONS", 80, 150);
		}
		
		g2d.dispose();
		g.dispose();
		bs.show();
	}
	
	// Metoda koja uzima sliku levela i pixele pretvara u stvarne blokove na osnovu njihovih boja
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
				else if(red == 180 && green == 180 && blue == 180)
					rBlocksHandler.blocks.add(new Block(i * tileSize, j * tileSize, textures));	
				else if(red == 255 && green == 255 && blue == 0)
					coinsHandler.coins.add(new Coin(i * tileSize, j * tileSize, textures));			
				else if(red == 255 && green == 0 && blue == 0)
					spikeHandler.spikes.add(new Spike(i * tileSize, j * tileSize, textures));			
				else if(red == 255 && green == 0 && blue == 255)
					enemiesHandler.enemies.add(new Enemy(i * tileSize, j * tileSize, 1, textures));				
				else if(red == 255 && green == 200 && blue == 255)
					enemiesHandler.enemies.add(new Enemy(i * tileSize, j * tileSize, 2, textures));			
				else if(red == 0 && green == 255 && blue == 255)
					smartWallHandler.smartWalls.add(new SmartWall(i * tileSize, j * tileSize, textures));
				else if(red == 0 && green == 0 && blue == 255)
					hpHandler.healthPack.add(new HealthPack(i * tileSize, j * tileSize, textures));
				else if(red == 255 && green == 128 && blue == 0)
					batHandler.bats.add(new Bat(i * tileSize, j * tileSize, 1, textures));
				else if(red == 255 && green == 128 && blue == 90)
					batHandler.bats.add(new Bat(i * tileSize, j * tileSize, 2, textures));
				else if(red == 0 && green == 161 && blue == 255)
					lHandler.lava.add(new Lava(i * tileSize, j * tileSize, textures));
				else if(red == 5 && green == 90 && blue == 255)
				{
					lHandler.lava.add(new Lava(i * tileSize, j * tileSize, textures));
					spikeHandler.spikes.add(new Spike(i * tileSize, j * tileSize, textures));
				}
				else if(red == 100 && green == 100 && blue == 100)
					blockHandler.blocks.add(new MovingBlock(i * tileSize, j * tileSize, textures));
			}
		}
	}
	
	// Metoda koja pravi novi Thread za igru
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	// Metoda koja zaustavlja Thread namenjen za igru
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
