package tictactoe.strategy;

import tictactoe.models.Game;

public interface WinningStrategy {
	boolean isWinning(Game game);

}
