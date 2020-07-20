package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

import enums.TeamType;
import util.FigureSelector;

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
	 * Calculates a valid but random turn for a random figure that belongs to the
	 * AI's team. This methods expects only check and not checkmate and will return
	 * a false turn if the current situation is checkmate.
	 * 
	 * @return the possible turn as an absolute position on the board, or
	 *         {@code null} if there is no possible turn.
	 * 
	 * @throws InvalidParameterException if there is no valid figure at the given
	 *                                   position.
	 * 
	 * @see GameBoard#getPossibleTurnsFor(Vector2)
	 */
	@Override
	public Vector2 getTurn(GameBoard board) {
		ArrayList<Vector2> team = FigureSelector.selectAliveTeam(board.getBoard(), getTeam());
		team.removeIf((pos) -> board.getPossibleTurnsFor(pos).size() == 0);
		if (team.size() != 0) {
			ArrayList<Vector2> turns = board.getPossibleTurnsFor(team.get(random.nextInt(team.size())));
			if (turns.size() != 0) {
				return turns.get(random.nextInt(turns.size()));
			}
		}
		return null;
	}
}
