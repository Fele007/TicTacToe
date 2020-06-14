package application;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
	  public Board board;
	  private Player playerX, playerO;
	  private FieldState winningFieldState;
	  private Player startPlayer, endPlayer;
	  public Player winningPlayer, losingPlayer;
	  private Thread gameThread;
	  public boolean gameOver=false;
	  public int round = 1;

	  public Game (Board board, Player playerX, Player playerO) {
	    this.board = board;
	    this.playerO = playerO;
	    this.playerX = playerX;
	    gameThread = new Thread(this);
	  }

	  public void startGame () {
	    startPlayer = playerX;
	    int coinToss = ThreadLocalRandom.current().nextInt(0, 2);
	    if (coinToss == 0) {
	      startPlayer = playerO;
	      endPlayer = playerX;
	    } else {
	      endPlayer = playerO;
	    }
	    gameThread.start();
	  }


	  public void drawGame() {
	    board.drawBoard();
	  }

	  public void run() {
	    while ((winningFieldState = board.finished()) == null) {
	      startPlayer.play(this);
	      round++;
	      if ((winningFieldState = board.finished()) != null) break;
	      endPlayer.play(this);
	      round++;
	    }
	    if (winningFieldState == playerX.fieldState) {
	      winningPlayer = playerX;
	      losingPlayer = playerO;
	      if (winningPlayer instanceof EvolvingPlayer) {
	        ((EvolvingPlayer) winningPlayer).reward(this);
	      }
	      if (losingPlayer instanceof EvolvingPlayer) {
	        ((EvolvingPlayer) losingPlayer).punish(this);
	        System.out.println("Punished");
	      }
	    }
	    else if (winningFieldState == playerO.fieldState) {
	      winningPlayer = playerO;
	      losingPlayer = playerX;
	      if (winningPlayer instanceof EvolvingPlayer) {
	        ((EvolvingPlayer) winningPlayer).reward(this);
	      }
	      if (losingPlayer instanceof EvolvingPlayer) {
	        ((EvolvingPlayer) losingPlayer).punish(this);
	      }
	    }
	    else winningPlayer = null;
	    gameOver = true;
	    
	  }
	}
