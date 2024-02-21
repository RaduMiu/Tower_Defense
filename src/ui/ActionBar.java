package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import helpz.Constants.Towers;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar{
	
	
	private Playing playing;
	private MyButton bMenu;
	
	private MyButton[] towerButtons;
	private Tower selectedTower; //for placing a tower
	private Tower displayedTower; //for displaying a tower
	
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x,y,width, height); //super apeleaza construsctorul clasei de baza
		this.playing = playing;
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		towerButtons = new MyButton[3];
		
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int)(w*1.1f);  //spatiul dintre ele
		
		for(int i=0; i< towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
		}
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		
		for(MyButton b : towerButtons) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			
			drawButtonFeedback(g, b);
		}
	}
	
	
	public void draws(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);
		
		//DisplayedTower
		drawDisplayedTower(g);
	}
	
	private void drawDisplayedTower(Graphics g) {   //desenam towerul selectat in bara din playing
		if(displayedTower != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85); //primele doua coordonatele x si y urmatoarele latime si inaltime
			g.drawRect(420, 650, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 490, 660);
			g.drawString("ID: " + displayedTower.getId(), 490, 675);
			
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);
		}
		
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.white);
		//g.drawOval(displayedTower.getX() - 50 + 16, displayedTower.getY() - 50 + 16, 100, 100); // scazand cu 50 cercul o sa aiba centrul in punctul stanga sus al tower.ului, iar -16 este pt al aduce in centrul towerului
		g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange() * 2) / 2,
				displayedTower.getY()+ 16 - (int)(displayedTower.getRange() * 2) / 2, (int) displayedTower.getRange() * 2, (int) displayedTower.getRange() * 2);
	}

	private void drawDisplayedTowerBorder(Graphics g) {   //face contur pe tower.ul selectat
		g.setColor(Color.CYAN);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
		
	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}
	
	public void mouseClicked(int x, int y) { //clicked face o actiune, pressed e doar actiunea vizuala
		if(bMenu.getBounds().contains(x,y))
			SetGameState(MENU);
		else {
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					selectedTower = new Tower(0, 0, -1, b.getId());  //-1 este id.ul iar ultima e tower type.ul
					playing.setSelectedTower(selectedTower);
					return;
				}
			}
		}
	}

	
	

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		for(MyButton b : towerButtons)
			b.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x,y))
			bMenu.setMouseOver(true);
		else 
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
				
			}
		
	}

	
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x,y))
			bMenu.setMousePressed(true);
		else
		for(MyButton b : towerButtons) {
			if(b.getBounds().contains(x, y)) {
				b.setMousePressed(true);
				return;
			}	
		}
	}

	
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for(MyButton b : towerButtons)
			b.resetBooleans();
	}
}
