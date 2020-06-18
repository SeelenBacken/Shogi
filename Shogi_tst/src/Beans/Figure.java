package Beans;

import Enums.Role;
import Enums.Team;

public class Figure {
	
	private int id;
	private Team t;
	private Role r;
	
	public Figure() {
		this.id = -1;
		this.t = t.NONE;
		this.r = r.NONE;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Team getT() {
		return this.t;
	}
	
	public Role getR() {
		return this.r;
	}
	
	public void setId(int id) {
		if(this.id == -1) {
			this.id = id;
		} else {
			new Exception("Unchangeble-Exception for id=" + id + "\n + id is unchangeble").printStackTrace();
		}
	}
	
	public void setT(Team t) {
		this.t = t;
	}
	
	public void setR(Role r) {
		this.r = r;
	}
	
	public void setEmpty() {
		this.id = -1;
		this.r = Role.EMPTY;
		this.t = Team.NONE;
	}
	
	public boolean equals(Figure f) {
		if(this.id == f.id) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEmpty() {
		if(this.id == -1) {
			return true;
		} else {
			return false;
		}
	}
	
}
