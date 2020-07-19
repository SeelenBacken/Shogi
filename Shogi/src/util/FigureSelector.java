package util;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import dataclasses.Figure;
import dataclasses.GameBoard;
import dataclasses.Vector2;
import enums.TeamType;

/**
 * This class is a utility class with static methods to select all alive figures
 * on a {@link GameBoard}. <br>
 * <br>
 * The constructor of this class is private because there is no need to get a
 * non static instance of this class.
 * 
 * @see #selectAliveTeam(Figure[][], TeamType)
 * @see #selectAliveEnemyTeam(Figure[][], TeamType)
 */
public class FigureSelector {

	private FigureSelector() {
	}

	/**
	 * Selects all figures on the board that belong to the given {@link TeamType}.
	 * 
	 * @param board the two dimensional array representing the board in the
	 *              {@link GameBoard} class.
	 * @param team  the team that will be selected.
	 * 
	 * @return an {@link ArrayList} representing the figures of the selected team
	 *         that are still alive or more formally are still on the board.
	 * 
	 * @throws InvalidParameterException if the team is {@link TeamType#NONE}.
	 */
	public static ArrayList<Vector2> selectAliveTeam(Figure[][] board, TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("To selected team can not be NONE!");
		}
		ArrayList<Vector2> selectedTeam = new ArrayList<Vector2>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null && board[x][y].getTeam() == team) {
					selectedTeam.add(new Vector2(x, y));
				}
			}
		}
		return selectedTeam;
	}

	/**
	 * Selects all figures on the board that belong to the enemy team of the given
	 * {@link TeamType}.
	 * 
	 * @param board the two dimensional array representing the board in the
	 *              {@link GameBoard} class.
	 * @param team  the enemy team of this will be selected.
	 * 
	 * @return an {@link ArrayList} representing the figures of the selected enemy
	 *         team that are still alive or more formally are still on the board.
	 * 
	 * @throws InvalidParameterException if the team is {@link TeamType#NONE}.
	 */
	public static ArrayList<Vector2> selectAliveEnemyTeam(Figure[][] board, TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("To selected team can not be NONE!");
		}
		ArrayList<Vector2> selectedTeam = new ArrayList<Vector2>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null && board[x][y].getTeam() != team) {
					selectedTeam.add(new Vector2(x, y));
				}
			}
		}
		return selectedTeam;
	}
}
