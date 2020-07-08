package util;

import java.util.ArrayList;
import java.util.List;

import dataclasses.Vector2;

public class CheckMap {
	private final ArrayList<List<Vector2>> paths;
	private final ArrayList<Integer> priorities;
	private final ArrayList<Vector2> crucialPositions;

	public CheckMap() {
		paths = new ArrayList<List<Vector2>>();
		priorities = new ArrayList<Integer>();
		crucialPositions = new ArrayList<Vector2>();
	}

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

	public boolean hasCheckPath() {
		return paths.size() != 0;
	}

	public int getPathCount() {
		return paths.size();
	}

	public List<Vector2> getFirstPath() {
		return paths.get(0);
	}

	public boolean hasCrucialPosition() {
		return crucialPositions.size() != 0;
	}

	public int getCrucialPositionCount() {
		return crucialPositions.size();
	}

	public Vector2 getFirstCrucialPosition() {
		return crucialPositions.get(0);
	}

	public Vector2 getMostImportantCrucialPosition() {
		int prio = getHighestCrucialPositionOrder();
		return prio == -1 ? null : crucialPositions.get(priorities.indexOf(prio));
	}

	public int getHighestCrucialPositionOrder() {
		if (getCrucialPositionCount() == 0) {
			return -1;
		}
		int prio = -1;
		for (int priority : priorities) {
			prio = prio < priority ? priority : prio;
		}
		return prio;
	}
}
