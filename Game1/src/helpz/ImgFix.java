package helpz;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgFix {
	
	//Rotate
	
	public static BufferedImage getRotImg(BufferedImage img, int rotAngle) { // cu metoda asta mai scapam din sprits de apa
		//rotatia se face in sens invers trigonometric
		int w = img.getWidth();
		int h = img.getHeight();
		
		BufferedImage newImg = new BufferedImage(w, h, img.getType());
		Graphics2D g2d = newImg.createGraphics();  // Graphics2D extends Graphics; Graphics2D allows to rotate and scale
		
		g2d.rotate(Math.toRadians(rotAngle), w/2, h/2); //w/2 si h/2 pt rotire din centru
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return newImg;
	}
	
	//Img layer build
	
	public static BufferedImage buildImg(BufferedImage[] imgs) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();
		
		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for(BufferedImage img : imgs) {
			g2d.drawImage(img, 0, 0, null);
		}
		g2d.dispose();
		
		return newImg;
	}
	
	
	//Rotate second img only
	public static BufferedImage getBuildRotImg(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
		int w = imgs[0].getWidth();
		int h = imgs[0].getHeight();
		
		BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
		Graphics2D g2d = newImg.createGraphics();
		
		for(int i=0; i<imgs.length; i++) {
			if(rotAtIndex == i)
				g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
			g2d.drawImage(imgs[i], 0, 0, null);
			
			if(rotAtIndex == i)
				g2d.rotate(Math.toRadians(-rotAngle), w/2, h/2);
		}
		
		g2d.dispose();
		
		return newImg;
	}
	
	//Rotate second img only + animation
		public static BufferedImage[] getBuildRotImg(BufferedImage[] imgs, BufferedImage secondImage, int rotAngle) {
			int w = imgs[0].getWidth();
			int h = imgs[0].getHeight();
			
			BufferedImage[] arr = new BufferedImage[imgs.length];
			
			for(int i=0; i<imgs.length; i++) {
				BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
				Graphics2D g2d = newImg.createGraphics();
				
				g2d.drawImage(imgs[i], 0, 0, null);
				g2d.rotate(Math.toRadians(rotAngle), w/2, h/2);
				g2d.drawImage(secondImage, 0, 0, null);
				g2d.dispose();
				
				arr[i] = newImg;
			}

			return arr;
		}
}












/* de pe cand de aici editam lv.ul
public static int[][] getLevelData(){
	
	//creates a 2D int array
	//where every value is a tile on the level
	int[][] lvl = {
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	};
	
	return lvl;
}
*/