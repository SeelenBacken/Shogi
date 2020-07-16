package dataclasses;

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
		super(team, "AI");
		random = new Random();
	}

	/**
	 * Calculates a valid but random turn for a speciefied figur it must be ensured
	 * that the figur can make a turn.
	 * 
	 * @return the possible turn as an absolute position on the board.
	 * 
	 * @throws IllegalArgumentException if the figur has no possible turns at all.
	 */
	@Override
	public Vector2 getTurn(Vector2 figur, GameBoard board) {
		ArrayList<Vector2> temp = board.getPossibleTurnsFor(figur);
		return temp.get(random.nextInt(temp.size()));
	}
}
