package scenes;

import java.awt.image.BufferedImage;

import main.Game;

//clasa parinta pt scene
public class GameScene {
		
	protected Game game;
	protected int animationIndex;
	protected int tick;    //when tick reaches out set animation speed, it increases the animation index by 1 and goes back to 0
	
	protected GameScene(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	
	protected void updateTick() {
		tick++;
		if(tick >= 25) {  //higher the number slower the animation
			tick = 0;
			animationIndex++;
			if(animationIndex >= 4)
				animationIndex = 0;
		}
		
	}
	
	protected BufferedImage getSprite(int spriteID) {
		return getGame().getTileManager().getSprite(spriteID);
	}
	
	protected BufferedImage getSprite(int spriteID, int animationIndex) {
		return getGame().getTileManager().getAniSprite(spriteID, animationIndex);
	}
	
}
