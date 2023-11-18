package main;
//enum e o clasa speciala unde stocam constante; in functie de ce constanta este activa vom desena o scena diferita

public enum GameStates {
	PLAYING,
	MENU,
	SETTINGS,
	EDIT;
	
	public static GameStates gameState = MENU;
	
	public static void SetGameState(GameStates state) {
		gameState = state;
	}
}
