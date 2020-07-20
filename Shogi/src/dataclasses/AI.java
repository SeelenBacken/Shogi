package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

import enums.TeamType;

/**
 * This class extends the {@link Player} class in order
 * {@link #getTurn(Vector2, GameBoard)} to return a random move for a specified
 * Figur.
 *
 */
public class AI extends Player {

	private final Random random;

	/**
	 * The constructor of this class.
	 * 
	 * @param team the team this AI will play.
	 */
	public AI(TeamType team) {
		super("AI", team);
		random = new Random();
	}

	/**
	 * Calculates a valid but random turn for a specified figure it must be ensured
	 * that the figure can make a turn.
	 * 
	 * @return the possible turn as an absolute position on the board.
	 * 
	 * @throws InvalidParameterException if there is no valid figure at the given
	 *                                   position.
	 * 
	 * @see GameBoard#getPossibleTurnsFor(Vector2)
	 */
	@Override
	public Vector2 getTurn(Vector2 figur, GameBoard board) {
		ArrayList<Vector2> temp = board.getPossibleTurnsFor(figur);
		return temp.get(random.nextInt(temp.size()));
	}
}
