package tictactoe.models;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tictactoe.strategy.ColumnWinningStrategy;
import tictactoe.strategy.DiagonalWinningStrategy;
import tictactoe.strategy.RowWinningStrategy;
import tictactoe.strategy.WinningStrategy;

//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
	public Board board;
	public List<Player> playerList;
	public int currPlayerIndex;
	public List<Cell> moves;
	GameState gameState;
	List<WinningStrategy> winningStrategies;
	Player winner;
	
	public Game(int n, List<Player> playerList) {
		this.board=new Board(n);
		this.playerList=playerList;
		this.currPlayerIndex=0;
		this.moves=new ArrayList<>();
		this.gameState=GameState.IN_PROGRESS;
		this.winningStrategies=new ArrayList<>(List.of(
					new RowWinningStrategy(),new ColumnWinningStrategy(), new DiagonalWinningStrategy()));
//					
	}
	
	public void setWinner() {
		gameState=GameState.WINNING;
		winner=playerList.get(currPlayerIndex);
	}
	
	public void setDraw() {
		gameState=GameState.DRAW;
	}
	


}
