package util;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import dataclasses.Figure;
import dataclasses.Vector2;
import enums.TeamType;

public class FigureSelector {

	private final TeamType enemyTeam;

	public FigureSelector(TeamType enemyTeam) {
		if (enemyTeam == TeamType.NONE) {
			throw new InvalidParameterException("To selected team can not be NONE!");
		}
		this.enemyTeam = enemyTeam;
	}

	public ArrayList<Vector2> selectAliveTeam(Figure[][] board) {
		ArrayList<Vector2> team = new ArrayList<Vector2>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null && board[x][y].getTeam() != enemyTeam) {
					team.add(new Vector2(x, y));
				}
			}
		}
		return team;
	}
}
