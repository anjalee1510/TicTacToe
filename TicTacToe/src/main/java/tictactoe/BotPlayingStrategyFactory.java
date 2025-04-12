package tictactoe;

import tictactoe.models.BotDifficultyLevel;
import tictactoe.strategy.BotPlayingStrategy;
import tictactoe.strategy.EasyBotPlayingStrategy;
import tictactoe.strategy.HardBotPlayingStrategy;
import tictactoe.strategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
	
	public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
	    switch (botDifficultyLevel) {
	        case EASY:
		            return (BotPlayingStrategy) new EasyBotPlayingStrategy();
	        case MEDIUM:
	            return (BotPlayingStrategy) new MediumBotPlayingStrategy();
	        case HARD: 
	        	return (BotPlayingStrategy) new HardBotPlayingStrategy();
	        default:
	            return (BotPlayingStrategy) new MediumBotPlayingStrategy(); 
	    }
	}
}
