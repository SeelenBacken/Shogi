package Beans;

import java.util.ArrayList;

public class History {

	private GameBoard[] history;
	
	public History() {
		this.history = new GameBoard[10];
	}
	
	public GameBoard[] getHistory(){
		return this.history;
	}
	
	public void setHistory(GameBoard[] history) {
		this.history = history;
	}
	
	public void add(GameBoard g) {
		if(this.isFull()) {
			for(int i = 1; i < this.history.length; i++) {
				history[i-1] = history[i];
			}
			history[14] = g;
		} else {
			for(int i = 0; i < this.history.length; i++) {
				if(history[i] == null) {
					history[i] = g;
					break;
				}
			}
		}
	}
	
	public boolean isFull() {
		for(GameBoard b : this.history) {
			if(b == null) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		for(GameBoard b : this.history) {
			if(b != null) {
				return false;
			}
		}
		return true;
	}
	
}
