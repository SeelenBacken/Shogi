package dataclasses;

import enums.TeamType;

/**
 * This class represents a player in a shogi game, a player has a name and a
 * team. The name has to be set by the setter in order to not have the player
 * name set to the default value "Player".
 * 
 */
public class Player {

	private String name;
	private final TeamType team;

	/**
	 * The public constructor of this class.
	 * 
	 * @param team of the player
	 */
	public Player(TeamType team) {
		this.name = "Player";
		this.team = team;
	}

	/**
	 * The protected constructor of this class used in the derived class {@link AI}.
	 * 
	 * @param team of the player
	 * @param name of the player
	 */
	protected Player(String name, TeamType team) {
		this.name = name;
		this.team = team;
	}

	/**
	 * Getter of the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of the team.
	 * 
	 * @return the team
	 */
	public TeamType getTeam() {
		return team;
	}

	/**
	 * Setter of the name.
	 * 
	 * @param name the new name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Only used for further implementation in the {@link AI} class. Should return
	 * true if a figure has moved.
	 * 
	 * @param board that contains the figure.
	 * 
	 * @return value indicating if the figure has been moved
	 * 
	 * @see AI#doTurn(GameBoard)
	 */
	public boolean doTurn(GameBoard board) {
		return false;
	}
}
