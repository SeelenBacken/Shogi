package util;

import java.util.ArrayList;
import java.util.List;

import dataclasses.Vector2;

/**
 * <p>
 * This class is a utility class that can be used to safe a check situation and
 * test this situation for check mate.
 * </p>
 * 
 * <p>
 * This class safes the paths that threaten the king in the current situation
 * and creates crucial positions from them. Crucial positions have to be covert
 * first in order to prevent the king from being check mate if there are more
 * than one crucial position the check mate cannot be prevented, each crucial
 * position has a priority referring to the dimension of the problem(how many
 * paths intersect at the specific position).
 * </p>
 * 
 */
public class CheckMap {

	private final ArrayList<List<Vector2>> paths;
	private final ArrayList<Integer> priorities;
	private final ArrayList<Vector2> crucialPositions;

	/**
	 * The constructor of this class.
	 */
	public CheckMap() {
		paths = new ArrayList<List<Vector2>>();
		priorities = new ArrayList<Integer>();
		crucialPositions = new ArrayList<Vector2>();
	}

	/**
	 * Adds a path that causes a check to the paths and tests if there is an
	 * intersection with other paths and creates a crucial position from it or
	 * increases the priority of it.
	 * 
	 * @param path that will be added
	 * 
	 * @see CheckMap
	 */
	public void addPath(List<Vector2> path) {
		for (Vector2 position : path) {
			for (List<Vector2> listedPath : paths) {
				for (Vector2 contPos : listedPath) {
					if (position.equals(contPos)) {
						int idx = crucialPositions.indexOf(position);
						if (idx == -1) {
							priorities.add(2);
							crucialPositions.add(position);
						} else {
							priorities.set(idx, priorities.get(idx) + 1);
						}
					}
				}
			}
		}
		paths.add(path);
	}

	/**
	 * Getter of the paths.
	 * 
	 * @return the paths
	 */
	public ArrayList<List<Vector2>> getPaths() {
		return paths;
	}

	/**
	 * Evaluates if there are any added paths.
	 * 
	 * @return {@code true} if and only if there has no path been added,
	 *         {@code false} otherwise.
	 */
	public boolean hasCheckPath() {
		return paths.size() != 0;
	}

	/**
	 * Evaluates the number of paths.
	 * 
	 * @return the number of paths
	 */
	public int getPathCount() {
		return paths.size();
	}

	/**
	 * Gets the first path that has been added.
	 * 
	 * @return the first path
	 */
	public List<Vector2> getFirstPath() {
		return paths.get(0);
	}

	/**
	 * Evaluates if there is at least one crucial position.
	 * 
	 * @return {@code true} if there is at least one crucial position, {@code false}
	 *         otherwise.
	 * 
	 * @see CheckMap
	 */
	public boolean hasCrucialPosition() {
		return crucialPositions.size() != 0;
	}

	/**
	 * Evaluates the number of crucial positions.
	 * 
	 * @return the number of crucial positions
	 * 
	 * @see CheckMap
	 */
	public int getCrucialPositionCount() {
		return crucialPositions.size();
	}

	/**
	 * Gets the first crucial position that has been added.
	 * 
	 * @return the first crucial position
	 */
	public Vector2 getFirstCrucialPosition() {
		return crucialPositions.get(0);
	}

	/**
	 * Evaluates the Position with the highest priority, if two positions have the
	 * same priority the first will be the result
	 * 
	 * @return {@code null} if there are no crucial positions, the crucial position
	 *         with the highest priority.
	 * 
	 * @see CheckMap
	 */
	public Vector2 getMostImportantCrucialPosition() {
		int prio = getHighestCrucialPositionOrder();
		return prio == -1 ? null : crucialPositions.get(priorities.indexOf(prio));
	}

	/**
	 * Evaluates the highest priority of all crucial positions.
	 * 
	 * @return -1 if there are no crucial positions, the highest priority otherwise.
	 * 
	 * @see CheckMap
	 */
	public int getHighestCrucialPositionOrder() {
		if (!hasCrucialPosition()) {
			return -1;
		}
		int prio = -1;
		for (int priority : priorities) {
			prio = prio < priority ? priority : prio;
		}
		return prio;
	}
}
