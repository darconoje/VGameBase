package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.model.Audit;

@Repository("auditDao")
@Transactional
public class AuditDaoImpl extends AbstractDao<Integer, Audit> implements AuditDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> findAll() {
		try {
			List<Audit> audits = getEntityManager().createQuery("SELECT a FROM Audit a ORDER BY id ").getResultList();
			return audits;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public Audit findByPk(long key) {
		return getByKey(key);
	}
	
	@Override
	public Audit save(Audit a) {
		if (findByPk(a.getId()) == null) {
			getEntityManager().persist(a);
		}
		return a;
	}
	
	public void update(Audit a) {
		super.update(a);
	}
	
	public void delete(Audit a) {
		super.delete(a);
	}
	
}
