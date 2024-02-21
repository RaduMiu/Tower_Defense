package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel {

    
    private Game game;
    private Dimension size;
    
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener; 
    
    public GameScreen(Game game){
        this.game = game;
        
        
        setPanelSize();
        

    }
    
    public void initInputs() {
    	myMouseListener = new MyMouseListener(game);
    	keyboardListener = new KeyboardListener(game);
    	
    	addMouseListener(myMouseListener);
    	addMouseMotionListener(myMouseListener);
    	addKeyListener(keyboardListener);
    	
    	requestFocus(); // focus pe componentele JFrame si JPanel(nu sunt multe 2, dar folosim request focus sa fim siguri ca nu avem bugguri)
    }

    
    
    private void setPanelSize() {
    	size = new Dimension(640, 800);
    	
    	setMinimumSize(size);
    	setPreferredSize(size);
    	setMaximumSize(size);

	}


	public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        game.getRender().render(g);
       
        //g.drawImage(img.getSubimage(32*9, 32*1, 32, 32), 0, 0, null);

        //g.drawRect(50 , 50, 100, 100); face doar conturul
        //g.fillRect(0,0, 32,32); //face un patratel

        
    }
	

    
}
