package application;
import java.util.ArrayList;

public class GameHandler implements Runnable {
	  private Game currentGame;
	  public ArrayList<Game> games = new ArrayList<Game>();
	  public ArrayList<EvolvingPlayer> winningPlayers = new ArrayList<EvolvingPlayer>();
	  private int counter = 0;
	  private int xWinner, oWinner, tie;
	  public GameHandler() {
	  }

	  public void addGame (Game game) {
	    games.add(game);
	    game.startGame();
	  }

	  public void run() {
	    while (true) {
	      while (games.size()!=0) {
	        for (int i = games.size()-1; i >= 0; i--) {
	          currentGame = games.get(i);
	          try {
	            if (currentGame.gameOver) {
	              if (currentGame.winningPlayer!=null && currentGame.winningPlayer instanceof EvolvingPlayer) {
	                winningPlayers.add((EvolvingPlayer)currentGame.winningPlayer);
	                xWinner++;
	              }
	              else if (currentGame.winningPlayer==null) tie++;
	              else oWinner++;
	              games.remove(currentGame);
	              System.out.println("The winner is " + currentGame.winningPlayer.fieldState.toString());
	            }
	          }
	          catch (NullPointerException e) {
	            System.out.println("Null-Pointer");
	          }
	          catch (ArrayIndexOutOfBoundsException e) {
	            System.out.println("Let the Computer win first!");
	          }
	        }
	      }
	      break;
	    }
	  }

	  public void printPlayerStats() {
	      System.out.println("X: " + xWinner + " O: " + oWinner + " Tie: " + tie);
	  }

	  public void drawCurrentGame() {
	    if (currentGame != null)
	    currentGame.drawGame();
	  }
	}
