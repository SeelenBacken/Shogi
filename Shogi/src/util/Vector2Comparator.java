package util;

import java.util.Comparator;

import dataclasses.Vector2;

/**
 * An implementation of the {@link Comparator} for the type {@link Vector2}.<br>
 * <br>
 * Compares the two instances by their length.
 *
 * @see #compare(Vector2, Vector2)
 * @see Vector2
 * @see Vector2#length()
 * @see Comparator
 */
public class Vector2Comparator implements Comparator<Vector2> {

	@Override
	public int compare(Vector2 arg0, Vector2 arg1) {
		int dist = (int) Math.round(arg1.length() - arg0.length());
		if (dist == 0) {
			if (arg1.equals(arg0.getInverted())) {
				return -1;
			} else if (arg1.getX() != arg0.getX()) {
				return arg1.getX() - arg0.getX();
			}
			return arg1.getY() - arg0.getY();
		}
		return dist;
	}
}
