package dataclasses;

import java.util.ArrayList;
import java.util.Random;

import enums.TeamType;

public class AI extends Player {

	private final Random random;

	public AI(TeamType team) {
		super(team, "AI");
		random = new Random();
	}

	@Override
	public Vector2 getTurn(Vector2 figur, GameBoard board) {
		ArrayList<Vector2> temp = board.getPossibleTurnsFor(figur);
		return temp.get(random.nextInt(temp.size()));
	}
}
