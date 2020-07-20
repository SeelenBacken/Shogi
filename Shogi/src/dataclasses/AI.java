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
	 * AI's team, and executes it. This methods expects only check and not check
	 * mate and will return a false turn if the current situation is check mate.
	 * 
	 * @return {@code true} and only if the random figure moved on the board,
	 *         {@code false} otherwise.
	 * 
	 * @see GameBoard#getPossibleTurnsFor(Vector2)
	 */
	@Override
	public boolean doTurn(GameBoard board) {
		ArrayList<Vector2> team = FigureSelector.selectAliveTeam(board.getBoard(), getTeam());
		team.removeIf((pos) -> board.getPossibleTurnsFor(pos).size() == 0);
		if (team.size() != 0) {
			Vector2 figure = team.get(random.nextInt(team.size()));
			ArrayList<Vector2> turns = board.getPossibleTurnsFor(figure);
			if (turns.size() != 0) {
				board.moveOnBoard(figure, turns.get(random.nextInt(turns.size())));
				return true;
			}
		}
		return false;
	}
}
