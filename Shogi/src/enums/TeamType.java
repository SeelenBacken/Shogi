package enums;

import dataclasses.Figure;
import dataclasses.Player;

/**
 * Each instance of this enum represents the team a {@link Player} or a
 * {@link Figure} belongs to.
 * 
 */
public enum TeamType {

	/**
	 * Represents no belonging to either the black or the white team.
	 */
	NONE,

	/**
	 * Represents the second team.
	 */
	BLACK,

	/**
	 * Represents a the first team.
	 */
	WHITE
}
