package application;
import processing.core.PApplet;

public class EvolvingPlayer extends Player {
	  private Brain brain;

	  public EvolvingPlayer (PApplet parent, FieldState fieldState) {
	    super(parent, fieldState);
	    brain = new Brain(this, 5, 27, 9, 5);
	  }

	  public void play(Game game) {
	    int [] decision = brain.think (game);
	    int row = decision[0];
	    int column = decision[1];
	    int counter = 0;
	    while (!game.board.setField(row, column, fieldState)) {
	      punish(game);
	      decision = brain.think (game);
	      row = decision[0];
	      column = decision[1];
	      counter++;
	    }
	    //System.out.println(counter);
	    reward(game);
	  }

	  public void reward(Game game) {
	    brain.reward(game);
	  }


	  public void punish(Game game) {
	    brain.punish(game);
	  }
	}
