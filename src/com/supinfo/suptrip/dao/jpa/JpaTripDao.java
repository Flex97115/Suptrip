package com.supinfo.suptrip.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.supinfo.suptrip.dao.TripDao;
import com.supinfo.suptrip.entity.Trip;
import com.supinfo.suptrip.exception.ItemAlreadyExistException;
import com.supinfo.suptrip.exception.UnknownItemException;

public class JpaTripDao implements TripDao {

	private EntityManager em;
	public JpaTripDao(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		em = emf.createEntityManager();
	}

	@Override
	public void addTrip(Trip trip) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(em.find(Trip.class, trip.getId()) != null) {
				throw new ItemAlreadyExistException(trip);
			}
			em.persist(trip);
			t.commit();
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}	

	}
	
	@Override
	public void deleteTrip(Trip trip) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.createQuery("DELETE FROM Trip t WHERE t.id="+trip.getId()).executeUpdate();
			t.commit();
		} catch (IllegalArgumentException e) {
			throw new UnknownItemException(Trip.class, trip.getId());
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}

	}

	@Override
	public Trip findById(int id) {
		EntityTransaction t = em.getTransaction();
		try {
			Trip result = em.find(Trip.class, id);
			if ( result == null ){
				throw new UnknownItemException(Trip.class, id);
			}
			return result;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> findAll() {
		List<Trip> myTrips = new ArrayList<Trip>();
		try {
			myTrips.addAll(em.createQuery("SELECT t from Trip AS t").getResultList());		
		} finally {
			em.close();
		}
		return myTrips;
	}

}
