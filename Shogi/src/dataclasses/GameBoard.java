package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import enums.FigureType;
import enums.TeamType;
import util.CheckMap;
import util.FigureSelector;
import util.Vector2Comparator;

/**
 * This class represents the whole game board, such as the game board it self,
 * the white prison and the black prison.
 *
 */
public class GameBoard {

	private Vector2 blackKingPos;
	private Vector2 whiteKingPos;

	private final int size;
	private final Figure[][] board;
	private final ArrayList<Figure> blackPrison;
	private final ArrayList<Figure> whitePrison;
	private final TreeMap<Vector2, ArrayList<Vector2>> possibleTurns;

	/**
	 * The constructor of this class.
	 * 
	 * @param size side length of the game board, has to be odd in order to ensure a
	 *             symmetrical game board. The game board itself will have this
	 *             number squared as fields.
	 * 
	 * @throws InvalidParameterException if the size is <b>smaller</b> than or
	 *                                   <b>equal</b> to <b>0</b> or is not
	 *                                   <b>odd</b>
	 */
	public GameBoard(int size) {
		if (size <= 0 || size % 2 == 0) {
			throw new InvalidParameterException("Size is not odd!");
		}
		this.size = size;
		board = new Figure[size][size];
		placeFigures();
		blackPrison = new ArrayList<Figure>(20);
		whitePrison = new ArrayList<Figure>(20);
		possibleTurns = new TreeMap<Vector2, ArrayList<Vector2>>(new Vector2Comparator());
	}

	/**
	 * 
	 * @return the game board
	 */
	public Figure[][] getBoard() {
		return board;
	}

	/**
	 * 
	 * @return the black prison
	 */
	public ArrayList<Figure> getBlackPrison() {
		return blackPrison;
	}

	/**
	 * 
	 * @return the white prison
	 */
	public ArrayList<Figure> getWhitePrison() {
		return whitePrison;
	}

	/**
	 * Evaluates all possible turns for a {@link Figure} at a specified postion if
	 * they haven't been already calculated.
	 * 
	 * @param pos the position of the figure
	 * 
	 * @return all possible turns for the figure as an {@link ArrayList}, if there
	 *         are no possible turns for this position this list will be empty.
	 * 
	 * @throws InvalidParameterException if there is no valid figure at the given
	 *                                   position
	 * 
	 * @see #calculatePossibleTurnsFor(Vector2)
	 */
	public ArrayList<Vector2> getPossibleTurnsFor(Vector2 pos) {
		if (ensureTeamTypeForPosition(pos) == TeamType.NONE) {
			throw new InvalidParameterException("No valid figure at the given position!");
		}
		ArrayList<Vector2> temp = possibleTurns.get(pos);
		if (temp == null) {
			temp = calculatePossibleTurnsFor(pos);
			possibleTurns.put(pos, temp);
		}
		return temp;
	}

	/**
	 * Clears all calculated possible turns.
	 */
	public void clearPossibleTurns() {
		possibleTurns.clear();
	}

	/**
	 * Tries to remove the specified figure from a prison.
	 * 
	 * @param figure that shall be removed
	 * 
	 * @return the removed figure
	 * 
	 * @throws InvalidParameterException if the figure is not defined or does not
	 *                                   belong to a team
	 * @throws IndexOutOfBoundsException if there is no such figure in the prison
	 */
	public Figure tryRemoveFromPrison(Figure figure) {
		if ((figure.getType() == FigureType.UNDEFINED) == true) {
			throw new InvalidParameterException("Undefined figure!");
		}
		switch (figure.getTeam()) {
		case WHITE:
			return whitePrison.remove(whitePrison.lastIndexOf(figure));
		case BLACK:
			return blackPrison.remove(blackPrison.lastIndexOf(figure));
		default:
			throw new InvalidParameterException("Figure does not belong to a Team!");
		}
	}

