package com.vgamebase.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vgamebase.dao.AbstractDao;
import com.vgamebase.dao.CommentDao;
import com.vgamebase.model.Comment;

@Repository("commentDao")
@Transactional
public class CommentDaoImpl extends AbstractDao<Integer, Comment> implements CommentDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findAll() {
		try {
			List<Comment> comments = getEntityManager().createQuery("SELECT c FROM Comment c ORDER BY id ").getResultList();
			return comments;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	public Comment findByPk(long key) {
		return getByKey(key);
	}
	
	@Override
	public Comment save(Comment c) {
		if (findByPk(c.getId()) == null) {
			getEntityManager().persist(c);
		}
		return c;
	}
	
	public void update(Comment c) {
		super.update(c);
	}
	
	public void delete(Comment c) {
		super.delete(c);
	}

}
