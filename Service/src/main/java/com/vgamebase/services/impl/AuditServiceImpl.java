package com.vgamebase.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgamebase.dao.AuditDao;
import com.vgamebase.model.Audit;
import com.vgamebase.services.AuditService;

@Service("auditService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDao auditDao;
	
	public List<Audit> findAll(){
		return auditDao.findAll();
	}
	
	public Audit findByPk(long key) {
		return auditDao.findByPk(key);
	}
	
	public Audit save(Audit a) {
		return auditDao.save(a);
	}
	
	public void update(Audit a) {
		auditDao.update(a);
	}
	
	public void delete(Audit a) {
		auditDao.delete(a);
	}
	
}