	/**
	 * 
	 * @param pos
	 * 
	 * @return the team of the figure
	 */
	public TeamType ensureTeamTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getTeam() : TeamType.NONE;
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public FigureType ensureFigureTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getType() : FigureType.UNDEFINED;
	}

	public boolean isCheck(TeamType team, CheckMap checkMap) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("");
		}
		boolean calculateCheckOnly = checkMap == null;
		boolean isCheck = false;
		Vector2 kingPos = (team == TeamType.WHITE) ? whiteKingPos : blackKingPos;
		for (Vector2 ePos : FigureSelector.selectAliveEnemyTeam(board, team)) {
			Figure temp = board[ePos.getX()][ePos.getY()];
			ArrayList<Vector2> pathToKing = new ArrayList<Vector2>();
			if (temp.canMoveThere(ePos, kingPos, this, pathToKing)) {
				if (calculateCheckOnly) {
					return true;
				}
				checkMap.addPath(pathToKing);
				isCheck = true;
			}
		}
		return isCheck;
	}

	public boolean isCheckMate(TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("");
		}
		Vector2 kingPos = (team == TeamType.WHITE) ? whiteKingPos : blackKingPos;
		CheckMap checkMap = new CheckMap();
		if (!isCheck(team, checkMap) || !calculatePossibleTurnsFor(kingPos).isEmpty()) {
			return false;
		}
		return kingCanBeSafed(kingPos, checkMap);
	}

	/**
	 * Evaluates the position of the the king for the specified team.
	 * 
	 * @param team the king belongs to
	 * 
	 * @return the position of the king as a {@link Vector2}
	 */
	private Vector2 getKingStartPosition(TeamType team) {
		int y;
		switch (team) {
		case WHITE:
			y = 0;
			break;
		case BLACK:
			y = size - 1;
			break;
		default:
			throw new InvalidParameterException();
		}
		int x = size / 2 + 1;
		return new Vector2(x, y);
	}

	/**
	 * Places all figures on the board.
	 */
	private void placeFigures() {
		whiteKingPos = getKingStartPosition(TeamType.WHITE);
		blackKingPos = getKingStartPosition(TeamType.BLACK);
		board[whiteKingPos.getX()][whiteKingPos.getY()] = Figure.getKing(TeamType.WHITE);
		board[blackKingPos.getX()][blackKingPos.getY()] = Figure.getKing(TeamType.BLACK);
		for (int x = 0; x < size; x++) {
			board[x][2] = Figure.getPawn(TeamType.WHITE);
			board[x][size - 3] = Figure.getPawn(TeamType.BLACK);
			if (x == 0 || x == size - 1) {
				board[x][1] = Figure.getLance(TeamType.WHITE);
				board[x][size - 2] = Figure.getLance(TeamType.BLACK);
			} else if (x == 1 || x == size - 2) {
				if (x == 1) {
					board[x][1] = Figure.getBishop(TeamType.WHITE);
					board[x][size - 2] = Figure.getTower(TeamType.BLACK);
				} else {
					board[x][1] = Figure.getTower(TeamType.WHITE);
					board[x][size - 2] = Figure.getBishop(TeamType.BLACK);
				}
				board[x][1] = Figure.getKnight(TeamType.WHITE);
				board[x][size - 2] = Figure.getKnight(TeamType.BLACK);
			} else if (x == 2 || x == size - 3) {
				board[x][1] = Figure.getSilverGeneral(TeamType.WHITE);
				board[x][size - 2] = Figure.getSilverGeneral(TeamType.BLACK);
			} else if (x == 3 || x == size - 4) {
				board[x][1] = Figure.getGoldenGeneral(TeamType.WHITE);
				board[x][size - 2] = Figure.getGoldenGeneral(TeamType.BLACK);
			}
		}
	}

	/**
	 * Calculates all possible turns for a figure specified by the current position.
	 * 
	 * @param pos the position of the figure on the game board
	 * 
	 * @return all possible turns as an {@link ArrayList} if there are no possible
	 *         turns the list will be empty
	 * 
	 * @see #calculateKingTurns(TreeSet, Vector2)
	 * @see #calculateTurnsUsingDirections(TreeSet, Vector2, Vector2...)
	 * @see #calculateTurnsUsingPositions(TreeSet, Vector2, Vector2...)
	 */
	private ArrayList<Vector2> calculatePossibleTurnsFor(Vector2 pos) {
		TreeSet<Vector2> possibleTurns = new TreeSet<Vector2>();
		switch (ensureFigureTypeForPosition(pos)) {
		case KING:
			calculateKingTurns(possibleTurns, pos);
			break;
		case GOLDEN_GENERAL:
			calculateTurnsUsingPositions(possibleTurns, pos, Vector2.Y, Vector2.ONE, Vector2.X, Vector2.Y.getInverted(),
					Vector2.X.getInverted(), Vector2.ONE.getXInverted());
			break;
		case KNIGHT:
			calculateTurnsUsingPositions(possibleTurns, pos, new Vector2(-1, 2), new Vector2(1, 2));
			break;
		case PAWN:
			calculateTurnsUsingPositions(possibleTurns, pos, Vector2.Y);
			break;
		case SILVER_GENERAL:
			calculateTurnsUsingPositions(possibleTurns, pos, Vector2.Y, Vector2.ONE, Vector2.ONE.getYInverted(),
					Vector2.NEG_ONE, Vector2.ONE.getXInverted());
			break;
		case BISHOP:
			calculateTurnsUsingDirections(possibleTurns, pos, Vector2.ONE, Vector2.ONE.getYInverted(), Vector2.NEG_ONE,
					Vector2.ONE.getXInverted());
			break;
		case LANCE:
			calculateTurnsUsingDirections(possibleTurns, pos, Vector2.Y);
			break;
		case TOWER:
			calculateTurnsUsingDirections(possibleTurns, pos, Vector2.Y, Vector2.X, Vector2.Y.getInverted(),
					Vector2.X.getInverted());
			break;
		case DRAGON:
			calculateTurnsUsingDirections(possibleTurns, pos, Vector2.Y, Vector2.X, Vector2.Y.getInverted(),
					Vector2.X.getInverted());
			calculateTurnsUsingPositions(possibleTurns, pos, Vector2.ONE, Vector2.ONE.getYInverted(), Vector2.NEG_ONE,
					Vector2.ONE.getXInverted());
			break;
		case HORSE:
			calculateTurnsUsingDirections(possibleTurns, pos, Vector2.ONE, Vector2.ONE.getYInverted(), Vector2.NEG_ONE,
					Vector2.ONE.getXInverted());
			calculateTurnsUsingPositions(possibleTurns, pos, Vector2.Y, Vector2.X, Vector2.Y.getInverted(),
					Vector2.X.getInverted());
			break;
		default:
			throw new InvalidParameterException("No valid figure at the given position!");
		}
		return new ArrayList<Vector2>(possibleTurns);
	}

	/**
	 * Determines if a specified position is outside of the game board or not.
	 * 
	 * @param pos the position
	 * 
	 * @return {@code true} if the position is outside of the game board,
	 *         {@code false} otherwise
	 */
	private boolean isOutOfBounds(Vector2 pos) {
		return pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= size || pos.getY() >= size;
	}

	/**
	 * Calculates all possible turns for a king at the specified position and adds
	 * them to the {@link TreeSet}.
	 * 
	 * @param turns the calculated turns will be added to this set
	 * 
	 * @param pos   the position of the specific king
	 */
	private void calculateKingTurns(TreeSet<Vector2> turns, Vector2 pos) {
		turns.add(Vector2.add(pos, Vector2.Y));
		turns.add(Vector2.add(pos, Vector2.ONE));
		turns.add(Vector2.add(pos, Vector2.X));
		turns.add(Vector2.add(pos, Vector2.ONE.getYInverted()));
		turns.add(Vector2.add(pos, Vector2.Y.getInverted()));
		turns.add(Vector2.add(pos, Vector2.NEG_ONE));
		turns.add(Vector2.add(pos, Vector2.X.getInverted()));
		turns.add(Vector2.add(pos, Vector2.ONE.getXInverted()));
		TeamType team = ensureTeamTypeForPosition(pos);
		ArrayList<Vector2> enemyTeam = FigureSelector.selectAliveEnemyTeam(board, team);
		for (Vector2 ePos : enemyTeam) {
			if (ePos.equals((team == TeamType.WHITE) ? blackKingPos : whiteKingPos)) {
				// remove if enemy king can move there
				turns.removeIf((v) -> board[ePos.getX()][ePos.getY()].isValidMove(v));
			} else {
				// remove if other enemy can move there
				turns.removeIf((turn) -> board[ePos.getX()][ePos.getY()].canMoveThere(ePos, turn, this));
			}
		}
		// remove if figure of the same team is at the position
		turns.removeIf((v) -> team == ensureTeamTypeForPosition(v));
	}

	private void calculateTurnsUsingPositions(TreeSet<Vector2> turns, Vector2 currPos, Vector2... relPositions) {
		for (Vector2 relPos : relPositions) {
			Vector2 pos = Vector2.add(currPos, relPos);
			if (!isOutOfBounds(pos)
					&& board[currPos.getX()][currPos.getY()].getTeam() != ensureTeamTypeForPosition(pos)) {
				turns.add(pos);
			}
		}
	}

	private void calculateTurnsUsingDirections(TreeSet<Vector2> turns, Vector2 pos, Vector2... directions) {
		TeamType team = ensureTeamTypeForPosition(pos);
		for (Vector2 dir : directions) {
			for (int x = pos.getX(); x >= 0 && x < size; x += dir.getX()) {
				for (int y = pos.getY(); y >= 0 && y < size; y += dir.getX()) {
					if (team == ensureTeamTypeForPosition(pos)) {
						break;
					} else if (TeamType.NONE != ensureTeamTypeForPosition(pos)) {
						turns.add(Vector2.add(pos, new Vector2(x, y)));
						break;
					}
					turns.add(Vector2.add(pos, new Vector2(x, y)));
				}
			}
		}
	}

	private boolean kingCanBeSafed(Vector2 kingPos, CheckMap checkMap) {
		ArrayList<Vector2> team = FigureSelector.selectAliveTeam(board, ensureTeamTypeForPosition(kingPos));
		if (checkMap.hasCrucialPosition()) {
			if (checkMap.getCrucialPositionCount() == 1) {
				for (Vector2 mate : team) {
					if (board[mate.getX()][mate.getY()].canMoveThere(mate, checkMap.getFirstCrucialPosition(), this,
							null)) {
						return true;
					}
				}
			} else {
				return false;
			}
		}
		switch (checkMap.getPathCount()) {
		case 0:
			return true;
		case 1:
			for (Vector2 posOnCheckPath : checkMap.getFirstPath()) {
				for (Vector2 mate : team) {
					if (board[mate.getX()][mate.getY()].canMoveThere(mate, posOnCheckPath, this, null)) {
						return true;
					}
				}
			}
			break;
		}
		return false;
	}
}
