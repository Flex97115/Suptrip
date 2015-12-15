package com.supinfo.suptrip.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private static EntityManagerFactory emf = null;
	
	public static EntityManagerFactory StartEntityManagerF(){
		if(emf == null){
			emf =  Persistence.createEntityManagerFactory("SupTrip-PU");
		}
		return emf;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	public static void CloseEntityManagerF(){
		if(emf != null && emf.isOpen()) emf.close();
	}

}
