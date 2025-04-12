package tictactoe.models;

import lombok.Getter;

@Getter
public abstract class Player {
	String name;
	Character symbol;
	int id;
	
	public Player(String name, Character symbol, int id) {
		this.name=name;
		this.symbol=symbol;
		this.id=id;
	}
//	public abstract Cell makeMove(Board board, Player player);
	public abstract Cell makeMove(Board board, Player player);
}
