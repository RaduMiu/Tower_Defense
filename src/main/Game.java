package main;

import javax.imageio.ImageIO;
import javax.swing.*;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable{

    private GameScreen gameScreen;
    private Thread gameThread;
    
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    
    
   
    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;   
    
    private TileManager tileManager;
    public Game(){
    	
    	initClasses();
    	createDefaultLevel();
    	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);  
        add(gameScreen);
        pack();
        setVisible(true);
        
    }
    
    private void createDefaultLevel() {
		int[] arr = new int[400]; //20*20
		for(int i=0; i<arr.length; i++) //in java length nu are()
			arr[i] = 0;  //punem iarba
		
		LoadSave.CreateLevel("new_level", arr);
	}
    
    private void initClasses() {
    	tileManager = new TileManager();
    	render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);
	}

	
    
    
    
    private void start() {
    	gameThread = new Thread(this){};
    	gameThread.start();
    }
    
    
    
    

	private void updateGame() {
		switch(GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case SETTINGS:
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

        Game joc = new Game();
        joc.gameScreen.initInputs();
        joc.start();
        
        
        
        
    }
	
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
	    long lastFrame = System.nanoTime();
	    long lastUpdate = System.nanoTime();
	    long lastTimeCheck = System.currentTimeMillis();
	       
	    int frames = 0;
	    int updates = 0;
	    
	    long now; 
		
		while(true) {
			
			now = System.nanoTime();
			//Render
			if(now - lastFrame >= timePerFrame) {
	        	repaint();
	        	lastFrame = now;
	        	frames++;
	        	
	        }
			//Update
			if(now - lastUpdate >= timePerUpdate) {
    			updateGame();
    			lastUpdate = now;
    			updates++;	
    		}
			
			//checking FPS and UPS
			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: "+ frames + " UPS: "+ updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
	
	//Getters and setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	public Editing getEditor() {
		return editing;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}
	
	
}
