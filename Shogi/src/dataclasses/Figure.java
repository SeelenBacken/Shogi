package dataclasses;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import enums.FigureType;
import enums.TeamType;

public class Figure {
	private FigureType type;
	private FigureType changeType;

	private final TeamType team;

	private Figure(FigureType type, FigureType changeType, TeamType team) {
		this.type = type;
		this.changeType = changeType;
		this.team = team;
	}

	public FigureType getType() {
		return type;
	}

	public FigureType getChangeType() {
		return changeType;
	}

	public TeamType getTeam() {
		return team;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Figure) {
			Figure temp = (Figure) obj;
			return type == temp.type && changeType == temp.changeType && team == temp.team;
		}
		return false;
	}

	public void changeType() {
		if (canChangeType()) {
			FigureType temp = type;
			type = changeType;
			changeType = temp;
		}
	}

	public boolean canMoveThere(Vector2 currPos, Vector2 targetPos, GameBoard board) {
		return canMoveThere(currPos, targetPos, board, null);
	}

	public boolean canMoveThere(Vector2 currPos, Vector2 targetPos, GameBoard board, ArrayList<Vector2> path) {
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

	public boolean isValidMove(Vector2 move) {
		if (move == null) {
			throw new NullPointerException("Move is not allowed to be null!");
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

	private boolean canChangeType() {
		return changeType != FigureType.UNDEFINED;
	}

	public static Figure getKing(TeamType team) {
		return new Figure(FigureType.KING, FigureType.UNDEFINED, team);
	}

	public static Figure getTower(TeamType team) {
		return new Figure(FigureType.TOWER, FigureType.DRAGON, team);
	}

	public static Figure getBishop(TeamType team) {
		return new Figure(FigureType.BISHOP, FigureType.HORSE, team);
	}

	public static Figure getGoldenGeneral(TeamType team) {
		return new Figure(FigureType.GOLDEN_GENERAL, FigureType.UNDEFINED, team);
	}

	public static Figure getSilverGeneral(TeamType team) {
		return new Figure(FigureType.SILVER_GENERAL, FigureType.GOLDEN_GENERAL, team);
	}

	public static Figure getKnight(TeamType team) {
		return new Figure(FigureType.KNIGHT, FigureType.GOLDEN_GENERAL, team);
	}

	public static Figure getLance(TeamType team) {
		return new Figure(FigureType.LANCE, FigureType.GOLDEN_GENERAL, team);
	}

	public static Figure getPawn(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.GOLDEN_GENERAL, team);
	}

	public static Figure getDragon(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.UNDEFINED, team);
	}

	public static Figure getHorse(TeamType team) {
		return new Figure(FigureType.PAWN, FigureType.UNDEFINED, team);
	}
}
