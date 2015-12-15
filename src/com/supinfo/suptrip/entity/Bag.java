package com.supinfo.suptrip.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="bag")
public class Bag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	@OneToOne
	@JoinColumn(name="trip_fk")
	private Trip trip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	
	
}
