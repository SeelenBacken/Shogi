package dataclasses;

import enums.TeamType;

public class Player {

	private String name;
	private final TeamType team;

	public Player(TeamType team) {
		this.name = "Player";
		this.team = team;
	}

	protected Player(TeamType team, String name) {
		this.team = team;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public TeamType getTeam() {
		return team;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2 getTurn(Vector2 figur, GameBoard board) {
		return figur;
	}
}
