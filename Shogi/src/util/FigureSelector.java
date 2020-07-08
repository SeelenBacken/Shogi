package util;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import dataclasses.Figure;
import dataclasses.Vector2;
import enums.TeamType;

public class FigureSelector {

	private FigureSelector() {
	}

	public static ArrayList<Vector2> selectAliveTeam(Figure[][] board, TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("To selected team can not be NONE!");
		}
		ArrayList<Vector2> selectedTeam = new ArrayList<Vector2>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null && board[x][y].getTeam() == team) {
					selectedTeam.add(new Vector2(x, y));
				}
			}
		}
		return selectedTeam;
	}

	public static ArrayList<Vector2> selectAliveEnemyTeam(Figure[][] board, TeamType team) {
		if (team == TeamType.NONE) {
			throw new InvalidParameterException("To selected team can not be NONE!");
		}
		ArrayList<Vector2> selectedTeam = new ArrayList<Vector2>();
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null && board[x][y].getTeam() != team) {
					selectedTeam.add(new Vector2(x, y));
				}
			}
		}
		return selectedTeam;
	}
}
