package util;

import java.util.Comparator;

import dataclasses.Vector2;

public class Vector2Comparator implements Comparator<Vector2> {

	@Override
	public int compare(Vector2 arg0, Vector2 arg1) {
		return (int) Math.sqrt(Math.pow(arg0.getX() - arg1.getX(), 2) + Math.pow(arg0.getY() - arg1.getY(), 2));
	}
}
