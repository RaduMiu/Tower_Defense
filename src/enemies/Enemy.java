package enemies;

import java.awt.Rectangle;
import static helpz.Constants.Direction.*;

public abstract class Enemy { //abstract bcz we dont want to create an enemy from the super class 
	protected float x, y;  //folosim float pt a putea avea jumatati de pixeli
	private Rectangle bounds;
	private int health;
	private int maxHealth;
	private int ID;
	private int enemyType;
	private int lastDir;
	protected boolean alive = true;
	protected int slowTickLimit = 60*2;	//60 de la UPS:60	inmultit cu cate secunde vrem sa aiba efect slow-ul
	protected int slowTick = slowTickLimit;
	
	public Enemy(float x, float y, int ID, int enemyType) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		bounds = new Rectangle ((int) x, (int) y, 32, 32);
		lastDir = -1;
		setStartHealth();
	}
	
	private void setStartHealth() {
		health = helpz.Constants.Enemies.GetStartHealth(enemyType);
		maxHealth = health;
	}
	
	public void hurt(int dmg) {
		this.health -= dmg;
		if(health <= 0)
			alive = false;
	}
	
	public void slow() {
		slowTick = 0;
	}
	
	public void move(float speed, int dir) {
		lastDir = dir;
		
		if(slowTick < slowTickLimit) {		
			slowTick++;
			speed *= 0.5f;
		}
		
		switch(dir) {
		case LEFT:
			this.x -= speed; 
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		}
		
		updateHitbox();
	}
	
	private void updateHitbox() {
		bounds.x = (int)x;
		bounds.y = (int)y;
		
	}

	public void setPos(int x, int y) {
		//Dont use this one for move, this is for pos fix
		this.x = x;
		this.y = y;
	}
	
	public float getHealthBarFloat() {    //numarul din care va rezulta lungimea barei de hp
		return health / (float) maxHealth;
	}
	
	
	public float getX() {
		return x;
	}


	public float getY() {
		return y;
	}


	public Rectangle getBounds() {
		return bounds;
	}


	public int getHealth() {
		return health;
	}


	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}	
	
	public int getLastDir() {
		return lastDir;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}
}
