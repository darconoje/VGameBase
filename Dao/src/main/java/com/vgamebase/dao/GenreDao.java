package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.Genre;

public interface GenreDao {

	List<Genre> findAll();

	Genre findByPk(long key);

	Genre findByName(String name);

	Genre save(Genre g);

	void update(Genre g);

	void delete(Genre g);

	long totalGenres();

	long totalGenresFilter(Map<String, Object> params);

	List<Genre> paginationGenreInit(int start, int recordsPerPage);

	List<Genre> paginationGenreFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Genre> paginationGenreOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Genre> paginationGenreFilterAndOrder(int start, Map<String, Object> params, String order, String ascDesc,
			int recordsPerPage);

}
