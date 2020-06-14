package application;
import processing.core.PApplet;
import processing.core.PConstants;


public class HumanPlayer extends Player {
	  private boolean inTurn;
	  public HumanPlayer (PApplet parent, FieldState fieldState) {
	    super(parent, fieldState);
	  }

	  public void play(Game game) {
	    inTurn = true;
	    while (inTurn) {
	      synchronized(this) {
	        if (parent.mousePressed && parent.mouseButton==PConstants.LEFT) {
	          //System.out.print("MousePressed");
	          for (int i = 0; i<3; i++) {
	            for (int j = 0; j<3; j++) {
	              if (game.board.fields[i][j].mouseInField()) {
	                if (game.board.setField(game.board.fields[i][j].row, game.board.fields[i][j].column, fieldState)) {
	                  inTurn = false;
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	  }
	}
