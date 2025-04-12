package tictactoe.strategy;

import tictactoe.models.Cell;
import tictactoe.models.CellState;
import tictactoe.models.Game;
import tictactoe.models.Player;

public class DiagonalWinningStrategy implements WinningStrategy {

	@Override
	public boolean isWinning(Game game) {
		return leftTopToRightBottomCheck(game) || rightTopToLeftBottomCheck(game);
	}
	
	public boolean leftTopToRightBottomCheck(Game game) {
	 
		Player currPlayer=game.playerList.get(game.currPlayerIndex);
//		Cell cell=game.moves.get(game.moves.size()-1);
//		int col=cell.getCol();
		
		for(int i=0;i<game.board.getN();i++) {
			Cell curr=game.board.getCells().get(i).get(i);
			if(curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean rightTopToLeftBottomCheck(Game game) {
		 
		Player currPlayer=game.playerList.get(game.currPlayerIndex);
		int N=game.board.getN();
//		Cell cell=game.moves.get(game.moves.size()-1);
//		int col=cell.getCol();
		
		for(int i=0;i<N;i++) {
			Cell curr=game.board.getCells().get(i).get(N-i-1);
			if(curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
				return false;
			}
		}
		return true;
	}
}
