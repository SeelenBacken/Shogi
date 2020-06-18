package Enums;

import java.util.ArrayList;

import Beans.Move;

public enum Role {
	
	EMPTY("", "", null),
	NONE(null, null, null),
	KING("König", "王将", new Move[]{
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move()
	}),
	ROOK("Turm", "飛", new Move[] {
		new Move(),
		new Move(),
		new Move(),
		new Move()
	}, "Drache", "龍王", new Move[] {
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move()
	}),
	BISHOP("Läufer", "角行", new Move[] {
		new Move(),
		new Move(),
		new Move(),
		new Move()
	}, "Pferd", "龍馬", new Move[] {
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move()			
	}),
	GOLD("Goldener General", "金将", new Move[] {
			new Move(),
			new Move(),
			new Move(),
			new Move(),
			new Move(),
			new Move(),
	}),
	SILVER("Silberner General", "銀将", new Move[] {
		new Move(),
		new Move(),
		new Move(),
		new Move(),
		new Move()
	}, "Bevörderter silberner General", "成銀", Role.GOLD.moves),
	KNIGHT("Springer", "桂馬", new Move[] {
			new Move(),
			new Move()
	}, "Bevörderter Springer", "成桂", Role.GOLD.moves),
	LANCE("Lanze", "香車", new Move[] {
			new Move()
	}, "Bevörderte Lanze", "成香", Role.GOLD.moves),
	PAWN("Bauer","歩兵", new Move[] {
			new Move()
	}, "Beförderter Bauer", "と金", GOLD.getMoves());
	
	private String nameG;
	private String nameJ;
	private Move[] moves; 
	private String superRoleNameG;
	private String superRoleNameJ;
	private Move[] superRoleMoves;
	
	private Role(String nameG, String nameJ, Move[] moves, String superRoleNameG, String superRoleNameJ, Move[] superRoleMoves) {
		this.nameG = nameG;
		this.nameJ = nameJ;
		this.moves = moves;
		this.superRoleNameG = superRoleNameG;
		this.superRoleNameJ = superRoleNameJ;
		this.superRoleMoves = superRoleMoves;
	}
	
	private Role(String nameG, String nameJ, Move[] moves) {
		this.nameG = nameG;
		this.nameJ = nameJ;
		this.moves = moves;
		this.superRoleNameG = nameG;
		this.superRoleNameJ = nameJ;
		this.superRoleMoves = moves;
	}
	
	public String getNameG() {
		return this.nameG;
	}
	
	public String getNameJ() {
		return this.nameJ;
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
	
	public String getSuperRoleNameG() {
		return this.superRoleNameG;
	}
	
	public String getSuperRoleNameJ() {
		return this.superRoleNameJ;
	}
	
	public Move[] getSuperRoleMoves() {
		return this.superRoleMoves;
	}
	
	public Role upgrade() {
		Move[] help1;
		String help2;
		
		help1 = this.moves;
		this.moves = this.superRoleMoves;
		this.superRoleMoves = help1;
		
		help2 = this.nameG;
		this.nameG = this.superRoleNameG;
		this.superRoleNameG = help2;
		
		help2 = this.nameJ;
		this.nameJ = this.superRoleNameJ;
		this.superRoleNameJ = help2;
		
		return this;
	}
	
}
