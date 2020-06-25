package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import enums.FigureType;
import enums.TeamType;
import util.FigureSelector;
import util.Vector2Comparator;

public class GameBoard {

	private Vector2 blackKingPos;
	private Vector2 whiteKingPos;

	private final int size;
	private final Figure[][] board;
	private final ArrayList<Figure> blackPrison;
	private final ArrayList<Figure> whitePrison;
	private final TreeMap<Vector2, ArrayList<Vector2>> possibleTurns;

	public GameBoard(int size) {
		if (size % 2 == 0) {
			throw new InvalidParameterException("Size is not odd!");
		}
		this.size = size;
		board = new Figure[size][size];
		blackKingPos = getKingStartPosition(TeamType.BLACK);
		whiteKingPos = getKingStartPosition(TeamType.WHITE);
		placeFigures();
		blackPrison = new ArrayList<Figure>(20);
		whitePrison = new ArrayList<Figure>(20);
		possibleTurns = new TreeMap<Vector2, ArrayList<Vector2>>(new Vector2Comparator());
	}

	public Figure[][] getBoard() {
		return board;
	}

	public ArrayList<Figure> getBlackPrison() {
		return blackPrison;
	}

	public ArrayList<Figure> getWhitePrison() {
		return whitePrison;
	}

	public ArrayList<Vector2> getPossibleTurnsFor(Vector2 pos) {
		ArrayList<Vector2> temp = possibleTurns.get(pos);
		if (temp == null) {
			temp = calculatePossibleTurnsFor(pos);
			possibleTurns.put(pos, temp);
		}
		return temp;
	}

	public void clearPossibleTurns() {
		possibleTurns.clear();
	}

	public Figure tryRemoveFromPrison(Figure figure) {
		switch (figure.getTeam()) {
		case WHITE:
			return whitePrison.remove(whitePrison.lastIndexOf(figure));
		case BLACK:
			return blackPrison.remove(blackPrison.lastIndexOf(figure));
		default:
			throw new InvalidParameterException("Figure does not belong to a Team!");
		}
	}

	private Vector2 getKingStartPosition(TeamType team) {
		int y;
		switch (team) {
		case BLACK:
			y = size - 1;
			break;
		case WHITE:
			y = 0;
			break;
		default:
			throw new InvalidParameterException();
		}
		int x = size / 2 + 1;
		return new Vector2(x, y);
	}

	private void placeFigures() {
	}

	private ArrayList<Vector2> calculatePossibleTurnsFor(Vector2 pos) {
		TreeSet<Vector2> possibleTurns = new TreeSet<Vector2>();
		if (ensureTeamTypeForPosition(pos) == TeamType.NONE) {
			throw new InvalidParameterException("No valid figure at the given position!");
		}
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

	private boolean isOutOfBounds(Vector2 pos) {
		return pos.getX() < 0 || pos.getY() < 0 || pos.getX() >= size || pos.getY() >= size;
	}

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
		ArrayList<Vector2> enemyTeam = new FigureSelector(team).selectAliveTeam(board);
		for (Vector2 ePos : enemyTeam) {
			if (ePos.equals((team == TeamType.WHITE) ? blackKingPos : whiteKingPos)) {
				turns.removeIf((v) -> board[ePos.getX()][ePos.getY()].isValidMove(v));
			} else {
				turns.removeIf((v) -> board[ePos.getX()][ePos.getY()].isValidMove(v));// to replace by can move there
			}
		}
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

	private TeamType ensureTeamTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getTeam() : TeamType.NONE;
	}

	private FigureType ensureFigureTypeForPosition(Vector2 pos) {
		Figure temp = board[pos.getX()][pos.getY()];
		return (temp != null) ? temp.getType() : FigureType.UNDEFINED;
	}
}
