package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import handlers.BlockHandler;
import handlers.CollisionHandler;
import graphics.Window;
import input.KeyInput;
import objects.Player;

public class Game extends Canvas implements Runnable
{
	public static final int width = 640, height = 480;
	
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	private KeyInput keyInput;
	private Player player;
	private BlockHandler blockHandler;
	private CollisionHandler collisionHandler;

	
	public Game()
	{
		new Window(width, height, "Jumpy", this);
		
		player = new Player(20, 20);
		keyInput = new KeyInput(player);
		this.addKeyListener(keyInput);
		blockHandler = new BlockHandler();
		collisionHandler = new CollisionHandler(player, blockHandler);
	}
	
	public void tick()
	{
		keyInput.tick();
		
		player.tick();
		
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
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		player.render(g);
		blockHandler.render(g);
		
		g.dispose();
		bs.show();
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
