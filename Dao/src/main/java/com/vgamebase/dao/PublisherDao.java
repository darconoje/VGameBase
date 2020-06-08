package com.vgamebase.dao;

import java.util.List;
import java.util.Map;

import com.vgamebase.model.Publisher;

public interface PublisherDao {

	List<Publisher> findAll();

	Publisher findByPk(long key);

	Publisher findByName(String name);

	Publisher save(Publisher p);

	void update(Publisher p);

	void delete(Publisher p);

	long totalPublishers();

	long totalPublishersFilter(Map<String, Object> params);

	List<Publisher> paginationPublisherInit(int start, int recordsPerPage);

	List<Publisher> paginationPublisherFilterNoOrder(int start, Map<String, Object> params, int recordsPerPage);

	List<Publisher> paginationPublisherOrderNoFilter(int start, String order, String ascDesc, int recordsPerPage);

	List<Publisher> paginationPublisherFilterAndOrder(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage);
	
	List<Publisher> paginationPublisherInit2(int start, int recordsPerPage);
	
	List<Publisher> paginationPublisherFilterNoOrder2(int start, Map<String, Object> params, int recordsPerPage);

	List<Publisher> paginationPublisherOrderNoFilter2(int start, String order, String ascDesc, int recordsPerPage);

	List<Publisher> paginationPublisherFilterAndOrder2(int start, Map<String, Object> params, String order,
			String ascDesc, int recordsPerPage);	

}
