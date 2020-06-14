package application;
import processing.core.PApplet;

public class Board {
	  public Field [][] fields;
	  private int x, y, fieldSize, fieldsPlayed;
	  private PApplet parent;

	  public Board(PApplet parent, int x, int y, int fieldSize) {
	    this.fieldSize = fieldSize;
	    this.x = x;
	    this.y = y;
	    this.parent = parent;
	    // Init Fields
	    fields = new Field [3][3];
	    for (int i = 0; i<3; i++) {
	      for (int j = 0; j<3; j++) {
	        fields[i][j] = new Field(parent, x+j*fieldSize, y+i*fieldSize, fieldSize, i, j);
	      }
	    }
	  }

	  public boolean setField (int row, int column, FieldState fieldState) {
	    if (fields[row][column].fieldState == fieldState.Empty) {
	      fields[row][column].fieldState = fieldState;
	      fieldsPlayed++;
	      return true;
	    } else return false;
	  }

	  public FieldState finished() {
	    // First five rounds
	    if (fieldsPlayed < 5) return null; 
	    // Check Rows
	    for (int i = 0; i<3; i++) {
	      if (fields[i][0].fieldState != FieldState.Empty & fields[i][0].fieldState==fields[i][1].fieldState && fields[i][0].fieldState==fields[i][2].fieldState) {
	        return fields[i][0].fieldState;
	      }
	    }
	    // Check Columns
	    for (int i = 0; i<3; i++) {
	      if (fields[0][i].fieldState != FieldState.Empty & fields[0][i].fieldState==fields[1][i].fieldState && fields[0][i].fieldState==fields[2][i].fieldState) {
	        return fields[0][i].fieldState;
	      }
	    }
	    // Check Diagonal
	    if (fields[0][0].fieldState != FieldState.Empty & fields[0][0].fieldState==fields[1][1].fieldState && fields[0][0].fieldState==fields[2][2].fieldState ||
	          fields[2][0].fieldState != FieldState.Empty & fields[2][0].fieldState==fields[1][1].fieldState && fields[2][0].fieldState==fields[0][2].fieldState) {
	      return fields[1][1].fieldState;
	    }
	    if (fieldsPlayed == 9) return FieldState.Empty;
	    else return null;
	  }

	  public void drawBoard() {
	    for (int i = 0; i<3; i++) {
	      for (int j = 0; j<3; j++) {
	        fields[j][i].drawField();
	      }
	    }
	  }
	}
