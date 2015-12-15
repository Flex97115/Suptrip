package com.supinfo.suptrip.dao;

import java.util.List;

import com.supinfo.suptrip.entity.Bag;

public interface BagDao {
	
	public Bag findById(int id);
	public List<Bag> findByUserId( int userId);
	public List<Bag> findBytripId(int tripId);
	public List<Bag> findByTripAndUserId(int userId , int tripId);
	public void addBag(Bag bag);
	void deleteBag(Bag bag);
}
