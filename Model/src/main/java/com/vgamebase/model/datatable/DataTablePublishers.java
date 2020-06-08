package com.vgamebase.model.datatable;

import java.util.List;

import com.vgamebase.model.Publisher;

public class DataTablePublishers {
	
	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<Publisher> publishers;
	
	public DataTablePublishers() {
		
	}

	public DataTablePublishers(int draw, long recordsTotal, long recordsFiltered, List<Publisher> publishers) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.publishers = publishers;
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

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

}
