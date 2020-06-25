package dataclasses;

public class Vector2 {

	public static final Vector2 X = new Vector2(1, 0);
	public static final Vector2 Y = new Vector2(0, 1);
	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 ONE = new Vector2(1, 1);
	public static final Vector2 NEG_ONE = ONE.getInverted();

	private int x;
	private int y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 toCopy) {
		this(toCopy.x, toCopy.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2) {
			Vector2 temp = (Vector2) obj;
			return x == temp.x && y == temp.y;
		}
		return false;
	}

	public Vector2 getXInverted() {
		return new Vector2(-x, y);
	}

	public Vector2 getYInverted() {
		return new Vector2(x, -y);
	}

	public Vector2 getInverted() {
		return new Vector2(-x, -y);
	}

	public void shortenIfPossible() {
		if (isDiagonal()) {
			x /= Math.abs(x);
			y /= Math.abs(y);
		} else if (isXVector()) {
			x /= Math.abs(x);
		} else if (isYVector()) {
			y /= Math.abs(y);
		}
	}

	public Vector2 getNewShortenedVectorIfPossible() {
		Vector2 copy = new Vector2(this);
		copy.shortenIfPossible();
		return copy;
	}

	public boolean isZero() {
		return this.equals(ZERO);
	}

	public boolean isDiagonal() {
		return !this.equals(ZERO) && Math.abs(x) == Math.abs(y);
	}

	public boolean isXVector() {
		return y == 0 && x != 0;
	}

	public boolean isYVector() {
		return x == 0 && y != 0;
	}

	public boolean isAxisVector() {
		return isXVector() || isYVector();
	}

	public static Vector2 add(Vector2 v0, Vector2 v1) {
		return new Vector2(v0.x + v1.x, v0.y + v1.y);
	}

	public static Vector2 sub(Vector2 v0, Vector2 v1) {
		return new Vector2(v0.x - v1.x, v0.y - v1.y);
	}
}
