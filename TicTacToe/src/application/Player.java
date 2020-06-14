package application;

import java.util.concurrent.ThreadLocalRandom;

import processing.core.PApplet;

public class Player {
	public FieldState fieldState;
	protected PApplet parent;

	public Player(PApplet parent, FieldState fieldState) {
		this.fieldState = fieldState;
		this.parent = parent;
	}

	public void play(Game game) {
		int row = ThreadLocalRandom.current().nextInt(0, 3);
		int column = ThreadLocalRandom.current().nextInt(0, 3);
		while (!game.board.setField(row, column, fieldState)) {
			row = ThreadLocalRandom.current().nextInt(0, 3);
			column = ThreadLocalRandom.current().nextInt(0, 3);
		}
	}
}
