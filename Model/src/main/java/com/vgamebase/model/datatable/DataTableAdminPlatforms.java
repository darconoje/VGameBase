package com.vgamebase.model.datatable;

import java.util.List;

import com.vgamebase.model.Platform;

public class DataTableAdminPlatforms {

	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<Platform> platforms;
	
	public DataTableAdminPlatforms() {
		
	}

	public DataTableAdminPlatforms(int draw, long recordsTotal, long recordsFiltered, List<Platform> platforms) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.platforms = platforms;
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

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}
	
}
