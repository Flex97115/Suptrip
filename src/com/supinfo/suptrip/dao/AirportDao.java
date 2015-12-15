package com.supinfo.suptrip.dao;

import java.util.List;

import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Campus;

public interface AirportDao {
	public Airport findById(int id);
	public Airport findByKey(String key);
	public List<Airport> findByCampus(Campus campus);
	public List<Airport> findAll();
	public Airport findByName(String name);
	void addAirport(Airport airport);
	
}
