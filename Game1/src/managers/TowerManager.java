package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;

import static helpz.Constants.Towers.*;

import helpz.LoadSave;
import objects.Tower;
import scenes.Playing;

public class TowerManager {
	
	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;
	
	public TowerManager(Playing playing) {
		this.playing = playing;
		
		loadTowerImgs();
		
	}

	

	private void loadTowerImgs() {  //aici implem towerImgs cu sprite.uri
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		towerImgs = new BufferedImage[3];
		for(int i = 0; i < 3; i++) 
			towerImgs[i] = atlas.getSubimage( (4+i) * 32, 32, 32, 32);
	}
	
	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType())); 
		
	}
	
	public void update() {
		for(Tower t : towers) {
			t.update();
			attackEnemyIfClose(t);
		}	
	}
	
	private void attackEnemyIfClose(Tower t) {
		
			for(Enemy e : playing.getEnemyManager().getEnemies()) {
				if(e.isAlive())
					if(isEnemyInRange(t, e)) {
						if(t.isCooldownOver()) {
							playing.shootEnemy(t, e);
							t.resetCooldown();
						}	
					}
					else {
						//we do nothing
					}
			}
		
	}



	private boolean isEnemyInRange(Tower t, Enemy e) {
		
		int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		
		return range < t.getRange();   //range e distanta dintre tower si enemy, iar metoda returnneaza true daca distanta dintre ei este mai mica decat range.ul towerului
	}



	public void draw(Graphics g) {
		
		for(Tower t : towers) {
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
		}
		//g.drawImage(towerImgs[ARCHER], tower.getX(), tower.getY(), null);
	}
	
	public Tower getTowerAt(int x, int y) {
		
		for(Tower t : towers) 
			if(t.getX() == x)
				if(t.getY() == y)
					return t;
		return null;	
	}
	
	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}



	

	
}
