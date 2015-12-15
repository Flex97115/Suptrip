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
@Table(name="trip")
public class Trip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Trip(){
		
	}
	
	public Trip(Campus campusOri, Campus campusDes, float price, int passengers, Airport airportOri,
			Airport airportDes, String departureTime, String arrivalTime ,String carrier, String aircraft) {
		super();
		this.campusOri = campusOri;
		this.campusDes = campusDes;
		this.price = price;
		this.passengers = passengers;
		this.airportOri = airportOri;
		this.airportDes = airportDes;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.setCarrier(carrier);
		this.aircraft = aircraft;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="campusOri_fk")
	private Campus campusOri;
	
	@ManyToOne
	@JoinColumn(name="campusDes_fk")
	private Campus campusDes;
	
	private float price;
	private int passengers;
	
	@ManyToOne
	@JoinColumn(name="airportOri_fk")
	private Airport airportOri;
	
	@ManyToOne
	@JoinColumn(name="airportDes_fk")
	private Airport airportDes;
	
	private String departureTime;
	private String arrivalTime;
	private String carrier;
	private String aircraft;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Campus getOrigin() {
		return campusOri;
	}
	public void setOrigin(Campus campusOri) {
		this.campusOri = campusOri;
	}
	public Campus getDestination() {
		return campusDes;
	}
	public void setDestination(Campus campusDes) {
		this.campusDes = campusDes;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public Airport getAirportOri() {
		return airportOri;
	}
	public void setAirportOri(Airport airportOri) {
		this.airportOri = airportOri;
	}
	public Airport getAirportDes() {
		return airportDes;
	}
	public void setAirportDes(Airport airportDes) {
		this.airportDes = airportDes;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
}
