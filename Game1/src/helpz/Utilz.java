package helpz;

import java.util.ArrayList;

public class Utilz {
	
	//din 1D array to 2D array
	public static int[][] ArrayListT2Dint(ArrayList<Integer> list, int ySize, int xSize){
		int[][] newArr = new int[ySize][xSize];
		
		
		for(int j = 0; j<newArr.length; j++) {
			for(int i=0; i<newArr[j].length; i++) {
				
				int index = j*ySize + i;
				newArr[j][i] = list.get(index);
			}
		}
		
		return newArr;
	}
	
	//din 2D array to 1D array
	public static int[] TwoDto1DintArr(int[][] twoArr) {
		int[] oneArr = new int[twoArr.length * twoArr[0].length];
		
		for(int j = 0; j<twoArr.length; j++) {
			for(int i=0; i<twoArr[j].length; i++) {
				
				int index = j*twoArr.length + i;
				oneArr[index] = twoArr[j][i];
			}
		}
		return oneArr;
	}
	
	//pt a afla distanta dintre enemy si tower
	public static int GetHypoDistance(float x1, float y1, float x2, float y2) {  //aici aplicam teorema lui Pitagora
		float xDiff = Math.abs(x1 - x2);  //cele 2 catete
		float yDiff = Math.abs(y1 - y2);  //
		
		return (int) Math.hypot(xDiff, yDiff); //meotda in sine returneaza un double si a trebuit sa.o facem cast de int
	}
	
}


