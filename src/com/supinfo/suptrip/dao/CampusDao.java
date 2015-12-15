package com.supinfo.suptrip.dao;

import java.util.List;

import com.supinfo.suptrip.entity.Campus;

public interface CampusDao {
	public Campus findById(int id);
	public Campus findByName(String name);
	public List<Campus> findAll();
	void addCampus(Campus campus);
}
