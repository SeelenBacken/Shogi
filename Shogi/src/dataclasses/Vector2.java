package dataclasses;

/**
 * This class represents a standard two dimensional vector with some utility
 * methods, also includes some constants.
 * 
 * @see #X
 * @see #Y
 * @see #ZERO
 * @see #ONE
 * @see #NEG_ONE
 */
public class Vector2 {

	/**
	 * Vector on the x-axis with length 1.
	 */
	public static final Vector2 X = new Vector2(1, 0);

	/**
	 * Vector on the y-axis with length 1.
	 */
	public static final Vector2 Y = new Vector2(0, 1);

	/**
	 * The trivial vector.
	 */
	public static final Vector2 ZERO = new Vector2(0, 0);

	/**
	 * Identity vector.
	 */
	public static final Vector2 ONE = new Vector2(1, 1);

	/**
	 * Inverse of the identity vector.
	 */
	public static final Vector2 NEG_ONE = ONE.getInverted();

	private int x;
	private int y;

	/**
	 * The constructor of this class.
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The copy-constructor of this class, initialises a new instance of this class
	 * with the same values.
	 * 
	 * @param toCopy the vector that will be copied.
	 */
	public Vector2(Vector2 toCopy) {
		this(toCopy.x, toCopy.y);
	}

	/**
	 * The getter of the x-value.
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * The getter of the y-value.
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * The setter of the x-value.
	 * 
	 * @param x new value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * The setter of the y-value.
	 * 
	 * @param y new value
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Instantiates a new vector based on this instance with the x-value negated.
	 * 
	 * @return a new instance having the x-values inverted.
	 */
	public Vector2 getXInverted() {
		return new Vector2(-x, y);
	}

	/**
	 * Instantiates a new vector based on this instance with the y-value negated.
	 * 
	 * @return a new instance having the y-values inverted.
	 */
	public Vector2 getYInverted() {
		return new Vector2(x, -y);
	}

	/**
	 * Instantiates a new vector based on this instance with both values negated.
	 * 
	 * @return a new instance with inverted values.
	 */
	public Vector2 getInverted() {
		return new Vector2(-x, -y);
	}

	/**
	 * Calculates the length of the vector.
	 * 
	 * @return the length
	 */
	public double length() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Shortens the vector if {@link #isDiagonal()} or {@link #isAxisVector()}
	 * {@code returns true}.
	 */
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

	/**
	 * Copies a vector and tries to shorten it.
	 * 
	 * @return the copy of the vector that may be shortened.
	 * 
	 * @see #shortenIfPossible()
	 */
	public Vector2 getNewShortenedVectorIfPossible() {
		Vector2 copy = new Vector2(this);
		copy.shortenIfPossible();
		return copy;
	}

	/**
	 * 
	 * 
	 * @return {@link #equals(Object)} for this instance and {@link #ZERO}.
	 */
	public boolean isZero() {
		return this.equals(ZERO);
	}

	/**
	 * Evaluates if {@link #isZero()} {@code returns false} and the absolutes of the
	 * coordinates are equal.
	 * 
	 * @return {@code true} if and only if the vector is diagonal.
	 */
	public boolean isDiagonal() {
		return !isZero() && Math.abs(x) == Math.abs(y);
	}

	/**
	 * Evaluates if this instance is a multiple of {@link #X}, more formally
	 * evaluates if the y-value equals zero and the x-value does not.
	 * 
	 * @return {@code true} if and only the y-value <b>is {@code zero} and the
	 *         x-value is not {@code zero}</b>, {@code false} otherwise.
	 */
	public boolean isXVector() {
		return y == 0 && x != 0;
	}

	/**
	 * Evaluates if the given instance is a multiple of {@link #Y}, more formally
	 * evaluates if the x-value equals zero and the y-value does not.
	 * 
	 * @return {@code true} if and only the x-value <b>is {@code zero} and the
	 *         y-value is not {@code zero}</b>, {@code false} otherwise.
	 */
	public boolean isYVector() {
		return x == 0 && y != 0;
	}

	/**
	 * Evaluates if either {@link #isXVector()} or {@link #isYVector()} returns true
	 * for this instance.
	 * 
	 * @return true if either {@link #isXVector()} or
	 */
	public boolean isAxisVector() {
		return isXVector() || isYVector();
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	/**
	 * Considers this instance equal to any other instance of this class where the
	 * x-values and the y-values are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2) {
			Vector2 temp = (Vector2) obj;
			return x == temp.x && y == temp.y;
		}
		return false;
	}

	/**
	 * Adds 2 vectors together.
	 * 
	 * @param v0 first vector
	 * @param v1 second vector
	 * 
	 * @return the result as a new instance.
	 */
	public static Vector2 add(Vector2 v0, Vector2 v1) {
		return new Vector2(v0.x + v1.x, v0.y + v1.y);
	}

	/**
	 * Subtracts the second from the first vector.
	 * 
	 * @param v0 first vector
	 * @param v1 second vector
	 * 
	 * @return the result as a new instance.
	 */
	public static Vector2 sub(Vector2 v0, Vector2 v1) {
		return new Vector2(v0.x - v1.x, v0.y - v1.y);
	}
}
