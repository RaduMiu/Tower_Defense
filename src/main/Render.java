package main;
//este responsabilă pentru a dirija procesul de randare grafică 
//în funcție de starea curentă a jocului. Aceasta asigură că 
//conținutul grafic adecvat este afișat în timpul jocului și că se 
//schimbă corespunzător când starea jocului se modifică
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Render {
	
	private Game game;
	
	public Render(Game game) {
		this.game = game;
		
		
	}
	
	
	public void render(Graphics g) {
		switch(GameStates.gameState) {   //in fuctie de state vom avea o scena diferita
			
			case MENU:
				game.getMenu().render(g);
				
				break;
			case PLAYING:
				
				game.getPlaying().render(g);
				
				break;
			case SETTINGS:
				
				game.getSettings().render(g);
				
				break;
			case EDIT:
				
				game.getEditor().render(g);
				
				break;	
		}
	}
	
}
