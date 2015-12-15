package com.supinfo.suptrip.dao;

import java.util.List;

import com.supinfo.suptrip.entity.Trip;

public interface TripDao {

	public void addTrip(Trip trip);
	public void deleteTrip( Trip trip);
	public Trip findById(int id);
	public List<Trip> findAll();
	
}
