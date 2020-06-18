package Beans;

public class Position {

	private int x;
	private int y;
	
	public Position() {
		this.x = -1;
		this.y = -1;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		if(x >= 0 && x <= 9) {
			this.x = x;
		} else {
			new Exception("Not-on-GameBoard-Exception for x = " + x).printStackTrace();
		}
	}
	
	public void setY(int y) {
		if(y >= 0 && y <= 9) {
			this.y = y;
		} else {
			new Exception("Not-on-GameBoard-Exception for y = " + y).printStackTrace();
		}
	}
	
	public void setPos(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
}
