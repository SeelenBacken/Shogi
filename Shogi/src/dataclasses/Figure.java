package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import enums.FigureType;
import enums.TeamType;

/**
 * This class represents a figure on the game board. This class can only be
 * instantiated using the static methods listed below, to ensure correct that
 * every figure is valid.
 * 
 * @see FigureType
 * @see TeamType
 * 
 * @see #Figure(FigureType, FigureType, TeamType)
 * @see #getKing(TeamType)
 * @see #getTower(TeamType)
 * @see #getBishop(TeamType)
 * @see #getGoldenGeneral(TeamType)
 * @see #getSilverGeneral(TeamType)
 * @see #getKnight(TeamType)
 * @see #getLance(TeamType)
 * @see #getPawn(TeamType)
 * @see #getDragon(TeamType)
 * @see #getHorse(TeamType)
 */
public class Figure {

	private FigureType type;
	private FigureType changeType;

	private final TeamType team;

	/**
	 * The constructor of this class, cannot be invoked directly only by using the
	 * static getFigur methods.
	 * 
	 * @param type       of the figure
	 * @param changeType if the figur can be upgraded or has to be degraded the it
	 *                   will be changed to this using {@link #changeType()}.
	 * @param team       of this Figure if set to {@link TeamType#NONE} the figure
	 *                   will not be considered as valid.
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #getKing(TeamType)
	 * @see #getTower(TeamType)
	 * @see #getBishop(TeamType)
	 * @see #getGoldenGeneral(TeamType)
	 * @see #getSilverGeneral(TeamType)
	 * @see #getKnight(TeamType)
	 * @see #getLance(TeamType)
	 * @see #getPawn(TeamType)
	 * @see #getDragon(TeamType)
	 * @see #getHorse(TeamType)
	 */
	private Figure(FigureType type, FigureType changeType, TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("Invalid team for a figur: " + TeamType.NONE);
		}
		this.type = type;
		this.changeType = changeType;
		this.team = team;
	}

	/**
	 * Getter of the type.
	 * 
	 * @return the type
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public FigureType getType() {
		return type;
	}

	/**
	 * Getter of the changeType.
	 * 
	 * @return the changeType
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public FigureType getChangeType() {
		return changeType;
	}

	public TeamType getTeam() {
		return team;
	}

	/**
	 * Considers an instance of this class equal to any instance of this class for
	 * which the type, the changeType and the team are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Figure) {
			Figure temp = (Figure) obj;
			return type == temp.type && changeType == temp.changeType && team == temp.team;
		}
		return false;
	}

	/**
	 * Changes the type of this figure by switching the type with the changeType if
	 * {@link #canChangeType()} returns true, more formally changes the type of this
	 * figure if and only if <b>{@code changeType != FigureType.UNDEFINED}</b> by
	 * swapping them.
	 * 
	 * @see FigureType
	 * @see #canChangeType()
	 */
	public void changeType() {
		if (canChangeType()) {
			FigureType temp = type;
			type = changeType;
			changeType = temp;
		}
	}

	/**
	 * Evaluates if this figure can move from the currPos to the targetPos on the
	 * board by calling
	 * {@link #canMoveThere(Vector2, Vector2, GameBoard, ArrayList)} with the last
	 * parameter set to {@code null}.
	 * 
	 * @param currPos   the current position of this figure on the board.
	 * @param targetPos the target position or more formally the absolute position
	 *                  on the board where this figure shall move at.
	 * @param board     the board this figure stands on.
	 * 
	 * @return {@code true} if and only if the path is not obstructed and the
	 *         targetPos is empty or at least occupied by an enemy, otherwise
	 *         {@code false}.
	 * 
	 * @throws InvalidParameterException if currPos, targetPos or board is null.
	 * 
	 * @see #canMoveThere(Vector2, Vector2, GameBoard, ArrayList)
	 */
	public boolean canMoveThere(Vector2 currPos, Vector2 targetPos, GameBoard board) {
		return canMoveThere(currPos, targetPos, board, null);
	}

	/**
	 * Evaluates if this figure can move from the currPos to and on the target
	 * position. If path is not set to {@code null} all absolute Positions the path
	 * is made of will be added to this.
	 * 
	 * @param currPos   the current position of this figure on the board.
	 * @param targetPos the target position or more formally the absolute position
	 *                  on the board where this figure shall move at.
	 * @param board     the board this figure stands on.
	 * @param path      the path this figure will have to walk to the targetPos (not
	 *                  including this, due to this should always be known) will be
	 *                  added to this if not set to {@code null}.
	 * 
	 * @return {@code true} if and only if the path is not obstructed and the
	 *         targetPos is empty or at least occupied by an enemy, otherwise
	 *         {@code false}.
	 * 
	 * @throws InvalidParameterException if currPos, targetPos or board is null.
	 */
	public boolean canMoveThere(Vector2 currPos, Vector2 targetPos, GameBoard board, ArrayList<Vector2> path) {
		if (currPos == null) {
			throw new InvalidParameterException("currPos cannot be null!");
		}
		if (targetPos == null) {
			throw new InvalidParameterException("targetPos cannot be null!");
		}
		if (board == null) {
			throw new InvalidParameterException("board cannot be null!");
		}
		Vector2 move = Vector2.sub(targetPos, currPos);
		if (isValidMove(move) == false) {
			return false;
		}
		boolean calculatePath = path != null;
		move.shortenIfPossible();
		Vector2 currPosCopy = Vector2.add(currPos, move);
		for (; !currPosCopy.equals(targetPos); currPosCopy = Vector2.add(currPosCopy, move)) {
			if (board.ensureTeamTypeForPosition(currPosCopy) == TeamType.NONE) {
				if (calculatePath) {
					path.add(new Vector2(currPosCopy));
				}
				continue;
			}
			return false;
		}
		return team != board.ensureTeamTypeForPosition(currPosCopy);
	}

	/**
	 * Gets the relative file path of the picture. depending on type and changeType,
	 * as a String
	 * 
	 * @return the relative file path of the picture as a String
	 */
	public String getPicturePath() {		
		if (team == TeamType.NONE) {
			throw new IllegalStateException("Team of this figure is " + TeamType.NONE + "!");
		}
		String path = "../res/half/";
		outer: switch (type) {
		case KING:
			path += ((team == TeamType.WHITE) ? "king" : "jewel");
			break;
		case TOWER:
			path += "tower";
			break;
		case BISHOP:
			path += "bishop";
			break;
		case GOLDEN_GENERAL:
			switch (changeType) {
			case SILVER_GENERAL:
				path += "prosilver";
				break outer;
			case KNIGHT:
				path += "proknight";
				break outer;
			case LANCE:
				path += "prolance";
				break outer;
			case PAWN:
				path += "tokin";
				break outer;
			default:
				path += "gold";
				break outer;
			}
		case SILVER_GENERAL:
			path += "silver";
			break;
		case KNIGHT:
			path += "knight";
			break;
		case LANCE:
			path += "lance";
			break;
		case PAWN:
			path += "pawn";
			break;
		case DRAGON:
			path += "dragon";
			break;
		case HORSE:
			path += "horse";
			break;
		default:
			throw new IllegalStateException("Figure type is " + FigureType.UNDEFINED + "!");
		}
		return path += ".png";
	}

	/**
	 * Evaluates if a move is valid more formally if the relative {@link Vector2}
	 * move is valid for this figure's type as defined in the game rules.
	 * 
	 * @param move a relative {@link Vector2} representing the move.
	 * 
	 * @return {@code true} if and only if the move is valid, otherwise
	 *         {@code false}.
	 */
	public boolean isValidMove(Vector2 move) {
		if (move == null) {
			throw new InvalidParameterException("Move is not allowed to be null!");
		}
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("Move cannot be valid if the figure does not belong to a Team!");
		} else if (team == TeamType.BLACK) {
			move = move.getInverted();
		}
		switch (type) {
		case KING:
			return (move.isDiagonal() || move.isAxisVector()) && move.equals(move.getNewShortenedVectorIfPossible());
		case TOWER:
			return move.isAxisVector();
		case BISHOP:
			return move.isDiagonal();
		case GOLDEN_GENERAL:
			return move.equals(Vector2.Y) || move.equals(Vector2.ONE) || move.equals(Vector2.X)
					|| move.equals(Vector2.Y.getInverted()) || move.equals(Vector2.X.getInverted())
					|| move.equals(Vector2.ONE.getXInverted());
		case SILVER_GENERAL:
			return move.equals(Vector2.Y) || move.equals(Vector2.ONE) || move.equals(Vector2.ONE.getYInverted())
					|| move.equals(Vector2.NEG_ONE) || move.equals(Vector2.ONE.getXInverted());
		case KNIGHT:
			return move.equals(new Vector2(-1, 2)) || move.equals(new Vector2(2, 1));
		case LANCE:
			return move.getNewShortenedVectorIfPossible().equals(Vector2.Y);
		case PAWN:
			return move.equals(Vector2.Y);
		case DRAGON:
			return move.isAxisVector() || move.equals(Vector2.ONE) || move.equals(Vector2.ONE.getYInverted())
					|| move.equals(Vector2.NEG_ONE) || move.equals(Vector2.ONE.getXInverted());
		case HORSE:
			return move.isDiagonal() || move.equals(Vector2.Y) || move.equals(Vector2.X)
					|| move.equals(Vector2.Y.getInverted()) || move.equals(Vector2.X.getInverted());
		default:
			throw new UnsupportedOperationException("If FigureType is undefined!");
		}
	}

	/**
	 * Evaluates if this figure can be upgraded or degraded, more formally if
	 * {@code changeType != FigureType.UNDEFINED}.
	 * 
	 * @return {@code true} if and only if
	 *         {@code changeType != FigureType.UNDEFINED}, otherwise {@code false}.
	 */
	private boolean canChangeType() {
		return changeType != FigureType.UNDEFINED;
	}

	/**
	 * Gets a valid instance of this class representing a king according to the game
	 * rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the king belongs to.
	 * 
	 * @return a valid king
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getKing(TeamType team) {
		return new Figure(FigureType.KING, FigureType.UNDEFINED, team);
	}

	/**
	 * Gets a valid instance of this class representing a tower according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the tower belongs to.
	 * 
	 * @return a valid tower
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getTower(TeamType team) {
		return new Figure(FigureType.TOWER, FigureType.DRAGON, team);
	}

	/**
	 * Gets a valid instance of this class representing a bishop according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the bishop belongs to.
	 * 
	 * @return a valid bishop
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getBishop(TeamType team) {
		return new Figure(FigureType.BISHOP, FigureType.HORSE, team);
	}

	/**
	 * Gets a valid instance of this class representing a golden general according
	 * to the game rules belonging to the team. Has to be used instead of
	 * Constructor.
	 * 
	 * @param team the team the golden general belongs to.
	 * 
	 * @return a valid golden general
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getGoldenGeneral(TeamType team) {
		return new Figure(FigureType.GOLDEN_GENERAL, FigureType.UNDEFINED, team);
	}

	/**
	 * Gets a valid instance of this class representing a silver general according
	 * to the game rules belonging to the team. Has to be used instead of
	 * Constructor.
	 * 
	 * @param team the team the silver general belongs to.
	 * 
	 * @return a valid silver general
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getSilverGeneral(TeamType team) {
		return new Figure(FigureType.SILVER_GENERAL, FigureType.GOLDEN_GENERAL, team);
	}

	/**
	 * Gets a valid instance of this class representing a knight according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the knight belongs to.
	 * 
	 * @return a valid knight
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getKnight(TeamType team) {
		return new Figure(FigureType.KNIGHT, FigureType.GOLDEN_GENERAL, team);
	}

	/**
	 * Gets a valid instance of this class representing a lance according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the lance belongs to.
	 * 
	 * @return a valid lance
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getLance(TeamType team) {
		return new Figure(FigureType.LANCE, FigureType.GOLDEN_GENERAL, team);
	}

	/**
	 * Gets a valid instance of this class representing a pawn according to the game
	 * rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the pawn belongs to.
	 * 
	 * @return a valid pawn
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getPawn(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.GOLDEN_GENERAL, team);
	}

	/**
	 * Gets a valid instance of this class representing a dragon according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the dragon belongs to.
	 * 
	 * @return a valid dragon
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getDragon(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.UNDEFINED, team);
	}

	/**
	 * Gets a valid instance of this class representing a horse according to the
	 * game rules belonging to the team. Has to be used instead of Constructor.
	 * 
	 * @param team the team the horse belongs to.
	 * 
	 * @return a valid horse
	 * 
	 * @throws InvalidParameterException if the team is set as {@link TeamType#NONE}
	 * 
	 * @see #Figure(FigureType, FigureType, TeamType)
	 */
	public static Figure getHorse(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.UNDEFINED, team);
	}
}
