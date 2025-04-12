package tictactoe.strategy;

import tictactoe.models.Board;
import tictactoe.models.Cell;
import tictactoe.models.Player;

public interface BotPlayingStrategy {
	Cell suggestMove(Player player,Board board);

}
