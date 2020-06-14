package application;
import processing.core.PApplet;

import java.util.Map;

public class Field {
  FieldState fieldState = FieldState.Empty;
  private int x, y, size;
  public int row, column;
  private PApplet parent;
  public Field (PApplet parent, int x, int y, int size, int row, int column) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.row = row;
    this.column = column;
    this.parent=parent;
  }

  public void drawField() {
    parent.fill(255);
    parent.rect(x, y, size, size);
    if (fieldState == FieldState.Empty) {
      return;
    } else if (fieldState == FieldState.X) {
      parent.line(x, y, x+size, y+size);
      parent.line(x, y+size, x+size, y);
    } else {
      parent.circle(x+size/2, y+size/2, size);
    }
  }

  public boolean mouseInField() {
    if (x < parent.mouseX && parent.mouseX < x+size && y < parent.mouseY && parent.mouseY < y+size) return true;
    else return false;
  }
}



