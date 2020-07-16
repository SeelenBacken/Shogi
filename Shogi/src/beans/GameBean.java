package beans;

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
  
	public int getPlayerTeam(int x, int y) {
		if(this.getBoard().getBoard()[x][y] != null) {
			Player p = (this.getBlack().getName().equals("AI")) ? this.getWhite() : this.getBlack();
			
			int res = (this.getBoard().getBoard()[x][y].getTeam().equals(p.getTeam())) ? 1 : 2;
			return res;
		} 
		return 1;
		
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
