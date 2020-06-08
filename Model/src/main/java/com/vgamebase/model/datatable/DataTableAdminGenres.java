package com.vgamebase.model.datatable;

import java.util.List;

import com.vgamebase.model.Genre;

public class DataTableAdminGenres {

	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<Genre> genres;

	public DataTableAdminGenres() {

	}

	public DataTableAdminGenres(int draw, long recordsTotal, long recordsFiltered, List<Genre> genres) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.genres = genres;
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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

}
