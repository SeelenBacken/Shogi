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
	 * @throws InvalidParameterException if the size is <b>smaller than</b> or
	 *                                   <b>equal to 0</b> or is not <b>odd</b>
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
	 * Getter of the game board.
	 * 
	 * @return the game board
	 */
	public Figure[][] getBoard() {
		return board;
	}

	/**
	 * Getter of the black prison.
	 * 
	 * @return the black prison
	 */
	public ArrayList<Figure> getBlackPrison() {
		return blackPrison;
	}
	
	/**
	 * Getter of the white prison.
	 * 
	 * @return the white prison
	 */
	public ArrayList<Figure> getWhitePrison() {
		return whitePrison;
	}

	/**
	 * Evaluates all possible turns for a {@link Figure} at a specified position if
	 * they have not been already calculated.
	 * 
	 * @param pos the position of the figure
	 * 
	 * @return all possible turns for the figure as an {@link ArrayList}, if there
	 *         are no possible turns for this position this list will be empty.
	 * 
	 * @throws InvalidParameterException if there is no valid figure at the given
	 *                                   position.
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
	 * Tests if a king specified by the team is check.
	 * 
	 * @param team     of the king that could be checked
	 * @param checkMap if not {@code null}, all paths that will result in a check
	 *                 will be added to this
	 * 
	 * @return {@code true} if check,{@code false} otherwise
	 * 
	 * @throws InvalidParameterException if team is {@link TeamType#NONE}
	 * 
	 * @see CheckMap
	 * @see #isCheckMate(TeamType)
	 */
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

	/**
	 * Tests if a king specified by the team is check mate.
	 * 
	 * @param team of the king that could be check mate
	 * 
	 * @return {@code true} if check mate,{@code false} otherwise
	 * 
	 * @throws InvalidParameterException if team is {@link TeamType#NONE}
	 * 
	 * @see CheckMap
	 * @see #isCheck(TeamType, CheckMap)
	 * @see #kingCanBeSafed(Vector2, CheckMap)
	 */
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
	 * Tests if the insertion of the specific figure at the given position would
	 * cause a check.
	 * 
	 * @param insertPos position at which the figure will be inserted. @param figure
	 *                  that will be inserted.
	 * 
	 * @return {@code true} if the insertion would cause a check {@code false}
	 *         otherwise.
	 * 
	 * @throws InvalidParameterException if either the {@code insertPos} or
	 *                                   {@code figure} is {@code null} or if the
	 *                                   team of the figure is
	 *                                   {@link TeamType#NONE}.
	 */
	public boolean causesCheck(Vector2 insertPos, Figure figure) {
		if (insertPos == null) {
			throw new InvalidParameterException("The insert position cannot be null!");
		}
		if (figure == null) {
			throw new InvalidParameterException("The inserted figure cannot be null!");
		}
		if (figure.getTeam() == TeamType.NONE) {
			throw new InvalidParameterException("The figure must be part of a team!");
		}
		Vector2 kingPos = figure.getTeam() == TeamType.WHITE ? blackKingPos : whiteKingPos;
		return figure.canMoveThere(insertPos, kingPos, this);
	}

	/**
	 * Moves a figure defined by the {@code hitter} parameter towards the target
	 * position if the target position is not occupied by a team member of the
	 * moving figure.
	 * 
	 * @param hitter a position where the figure that moves on the board towards the
	 *               target
	 * @param target the position where the figure should move at.
	 * 
	 * @throws InvalidParameterException if there is no hitter at the given
	 *                                   position.
	 */
	public void moveOnBoard(Vector2 hitter, Vector2 target) {
		if (ensureFigureTypeForPosition(hitter) == FigureType.UNDEFINED) {
			throw new InvalidParameterException("No hitter at given position: " + hitter);
		}
		if (ensureTeamTypeForPosition(target) != TeamType.NONE) {
			if (ensureTeamTypeForPosition(target) == ensureTeamTypeForPosition(hitter)) {
				return;
			}
			switch (ensureTeamTypeForPosition(target)) {
			case BLACK:
				blackPrison.add(board[target.getX()][target.getY()]);
				break;
			case WHITE:
				whitePrison.add(board[target.getX()][target.getY()]);
			default:
				break;
			}
		}
		board[target.getX()][target.getY()] = board[hitter.getX()][hitter.getY()];
		board[hitter.getX()][hitter.getY()] = null;
	}

	/**
	 * Evaluates a {@link TeamType} for a figure at the given position on the game
	 * board, even if there is none.
	 * 
	 * @param pos the absolute position of a figure on the game board.
	 * 
	 * @return the team of the figure or {@link TeamType#NONE} if there is no Figure
	 *         at the given position.
	 */
	public TeamType ensureTeamTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getTeam() : TeamType.NONE;
	}

	/**
	 * Evaluates a {@link FigureType} for a figure at the given position on the game
	 * board, even if there is none.
	 * 
	 * @param pos the absolute position of a figure on the game board.
	 * 
	 * @return the team of the figure or {@link FigureType#UNDEFINED} if there is no
	 *         Figure at the given position.
	 */
	public FigureType ensureFigureTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getType() : FigureType.UNDEFINED;
	}

	/**
	 * Evaluates the absolute position of the the king for the specified team.
	 * 
	 * @param team the king belongs to
	 * 
	 * @return the position of the king as a {@link Vector2}
	 * 
	 * @throws IllegalArgumentException if the team is {@link TeamType#NONE}
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
			throw new IllegalArgumentException();
		}
		int x = size / 2;
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
				board[x][0] = Figure.getLance(TeamType.WHITE);
				board[x][size - 1] = Figure.getLance(TeamType.BLACK);
			} else if (x == 1 || x == size - 2) {
				if (x == 1) {
					board[x][1] = Figure.getTower(TeamType.WHITE);
					board[x][size - 2] = Figure.getBishop(TeamType.BLACK);
				} else {
					board[x][1] = Figure.getBishop(TeamType.WHITE);
					board[x][size - 2] = Figure.getTower(TeamType.BLACK);
				}
				board[x][0] = Figure.getKnight(TeamType.WHITE);
				board[x][size - 1] = Figure.getKnight(TeamType.BLACK);
			} else if (x == 2 || x == size - 3) {
				board[x][0] = Figure.getSilverGeneral(TeamType.WHITE);
				board[x][size - 1] = Figure.getSilverGeneral(TeamType.BLACK);
			} else if (x == 3 || x == size - 4) {
				board[x][0] = Figure.getGoldenGeneral(TeamType.WHITE);
				board[x][size - 1] = Figure.getGoldenGeneral(TeamType.BLACK);
			}
		}
	}

	/**
	 * Calculates all possible turns for a figure specified by the current position.
	 * Ensures that the turns are valid even if the current situation
	 * {@link #isCheck(TeamType, CheckMap) is check}.
	 * 
	 * @param pos the position of the figure on the game board
	 * 
	 * @return all possible turns as an {@link ArrayList} if there are no possible
	 *         turns the list will be empty
	 * 
	 * @throws InvalidParameterException if the figure at the given position is not
	 *                                   valid, let it be either the team is
	 *                                   {@link TeamType#NONE} or the type is
	 *                                   {@link FigureType#UNDEFINED}, or there is
	 *                                   no figure at all.
	 * 
	 * @see #calculateKingTurns(TreeSet, Vector2)
	 * @see #calculateTurnsUsingDirections(TreeSet, Vector2, Vector2...)
	 * @see #calculateTurnsUsingPositions(TreeSet, Vector2, Vector2...)
	 * @see #isCheck(TeamType, CheckMap)
	 */
	private ArrayList<Vector2> calculatePossibleTurnsFor(Vector2 pos) {
		TreeSet<Vector2> possibleTurns = new TreeSet<Vector2>(new Vector2Comparator());
		System.out.println("figure:" + pos);
		System.out.println(this.ensureFigureTypeForPosition(pos));
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
		CheckMap toSatisfy = new CheckMap();
		TeamType team = ensureTeamTypeForPosition(pos);
		if (isCheck(team, toSatisfy) && ensureFigureTypeForPosition(pos) != FigureType.KING) {
			if (toSatisfy.hasCrucialPosition()) {
				possibleTurns.removeIf((turn) -> !turn.equals(toSatisfy.getFirstCrucialPosition()));
			} else if (toSatisfy.getPathCount() > 1) {
				possibleTurns.clear();
			} else if (toSatisfy.getPathCount() == 1) {
				possibleTurns.removeIf((turn) -> !toSatisfy.getFirstPath().contains(turn));
			}
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
			// remove if other enemy can move there
			turns.removeIf((turn) -> board[ePos.getX()][ePos.getY()].canMoveThere(ePos, turn, this));
		}
		// remove if figure of the same team is at the position
		turns.removeIf((v) -> isOutOfBounds(v) || team == ensureTeamTypeForPosition(v));
	}

	/**
	 * Calculates all possible turns for a figure and adds them to the turns, more
	 * formally tests if the absolute position, that results as addition of the
	 * currPos and the relPositions are inside the game boards boundaries and free
	 * or occupied be an enemy and if so this result will be added to the turns.
	 * 
	 * @param turns        all valid absolute positions will be added to this.
	 * @param currPos      current position of this figure on the game board.
	 * @param relPositions relative positions to the current position, representing
	 *                     the turns of this figure, all have to be valid in order
	 *                     allowing this method to execute properly.
	 */
	private void calculateTurnsUsingPositions(TreeSet<Vector2> turns, Vector2 currPos, Vector2... relPositions) {
		for (Vector2 relPos : relPositions) {
			TeamType currPosTeam = ensureTeamTypeForPosition(currPos);
			Vector2 pos = Vector2.add(currPos, currPosTeam == TeamType.BLACK ? relPos.getInverted() : relPos);
			if (!isOutOfBounds(pos)
					&& currPosTeam != ensureTeamTypeForPosition(pos)) {
				turns.add(pos);
			}
		}
	}

	/**
	 * Calculates all possible turns for a figure and adds them to the turns, more
	 * formally tests if the absolute position, that results as addition of the
	 * currPos and the directions are inside the game boards boundaries and free or
	 * occupied be an enemy and if so this result will be added to the turns. The
	 * each direction will be added to the current position until the result is is
	 * either out of bounds or the position is occupied by a other figure than, in
	 * case the position is occupied by an enemy this result will also be added.
	 * 
	 * @param turns      all valid absolute positions will be added to this.
	 * @param currPos    current position of this figure on the game board.
	 * @param directions each represents a path the figure can take until a position
	 *                   on the path blocks further movements of this figure, all
	 *                   directions have to be valid in order allowing this method
	 *                   to execute properly.
	 */
	private void calculateTurnsUsingDirections(TreeSet<Vector2> turns, Vector2 pos, Vector2... directions) {
		TeamType team = ensureTeamTypeForPosition(pos);
		for (Vector2 dir : directions) {
			if(ensureTeamTypeForPosition(pos) == TeamType.BLACK) {
				dir = dir.getInverted();
			}
			for (Vector2 currPos = new Vector2(pos); !isOutOfBounds(currPos); currPos = Vector2.add(currPos, dir)) {
				if (team == ensureTeamTypeForPosition(currPos)) {
					break;
				} else if (TeamType.NONE != ensureTeamTypeForPosition(pos)) {
					turns.add(currPos);
					break;
				}
				turns.add(currPos);
			}
		}
	}

	/**
	 * Evaluates if the king can be safed from checkmate, more formally if all paths
	 * contained by the {@link CheckMap} threatening the king can be covered using
	 * own pawns.
	 * 
	 * @param kingPos  the position of the checked king.
	 * @param checkMap the {@link CheckMap} representing the current check
	 *                 situation.
	 * 
	 * @return true if and only if the king can be saved from check mate, false
	 *         otherwise.
	 */
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
