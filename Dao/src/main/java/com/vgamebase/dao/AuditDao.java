package com.vgamebase.dao;

import java.util.List;

import com.vgamebase.model.Audit;

public interface AuditDao {

	List<Audit> findAll();
	
	Audit findByPk(long key);
	
	Audit save(Audit a);
	
	void update(Audit a);
	
	void delete(Audit a);
	
}
