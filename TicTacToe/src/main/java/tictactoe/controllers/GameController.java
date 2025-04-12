package tictactoe.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import lombok.Builder;
import tictactoe.models.BotDifficultyLevel;
import tictactoe.models.BotPlayer;
import tictactoe.models.Cell;
import tictactoe.models.CellState;
import tictactoe.models.Game;
import tictactoe.models.HumanPlayer;
import tictactoe.models.Player;
 
//@Builder
public class GameController {
	
	Game game;
	public BoardController boardController;
	
	public GameController(Game game) {
		this.game=game;
		this.boardController= new BoardController(game.board);
	} 
//	private static final Game Game = null;

	public static Game initialiseGame() {
		System.out.println("Enter the board size: ");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		List<Player> playerList=new ArrayList<>();
		for(int i=0;i<n-1;i++) {
			playerList.add(getPlayerInfoFromUser(i+1));
		}
			
		return new Game(n, playerList);
//		return Game builder()
//				.board()
//				.playerList()
//				.build();
	}
	
	private static Player getPlayerInfoFromUser(int i) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the player name, and the symbol ");
		String name= sc.next();
		String symbol=sc.next();
		System.out.println("Is this a bot player?");
		String ans=sc.next();
		System.out.println(" ans is: "+ans);
		if(ans.equals("Y")) {
			System.out.println("Inside yes");
			System.out.println("Enter the difficulty level for the bot (1/2/3)");
			int val = sc.nextInt();

			BotDifficultyLevel botDifficultyLevel;

			switch (val) {
			    case 1:
			        botDifficultyLevel = BotDifficultyLevel.EASY;
			        break;
			    case 2:
			        botDifficultyLevel = BotDifficultyLevel.MEDIUM;
			        break;
			    case 3:
			    	botDifficultyLevel=BotDifficultyLevel.HARD;
			    default:
			        botDifficultyLevel = BotDifficultyLevel.MEDIUM;
			        break;
			}
			return new BotPlayer(name, symbol.charAt(0),i+1,botDifficultyLevel);
		} 
			return new HumanPlayer(name, symbol.charAt(0),i+1);
		
		
	}
	/**
	 * 
	 * 1.Finds the player with next move
	 * 2. Calls the makeMove method for the corresponding player
	 * 3. Updates the board with that move and corresponding validation
	 * 4. Checks all the winning strategies
	 * 5. Displays the board
	 *  
	 */
	public void makeNextMove() {
		System.out.println("inside makeNextMove");
		if(boardController.isFull()) {
			System.out.println("isFull condition true");
			game.setDraw();
			return;
		}
		// Step 1
		int currPlayerIndex=game.getCurrPlayerIndex();
		Player currPlayer=game.playerList.get(currPlayerIndex);
		
		// Step 2
		System.out.printf("It's %s's move\n",currPlayer.getName());
		makeMoveForCurrPlayer();

		// Step 4- check for winning strategies
		postMoveWinnerCheck();

	}
	public void undoLastMove() {
		// 1. Remove from moves list
		System.out.println("inside undo function");
		Cell moveCell= game.moves.get(game.moves.size()-1);
		game.moves.remove(moveCell);
		
		// 2. Updating the board without that cell
		Cell cell =game.getBoard().getCells().get(moveCell.getRow()).get(moveCell.getCol());
		cell.setPlayer(null);
		cell.setCellState(CellState.FREE);
		
		// 3. Update the curr player
		game.currPlayerIndex =(game.currPlayerIndex -1+ game.playerList.size())%game.playerList.size();
		
	}
	/**
	 * This method makes the next player decide a move and updates the board.
	 */
	public void makeMoveForCurrPlayer() {
		Player currPlayer=this.game.playerList.get(game.currPlayerIndex);
		Cell cell=currPlayer.makeMove(game.board, currPlayer);
		
		//increment game index
		//check if somebody won
		// Step 3- update the board, if it fails, again try to make the next move
		try {
			this.boardController.updateBoard(cell, currPlayer);
			this.game.moves.add(cell);
		}catch(IllegalArgumentException e) {
			System.out.println("Please choose a valid cell.");
			makeMoveForCurrPlayer();
		}
		
		
	}
	
	public void postMoveWinnerCheck() {
		boolean isWin = game.getWinningStrategies().stream()
				.anyMatch(winningStrategy -> winningStrategy.isWinning(game));
		
		if(isWin) {
			// Store the winning player, change the game state
			game.setWinner();
		}else {
			// update the next player index
			game.currPlayerIndex+=1;
			game.currPlayerIndex %= game.playerList.size();
		}
	}
	
	

}
