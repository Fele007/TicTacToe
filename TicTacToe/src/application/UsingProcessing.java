package application;

import processing.core.*;

public class UsingProcessing extends PApplet {

	// The argument passed to main must match the class name
	public static void main(String[] args) {
		PApplet.main("application.UsingProcessing");
	}

	// method for setting the size of the window
	public void settings() {
		size(640, 640);
	}

	GameHandler gameHandler;

	// identical use to setup in Processing IDE except for size()
	public void setup() {
		background(0);
		// frameRate(Integer.MAX_VALUE);
		frameRate(60);

		gameHandler = new GameHandler();
		Thread gameHandlerThread = new Thread(gameHandler);

		for (int i = 0; i < 10000; i++) {
			if (gameHandler.winningPlayers.size() > 0) {
				gameHandler.addGame(new Game(new Board(this, 0, 0, 200),
						gameHandler.winningPlayers.get(gameHandler.winningPlayers.size() - 1),
						new Player(this, FieldState.O)));
			} else
				gameHandler.addGame(new Game(new Board(this, 0, 0, 200), new EvolvingPlayer(this, FieldState.X),
						new Player(this, FieldState.O)));
			gameHandlerThread.start();
			try {
				gameHandlerThread.join();
			} catch (Exception e) {
			}
			gameHandlerThread = new Thread(gameHandler);
		}

		EvolvingPlayer winner = gameHandler.winningPlayers.get(gameHandler.winningPlayers.size() - 1);
		gameHandler = new GameHandler();
		gameHandlerThread = new Thread(gameHandler);
		for (int i = 0; i < 10000; i++) {
			if (gameHandler.winningPlayers.size() > 0) {
				gameHandler.addGame(new Game(new Board(this, 0, 0, 200),
						gameHandler.winningPlayers.get(gameHandler.winningPlayers.size() - 1),
						new Player(this, FieldState.O)));
			} else
				gameHandler.addGame(new Game(new Board(this, 0, 0, 200), winner, new Player(this, FieldState.O)));
			gameHandlerThread.start();
			try {
				gameHandlerThread.join();
			} catch (Exception e) {
			}
			gameHandlerThread = new Thread(gameHandler);
		}
		gameHandler.printPlayerStats();
		gameHandler.addGame(new Game(new Board(this, 0, 0, 200),
				gameHandler.winningPlayers.get(gameHandler.winningPlayers.size() - 1),
				new HumanPlayer(this, FieldState.O)));
		gameHandlerThread.start();
	}

	public void draw() {
		gameHandler.drawCurrentGame();
	}
}