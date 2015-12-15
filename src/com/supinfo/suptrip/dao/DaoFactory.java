package com.supinfo.suptrip.dao;

import javax.persistence.EntityManagerFactory;

import com.supinfo.suptrip.dao.jpa.JpaAirportDao;
import com.supinfo.suptrip.dao.jpa.JpaBagDao;
import com.supinfo.suptrip.dao.jpa.JpaCampusDao;
import com.supinfo.suptrip.dao.jpa.JpaTripDao;
import com.supinfo.suptrip.dao.jpa.JpaUserDao;
import com.supinfo.suptrip.util.PersistenceManager;

public class DaoFactory {

	private static final DaoFactory INSTANCE = new DaoFactory();
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	private final EntityManagerFactory emf;
	
	private DaoFactory() {
		emf = PersistenceManager.StartEntityManagerF();
	}
	
	public TripDao getTripDao(){
		return new JpaTripDao(emf);
	}
	
	public UserDao getUserDao(){
		return new JpaUserDao(emf);
	}
	
	public BagDao getBagDao(){
		return new JpaBagDao(emf);
	}
	
	public CampusDao getCampusDao(){
		return new JpaCampusDao(emf);
	}
	
	public AirportDao getAirportDao(){
		return new JpaAirportDao(emf);
	}

}
