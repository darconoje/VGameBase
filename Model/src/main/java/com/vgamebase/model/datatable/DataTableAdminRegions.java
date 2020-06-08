package com.vgamebase.model.datatable;

import java.util.List;

import com.vgamebase.model.Region;

public class DataTableAdminRegions {
	
	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<Region> regions;
	
	public DataTableAdminRegions() {
		
	}

	public DataTableAdminRegions(int draw, long recordsTotal, long recordsFiltered, List<Region> regions) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.regions = regions;
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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
}
