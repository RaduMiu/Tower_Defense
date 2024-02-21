package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import static helpz.Constants.Towers.*;
import static helpz.Constants.Projectiles.*;

public class ProjectileManager {
	
	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private BufferedImage[] proj_imgs;
	private int proj_id = 0; //everytime create a new proj, it give an id+1
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		importImgs();
	}
	
	private void importImgs() {
		// x 7 8 9   y 1
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		proj_imgs = new BufferedImage[3];
		for(int i=0; i<3; i++)
			proj_imgs[i] = atlas.getSubimage((7 + i) * 32, 32, 32, 32);
	}
	
	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);
		
		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);
		
		float xPercent = (float) Math.abs(xDist) / totDist;
		
		float xSpeed = xPercent * helpz.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helpz.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		
		if(t.getX() > e.getX())
			xSpeed *= -1;
		if(t.getY() > e.getY())
			ySpeed *= -1;
		
		float rotate = 0;
		
		if(type == ARROW) {
			float arcValue = (float) Math.atan(yDist / (float) xDist);
			rotate = (float) Math.toDegrees(arcValue);
			
			if(xDist < 0)
				rotate += 180; 
		}
		 
		projectiles.add(new Projectile(t.getX()+16, t.getY()+16, xSpeed, ySpeed, t.getDmg(), rotate, proj_id++, type));
	}
	
	private int getProjType(Tower t) {
		switch(t.getTowerType()) {
		case ARCHER:
			return ARROW;
		case CANNON:
			return BOMB;
		case WIZARD:
			return CHAINS;
		}
		return 0;
	}

	public void update() {
		for(Projectile p : projectiles)
			if(p.isActive()) {
				p.move();
				if(isProjHittingEnemy(p)) {
					p.setActive(false);
				}
				else {
					//we do nothing
				}
			}
	}
	
	private boolean isProjHittingEnemy(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive())
				if(e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					if(p.getProjectileType() == CHAINS)
						e.slow();
					
					return true;
				}
		}
		return false;
	}

	public void draw(Graphics g) {
		//in Graphics nu exista rotire asa ca folosim Graphics2D facand un cast lui g
		Graphics2D g2d = (Graphics2D) g;
		
		for(Projectile p : projectiles)
			if(p.isActive()) {
				if(p.getProjectileType() == ARROW) {
					g2d.translate(p.getPos().x, p.getPos().y);	//unde vrem sa fie centrul grilei
					g2d.rotate(Math.toRadians(p.getRotation()));
					g2d.drawImage(proj_imgs[p.getProjectileType()], -16, -16, null);
					g2d.rotate(-Math.toRadians(p.getRotation()));
					g2d.translate(-p.getPos().x, -p.getPos().y);
				}else {
					g2d.drawImage(proj_imgs[p.getProjectileType()], (int)p.getPos().x-16, (int)p.getPos().y-16, null);	//-16 pt ca bomba nu parea ca iese din centrul tunului
				}	
			}
	}
}
