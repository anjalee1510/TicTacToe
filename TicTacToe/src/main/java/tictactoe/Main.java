package tictactoe;

import java.util.Scanner;

import tictactoe.controllers.GameController;
import tictactoe.models.Game;
import tictactoe.models.GameState;

public class Main {
	public static void main(String[] args) {
//		Board board=new Board(3);
//		board.displayBoard();
		
		Game game=GameController.initialiseGame();
		
		GameController gc= new GameController(game);
		gc.boardController.displayBoard();
		while(game.getGameState().equals(GameState.IN_PROGRESS )) {
			//Prints next player's move
			//Ask the user for an input
			
			gc.makeNextMove();
			gc.boardController.displayBoard();
			System.out.println("Do you want to undo the last move?");
			Scanner sc= new Scanner(System.in);
			String ans=sc.next();
			if(ans.equals("Y")) {
				System.out.println("Calling undo ");
				gc.undoLastMove();
				gc.boardController.displayBoard();
			}
			
			
		}
		gc.boardController.displayBoard();
		
		if(game.getGameState().equals(GameState.WINNING)) {
			System.out.printf("The winner of the game is %s\n", game.getWinner().getName());
		}else {
			System.out.printf("The game has ended in a draw");
		}
	}

}
		