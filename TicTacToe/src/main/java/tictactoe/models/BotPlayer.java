package tictactoe.models;

import tictactoe.BotPlayingStrategyFactory;
import tictactoe.strategy.BotPlayingStrategy;

public class BotPlayer extends Player{
	BotDifficultyLevel botDifficultyLevel;
	BotPlayingStrategy botPlayingStrategy;

	public BotPlayer(String name, Character symbol, int id,BotDifficultyLevel botDifficultyLevel ) {
		super(name, symbol, id);
		// TODO Auto-generated constructor stub
		this.botDifficultyLevel=botDifficultyLevel;
		this.botPlayingStrategy=BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
	}

	@Override
	public Cell makeMove(Board board, Player player) {
		// TODO Auto-generated method stub
		return botPlayingStrategy.suggestMove(player,board);
		
	}

//	@Override
//	public Cell makeMove(Board board, Player player) {
//		return botPlayingStrategy.suggestMove(player, board);
//	}
	
	
	

}
