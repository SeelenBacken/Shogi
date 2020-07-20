package beans;

import dataclasses.AI;
import dataclasses.GameBoard;
import dataclasses.History;
import dataclasses.Player;
import enums.TeamType;

/**
 * The an instance of this class is a bean representing a game. The name of the
 * players should be set and if there is a bot in this game the bot should set
 * right after instantiation.
 *
 */
public class GameBean {

	private int round;
	private final History history;
	private Player white;
	private Player black;
	private final GameBoard board;

	/**
	 * The constructor of this class.
	 */
	public GameBean() {
		round = 0;
		history = new History(10);
		white = new Player(TeamType.WHITE);
		black = new Player(TeamType.BLACK);
		board = new GameBoard(9);
	}

	/**
	 * Getter of the history.
	 * 
	 * @return the history
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * Getter of the white player.
	 * 
	 * @return the white player
	 */
	public Player getWhite() {
		return white;
	}

	/**
	 * Getter of the black player.
	 * 
	 * @return the black player
	 */
	public Player getBlack() {
		return black;
	}

	/**
	 * Getter of the game board.
	 * 
	 * @return the game board
	 */
	public GameBoard getBoard() {
		return board;
	}

	/**
	 * Evaluates an integer value based on the player that is an {@link AI},
	 * referring to a figure at a position on the {@link GameBoard} represented by
	 * the parameters.
	 * 
	 * @param x value of the figure on the board.
	 * @param y value of the figure on the board.
	 * 
	 * @return {@code 1} if the figure at the coordinate is {@code null} or the
	 *         player that occupies the figure is a human player, 2 if the the
	 *         player is an {@link AI}
	 */
	public int getPlayerTeam(int x, int y) {
		if (this.getBoard().getBoard()[x][y] != null) {
			if (white instanceof AI) {
				return board.getBoard()[x][y].getTeam() == TeamType.WHITE ? 1 : 2;
			} else if (black instanceof AI) {
				return board.getBoard()[x][y].getTeam() == TeamType.BLACK ? 2 : 1;
			}
		}
		return 1;
	}

	/**
	 * Setter of the round. Should only used with 1 or -1.
	 * 
	 * @param i the number of rounds to be incremented.
	 */
	public void setRound(int i) {
		this.round += i;
	}

	/**
	 * Setter of the white player, should only be used if the white player is an AI
	 * 
	 * @param white the ai player
	 */
	public void setWhite(Player white) {
		this.white = white;
	}

	/**
	 * Setter of the black player, should only be used if the black player is an AI
	 * 
	 * @param black the ai player
	 */
	public void setBlack(Player black) {
		this.black = black;
	}
}
