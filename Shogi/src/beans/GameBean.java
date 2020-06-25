package beans;

import dataclasses.GameBoard;
import dataclasses.History;
import dataclasses.Player;
import enums.TeamType;

public class GameBean {

	private int round;
	private final History history;
	private final Player white;
	private final Player black;
	private final GameBoard board;

	public GameBean() {
		round = 0;
		history = new History(10);
		white = new Player(TeamType.WHITE);
		black = new Player(TeamType.BLACK);
		board = new GameBoard(9);
	}

	public History getHistory() {
		return history;
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}

	public GameBoard getBoard() {
		return board;
	}

	public Player getCurrentPlayer() {
		return (++round % 2 == 1) ? white : black;
	}
}
