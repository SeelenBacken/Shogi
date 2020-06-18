package Beans;

import java.util.ArrayList;
import Enums.Role;
import Enums.Team;

public class GameBoard {

	private Figure[][] field;
	private Position wKing;
	private Position bKing;
	private ArrayList<Figure> wJail;
	private ArrayList<Figure> bJail;
	
	public GameBoard() {
	
		this.field = new Figure[9][9];
		
		//Initializing the white figures
		
		field[0][0] = new Figure();
		field[0][0].setId(0);
		field[0][0].setT(Team.WHITE);
		field[0][0].setR(Role.LANCE);
		
		field[1][0] = new Figure();
		field[1][0].setId(1);
		field[1][0].setT(Team.WHITE);
		field[1][0].setR(Role.KNIGHT);
		
		field[2][0] = new Figure();
		field[2][0].setId(2);
		field[2][0].setT(Team.WHITE);
		field[2][0].setR(Role.SILVER);
		
		field[3][0] = new Figure();
		field[3][0].setId(3);
		field[3][0].setT(Team.WHITE);
		field[3][0].setR(Role.GOLD);
		
		field[4][0] = new Figure();
		field[4][0].setId(4);
		field[4][0].setT(Team.WHITE);
		field[4][0].setR(Role.KING);
		
		field[5][0] = new Figure();
		field[5][0].setId(5);
		field[5][0].setT(Team.WHITE);
		field[5][0].setR(Role.GOLD);
		
		field[6][0] = new Figure();
		field[6][0].setId(6);
		field[6][0].setT(Team.WHITE);
		field[6][0].setR(Role.SILVER);
		
		field[7][0] = new Figure();
		field[7][0].setId(7);
		field[7][0].setT(Team.WHITE);
		field[7][0].setR(Role.KNIGHT);
		
		field[8][0] = new Figure();
		field[8][0].setId(8);
		field[8][0].setT(Team.WHITE);
		field[8][0].setR(Role.LANCE);
		
		field[1][1] = new Figure();
		field[1][1].setId(9);
		field[1][1].setT(Team.WHITE);
		field[1][1].setR(Role.BISHOP);
		
		field[7][1] = new Figure();
		field[7][1].setId(10);
		field[7][1].setT(Team.WHITE);
		field[7][1].setR(Role.ROOK);
		
		for(int i = 0; i < field.length; i++) {
			field[i][2] = new Figure();
			field[i][2].setId(11 + i);
			field[i][2].setT(Team.WHITE);
			field[i][2].setR(Role.PAWN);
		}
		
		//Initializing the black figures
		
		field[0][8] = new Figure();
		field[0][8].setId(50);
		field[0][8].setT(Team.BLACK);
		field[0][8].setR(Role.LANCE);
		
		field[1][8] = new Figure();
		field[1][8].setId(51);
		field[1][8].setT(Team.BLACK);
		field[1][8].setR(Role.KNIGHT);
		
		field[2][8] = new Figure();
		field[2][8].setId(52);
		field[2][8].setT(Team.BLACK);
		field[2][8].setR(Role.SILVER);
		
		field[3][8] = new Figure();
		field[3][8].setId(53);
		field[3][8].setT(Team.BLACK);
		field[3][8].setR(Role.GOLD);
		
		field[4][8] = new Figure();
		field[4][8].setId(54);
		field[4][8].setT(Team.BLACK);
		field[4][8].setR(Role.KING);
		
		field[5][8] = new Figure();
		field[5][8].setId(55);
		field[5][8].setT(Team.BLACK);
		field[5][8].setR(Role.GOLD);
		
		field[6][8] = new Figure();
		field[6][8].setId(56);
		field[6][8].setT(Team.BLACK);
		field[6][8].setR(Role.SILVER);
		
		field[7][8] = new Figure();
		field[7][8].setId(57);
		field[7][8].setT(Team.BLACK);
		field[7][8].setR(Role.KNIGHT);
		
		field[8][8] = new Figure();
		field[8][8].setId(58);
		field[8][8].setT(Team.BLACK);
		field[8][8].setR(Role.LANCE);
		
		field[1][7] = new Figure();
		field[1][7].setId(59);
		field[1][7].setT(Team.BLACK);
		field[1][7].setR(Role.ROOK);
		
		field[7][7] = new Figure();
		field[7][7].setId(60);
		field[7][7].setT(Team.BLACK);
		field[7][7].setR(Role.BISHOP);
		
		for(int i = 0; i < field.length; i++) {
			field[i][6] = new Figure();
			field[i][6].setId(61 + i);
			field[i][6].setT(Team.BLACK);
			field[i][6].setR(Role.PAWN);
		}
		
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[i].length; j++) {
				if(field[i][j] == null) {
					field[i][j] = new Figure();
					field[i][j].setEmpty();
				}
			}
		}
		
		this.wKing = new Position();
		this.wKing.setPos(4, 0);
		
		this.bKing = new Position();
		this.bKing.setPos(4, 8);
		
		this.wJail = new ArrayList<>();
		this.bJail = new ArrayList<>();;
	}
	
	public Figure[][] getField(){
		return this.field;
	}
	
	public Position getWKing() {
		return this.wKing;
	}
	
	public Position getBKing() {
		return this.bKing;
	}
	
	public ArrayList<Figure> getWJail(){
		return this.wJail;
	}
	
	public ArrayList<Figure> getBJail(){
		return this.bJail;
	}
	
	public void setField(Figure[][] field) {
		this.field = field;
	}
	
	public void setWKing(Position wKing) {
		this.wKing = wKing;
	}
	
	public void setBKing(Position bKing) {
		this.bKing = bKing;
	}
	
	public void setWJail(ArrayList<Figure> wJail) {
		this.wJail = wJail;
	}
	
	public void addWJail(Figure b) {
		this.wJail.add(b);
	}
	
	public void removeWJail(Figure b) {
		this.wJail.remove(b);
	}
	
	public void setBJail(ArrayList<Figure> bJail) {
		this.bJail = bJail;
	}
	
	public void addBJail(Figure w) {
		this.bJail.add(w);
	}
	
	public void removeBJail(Figure w) {
		this.bJail.remove(w);
	}
	
	public void updateBoard(Position p, Move m) {
		
		Figure f = this.getField()[p.getX()][p.getY()];
		this.getField()[p.getX()][p.getY()].setEmpty();
		
		Position nPos = new Position();
		switch (m.getDirection()) {
		case 0:
			nPos = p;
			break;
		case 1:
			nPos.setPos(p.getX() - m.getMaxLength(), p.getY() + m.getMaxLength());
			break;
		case 2:
			nPos.setPos(p.getX(), p.getY() + m.getMaxLength());
			break;
		case 3:
			nPos.setPos(p.getX() + m.getMaxLength(), p.getY() + m.getMaxLength());
			break;
		case 4:
			nPos.setPos(p.getX() + m.getMaxLength(), p.getY());
			break;
		case 5:
			nPos.setPos(p.getX() + m.getMaxLength(), p.getY() - m.getMaxLength());
			break;
		case 6:
			nPos.setPos(p.getX(), p.getY() - m.getMaxLength());
			break;
		case 7:
			nPos.setPos(p.getX() - m.getMaxLength(), p.getY() - m.getMaxLength());
			break;
		case 8:
			nPos.setPos(p.getX() - m.getMaxLength(), p.getY());
			break;
		case 9:
			nPos.setPos(p.getX() - 1, p.getY() + m.getMaxLength());
			break;
		case 10:
			nPos.setPos(p.getX() + 1, p.getY() + m.getMaxLength());
			break;
		default:
			break;
		}
		
		if(this.field[nPos.getX()][nPos.getY()].isEmpty()) {
			this.field[nPos.getX()][nPos.getY()] = f;
		} else {
			Figure help = this.field[nPos.getX()][nPos.getY()];
			this.field[nPos.getX()][nPos.getY()] = f;
			if(help.getT() == Team.BLACK) {
				help.setT(Team.WHITE);
				this.wJail.add(help);
			} else if(help.getT() == Team.WHITE) {
				help.setT(Team.BLACK);
				this.bJail.add(help);
			}
		}
		
	}
	
	public void SetBoard(Figure[][] field, Position wKing, Position bKing, ArrayList<Figure> wJail, ArrayList<Figure> bJail) {
		this.field = field;
		this.wKing = wKing;
		this.bKing = bKing;
		this.wJail = wJail;
		this.bJail = bJail;
	}
	
}
