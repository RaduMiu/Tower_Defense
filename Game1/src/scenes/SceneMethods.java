package scenes;

import java.awt.Graphics;

public interface SceneMethods {    //pt a decide ce tip de metode au nevoie toate scenele
	
	public void render(Graphics g);
	public void mouseClicked(int x, int y);
	public void mouseMoved(int x, int y);
	public void mousePressed(int x, int y);
	public void mouseReleased(int x, int y);
	public void mouseDragged(int x, int y);
}
