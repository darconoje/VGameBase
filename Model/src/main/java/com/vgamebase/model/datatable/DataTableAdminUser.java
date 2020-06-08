package com.vgamebase.model.datatable;

import java.util.List;

import com.vgamebase.model.User;

public class DataTableAdminUser {
	
	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<User> users;
	
	public DataTableAdminUser() {
		
	}

	public DataTableAdminUser(int draw, long recordsTotal, long recordsFiltered, List<User> users) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.users = users;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
