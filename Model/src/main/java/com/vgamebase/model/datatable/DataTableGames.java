package com.vgamebase.model.datatable;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.GamePlatform;

public class DataTableGames {

	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<GamePlatform> games;
	private Map<String, List<?>> filters;
	
	public DataTableGames() {
		
	}

	public DataTableGames(int draw, long recordsTotal, long recordsFiltered, List<GamePlatform> games,
			Map<String, List<?>> filters) {
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.games = games;
		this.filters = filters;
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

	public List<GamePlatform> getGames() {
		return games;
	}

	public void setGames(List<GamePlatform> games) {
		this.games = games;
	}

	public Map<String, List<?>> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, List<?>> filters) {
		this.filters = filters;
	}
	
}
