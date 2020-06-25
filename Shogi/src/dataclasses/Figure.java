package dataclasses;

import java.security.InvalidParameterException;

import enums.FigureType;
import enums.TeamType;

public class Figure {
	private FigureType type;
	private FigureType changeType;

	private final TeamType team;

	public Figure(FigureType type, FigureType changeType, TeamType team) {
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
}
