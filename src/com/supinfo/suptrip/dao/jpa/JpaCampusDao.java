package com.supinfo.suptrip.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.exception.ItemAlreadyExistException;
import com.supinfo.suptrip.exception.UnknownItemException;

public class JpaCampusDao implements CampusDao {

	private EntityManager em;
	public JpaCampusDao(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		em = emf.createEntityManager();
	}
	
	@Override
	public void addCampus(Campus campus) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(em.find(Campus.class, campus.getId()) != null) {
				throw new ItemAlreadyExistException(campus);
			}
			em.persist(campus);
			t.commit();
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}		
	}

	@Override
	public Campus findById(int id) {
		EntityTransaction t = em.getTransaction();
		try {
			Campus result = em.find(Campus.class, id);
			if ( result == null ){
				throw new UnknownItemException(Campus.class, id);
			}
			return result;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Campus findByName(String name) {
		
		Campus campus = new Campus();
		try {
			List<Campus> myQuery = em.createQuery("SELECT c FROM Campus AS c WHERE c.name='"+name+"'").getResultList();
			campus = myQuery.get(0);
		} finally {
			em.close();
		}
		return campus;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Campus> findAll() {
		List<Campus> myCampus = new ArrayList<Campus>();
		try {
			myCampus.addAll(em.createQuery("SELECT c FROM Campus AS c").getResultList());		
		} finally {
			em.close();
		}
		return myCampus;
	}

}
