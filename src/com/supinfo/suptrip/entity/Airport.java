package com.supinfo.suptrip.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="airport")
public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Airport() {
		// TODO Auto-generated constructor stub
	}
	
	public Airport(String name , String airportKey , Campus campus) {
		this.name = name;
		this.campus = campus;
		this.airportKey = airportKey;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String airportKey;
	
	@ManyToOne
	@JoinColumn(name="campus_fk")
	private Campus campus;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getAirportKey() {
		return airportKey;
	}

	public void setAirportKey(String airportKey) {
		this.airportKey = airportKey;
	}

}
