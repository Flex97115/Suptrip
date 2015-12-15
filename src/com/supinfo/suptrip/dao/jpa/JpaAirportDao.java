package com.supinfo.suptrip.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.supinfo.suptrip.dao.AirportDao;
import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.exception.ItemAlreadyExistException;
import com.supinfo.suptrip.exception.UnknownItemException;

public class JpaAirportDao implements AirportDao {

private EntityManager em;
	
	public JpaAirportDao(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		em = emf.createEntityManager();
	}
	
	@Override
	public void addAirport(Airport airport) {
		// TODO Auto-generated method stub
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(em.find(Airport.class, airport.getId()) != null) {
				throw new ItemAlreadyExistException(airport);
			}
			em.persist(airport);
			t.commit();
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}		
	}

	@Override
	public Airport findById(int id) {
		
		EntityTransaction t = em.getTransaction();
		try {
			Airport result = em.find(Airport.class, id);
			if ( result == null ){
				throw new UnknownItemException(Airport.class, id);
			}
			return result;
		} finally {
			if(t.isActive()) t.rollback();
			em.close();
		}
	}

	@Override
	public Airport findByKey(String key) {
		
		Airport airport = new Airport();
		try {
			airport = (Airport)em.createQuery("SELECT a FROM Airport AS a WHERE a.key='"+key+"'");	
		} finally {
			em.close();
		}
		return airport;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Airport> findByCampus(Campus campus) {
		
		List<Airport> myAirports = new ArrayList<Airport>();
		try {
			myAirports.addAll(em.createQuery("SELECT a FROM Airport AS a WHERE a.campus_fk="+campus.getId()).getResultList());		
		} finally {
			em.close();
		}
		return myAirports;
	}
	
	@Override
	public Airport findByName(String name) {
		
		Airport myAirport = new Airport();
		try {
			myAirport = (Airport)em.createQuery("SELECT a FROM Airport AS a WHERE a.name='"+name+"'");		
		} finally {
			em.close();
		}
		return myAirport;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Airport> findAll() {
		List<Airport> myAirports = new ArrayList<Airport>();
		try {
			myAirports.addAll(em.createQuery("SELECT a FROM Airport AS a").getResultList());		
		} finally {
			em.close();
		}
		return myAirports;
	}

}
