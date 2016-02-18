package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import graphics.BufferedImageLoader;
import graphics.Window;
import handlers.BlockHandler;
import handlers.CollisionHandler;
import input.KeyInput;
import objects.Block;
import objects.Camera;
import objects.Player;

public class Game extends Canvas implements Runnable
{
	public static final int width = 650, height = 480;
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	private KeyInput keyInput;
	private Player player;
	private BlockHandler blockHandler;
	private CollisionHandler collisionHandler;
	private BufferedImage level1 = null;
	private Camera camera;

	
	public Game()
	{
		new Window(width, height, "Jumpy", this);

		player = new Player(width / 2, 1200);
		camera = new Camera(0, 0);
		blockHandler = new BlockHandler();		
		keyInput = new KeyInput(player);
		this.addKeyListener(keyInput);
		collisionHandler = new CollisionHandler(player, blockHandler);
	}
	
	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/level1.png");
		loadImageLevel(level1);
	}
	
	public void tick()
	{
		keyInput.tick();
		
		player.tick();
		camera.tick(player);
		
		collisionHandler.tick();
	}
	
	public void render()
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
		
		g2d.translate(camera.getX(), camera.getY());
		
		player.render(g);
		blockHandler.render(g);
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		g.dispose();
		bs.show();
	}
	
	public void loadImageLevel(BufferedImage image)
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
					blockHandler.blocks.add(new Block(i * 32, j * 32));
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
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " UPDATES: " + updates);
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
