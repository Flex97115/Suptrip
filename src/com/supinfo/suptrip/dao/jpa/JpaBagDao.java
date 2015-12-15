package com.supinfo.suptrip.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.supinfo.suptrip.dao.BagDao;
import com.supinfo.suptrip.entity.Bag;
import com.supinfo.suptrip.exception.ItemAlreadyExistException;
import com.supinfo.suptrip.exception.UnknownItemException;

public class JpaBagDao implements BagDao {
	
	private EntityManager em;
	
	public JpaBagDao(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		em = emf.createEntityManager();
	}
	
	@Override
	public void addBag(Bag bag) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(em.find(Bag.class, bag.getId()) != null) {
				throw new ItemAlreadyExistException(bag);
			}
			em.persist(bag);
			t.commit();
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}		
	}
	
	@Override
	public void deleteBag(Bag bag) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.createQuery("DELETE FROM Bag b WHERE b.id="+bag.getId()).executeUpdate();
			t.commit();
		} catch (IllegalArgumentException e) {
			throw new UnknownItemException(Bag.class, bag.getId());
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}		
	}

	@Override
	public Bag findById(int id) {
		EntityTransaction t = em.getTransaction();
		try {
			Bag result = em.find(Bag.class, id);
			if ( result == null ){
				throw new UnknownItemException(Bag.class, id);
			}
			return result;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bag> findByUserId(int userId) {
		
		List<Bag> myBags = new ArrayList<Bag>();
		try {
			myBags.addAll(em.createQuery("SELECT b FROM Bag AS b WHERE b.user="+userId).getResultList());		
		} finally {
			em.close();
		}
		return myBags;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bag> findBytripId(int tripId) {
		List<Bag> myBags = new ArrayList<Bag>();
		try {
			myBags.addAll(em.createQuery("SELECT b FROM Bag AS b WHERE b.trip_fk="+tripId).getResultList());		
		} finally {
			em.close();
		}
		return myBags;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bag> findByTripAndUserId(int userId, int tripId) {
		List<Bag> myBags = new ArrayList<Bag>();
		try {
			myBags.addAll(em.createQuery("SELECT b FROM Bag AS b WHERE b.trip_fk="+tripId+" AND b.user="+userId).getResultList());		
		} finally {
			em.close();
		}
		return myBags;
	}

}
