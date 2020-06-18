package Beans;

public class Move {
	
	/*
	 * The direction attribute hands an integer value based on the following matrix.
	 * The current position of a figure is 0. The direction is given by the numbers from 1 to 8.
	 * If the figure is a knight the direction 9 or 10 is given.
	 * The default value is 0 for staying on a position.
	 * 
	09|..|10
	--+--+--
	01|02|03
	--+--+--
	08|00|04
	--+--+--
	07|06|05
	*
	*/
	private int direction;
	
	/*
	 * The maxLength attribute hands back a integer value from 0 to 9 because the gameboard is 9x9 fields wide.
	 * The default value is 0 for staying on a position.
	 * 
	 * */
	private int maxLength;
	
	private boolean knight;
	
	public Move() {
		this.knight = false;
		this.direction = 0;
		this.maxLength = 0;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public int getMaxLength() {
		return this.maxLength;
	}
	
	public void setDirection(int direction) {
		if(this.knight == false) {
			if(direction >= 0 && direction <= 8) {
				this.direction = direction;
			} else {
				new Exception("No-Direction-Error for direction =" + direction).printStackTrace();
			}
		} else {
			if(direction == 9 | direction == 10) {
				this.direction = direction;
			} else {
				new Exception("No-Direction-Error for direction =" + direction).printStackTrace();
			}
		}
	}
	
	public void setMaxLength(int maxLength) {
		if(this.knight == false) {
			if(maxLength >= 0 && maxLength <= 9) {
				this.maxLength = maxLength;
			} else {
				new Exception("No-MaxLength-Error for maxLength =" + maxLength).printStackTrace();
			}
		} else {
			if(maxLength == 3) {
				this.maxLength = maxLength;
			} else {
				new Exception("No-MaxLength-Error for maxLength =" + maxLength).printStackTrace();
			}
		}
		
	}
	
	public boolean getKnight() {
		return this.knight;
	}
	
	public void setKnight(boolean knight) {
		this.knight = knight;
	}
	

}
