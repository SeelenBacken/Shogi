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
		return (int) (arg1.length() - arg0.length());
	}
}
