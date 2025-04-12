package tictactoe.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import tictactoe.models.Board;
import tictactoe.models.Cell;
import tictactoe.models.CellState;
import tictactoe.models.Player;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {

	@Override
	public Cell suggestMove(Player player, Board board) {
		int n=board.getN();
		List<Cell> availableCells=new ArrayList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(board.getCells().get(i).get(j).getCellState().equals(CellState.FREE)) {
					availableCells.add(board.getCells().get(i).get(j));
				}				
			}
		}
		
		int x= Math.abs(RandomGenerator.getDefault().nextInt()) % availableCells.size();
		return availableCells.get(x);
	}
}
