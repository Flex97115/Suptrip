package com.supinfo.suptrip.entity;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="campus")
public class Campus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="campus",  cascade = CascadeType.ALL)
	private Collection<Airport> airports;
	
	public Collection<Airport> getAirports() {
		return airports;
	}
	public void setAirports(Collection<Airport> airports) {
		this.airports = airports;
	}
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
	
}
