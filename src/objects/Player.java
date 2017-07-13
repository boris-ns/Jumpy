package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Animation;
import graphics.Textures;
import main.Game;
import sound.AudioPlayer;

public class Player extends GameObject {

	private final float MAX_VELY = 10;
	private float lastVelX, gravity = 1f;
	private int health, damage, coinsCollected;
	private int timer = 0, poisonedTimer;
	private boolean falling , jumping, isPoisoned;
	private Animation walkLeftAnim, walkRightAnim;
	
	public Player(float x, float y) {
		super(x, y, 0.0f, 0.0f, 24, 48, null);
		
		lastVelX = 1.0f;
		coinsCollected = 0;
		health = 100;
		damage = 25;
		falling = true;
		jumping = false;
		isPoisoned = false;
		poisonedTimer = 40;
		
		walkLeftAnim = new Animation(3, Textures.playerTiles[2], Textures.playerTiles[3], Textures.playerTiles[4],
				Textures.playerTiles[5], Textures.playerTiles[6], Textures.playerTiles[7], Textures.playerTiles[8], Textures.playerTiles[9]);
		walkRightAnim = new Animation(3, Textures.playerTiles[10], Textures.playerTiles[11], Textures.playerTiles[12],
				Textures.playerTiles[13], Textures.playerTiles[14], Textures.playerTiles[15], Textures.playerTiles[16], Textures.playerTiles[17]);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		// Gravitacija koja sluzi da odredi kretanje igraca tokom njegovog padanja
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MAX_VELY)
				velY = MAX_VELY;
		}
		
		// Pamcenje poslednje vrednosti velX da bi se znalo u kom smeru treba ispaljivati metke
		if(velX != 0)
			lastVelX = velX;
		
		// Kada igrac izgubi zivot, status igre se postavlja na gameOver i pusta se zvuk gameOver.mp3
		if(health <= 0) {
			AudioPlayer.play("GameOver");
			health = 0;
			Game.gameOver = true;
		}
		
		// Ukoliko je igrac sakupio vise od 25 novcica njegov damage se povecava
		if(coinsCollected >= 25) {
			coinsCollected -= 25;
			damage += 25;
			timer = 70;
			System.out.println("Damage: " + damage);
		}
		
		// Ukoliko se igrac otrovao pri dodiru sa Bat-om
		if(isPoisoned && --poisonedTimer == 0) {
			health -= 1;
			poisonedTimer = 40;
		}
		
		walkLeftAnim.runAnimation();
		walkRightAnim.runAnimation();
	}

	@Override
	public void render(Graphics g) {		
		// Ukoliko igrac stoji u mestu, iscrtavace se 1 slika u zavisnosti od poslednje vrednosti koju je imao velX
		if(velX == 0 && lastVelX > 0)
			g.drawImage(Textures.playerTiles[0], (int)x, (int)y, null);
		else if(velX == 0 && lastVelX < 0)
			g.drawImage(Textures.playerTiles[1], (int)x, (int)y, null);
		// Ukoliko je igrac u pokretnu u zavisnosti od smera kretanja izvrsava se odredjena animacija
		else if(velX > 0)
			walkLeftAnim.drawAnimation(g, (int)x, (int)y);
		else if(velX < 0)
			walkRightAnim.drawAnimation(g, (int)x, (int)y);
		
		// Sluzi za ispisivanje teksta "Damage increased" da bi igrac bio obavesten da mu je damage povecan
		if(timer-- >= 0) {
			g.setFont(new Font("Arial Black", 1, 15));
			g.setColor(Color.ORANGE);
			g.drawString("Damage", (int)x - 25, (int)y - 30);
			g.drawString("increased", (int)x - 35, (int)y - 10);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x + 5, (int)y, width - 10, 5);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int)x + 5, (int)y + height - 5, width - 10, 5);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, 5, height - 10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int)x + width - 5, (int)y + 5, 5, height - 10);
	}
	
	public float getLastVelX() {
		return lastVelX;
	}
	
	public boolean getFalling() {
		return falling;
	}
	
	public boolean getJumping() {
		return jumping;
	}
	
	public int getCoinsCollected() {
		return coinsCollected;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean getIsPoisoned() {
		return isPoisoned;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public void setCoinsCollected(int coinsCollected) {
		this.coinsCollected = coinsCollected;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setIsPoisoned(boolean isPoisoned) {
		this.isPoisoned = isPoisoned;
	}
}
