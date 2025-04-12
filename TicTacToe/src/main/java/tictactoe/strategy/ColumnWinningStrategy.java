package tictactoe.strategy;

import tictactoe.models.Cell;
import tictactoe.models.CellState;
import tictactoe.models.Game;
import tictactoe.models.Player;

public class ColumnWinningStrategy implements WinningStrategy {

	@Override
	public boolean isWinning(Game game) {
		// TODO: Iterate over all the rows and decide if the game ends
		Player currPlayer=game.playerList.get(game.currPlayerIndex);
		Cell cell=game.moves.get(game.moves.size()-1);
		int col=cell.getCol();
		
		for(int i=0;i<game.board.getN();i++) {
			Cell curr=game.board.getCells().get(i).get(col);
			if(curr.getCellState().equals(CellState.FREE) || !curr.getPlayer().equals(currPlayer)) {
				return false;
			}
		}
		return true;
	}
}
