package application;

public enum FieldState {
	  X(1), O(2), Empty(3);
	  
	  private int value;
	  private FieldState (int value) {
	    this.value=value;
	  }
	  public int getValue() {
	    return value;
	  }
	  
	};
