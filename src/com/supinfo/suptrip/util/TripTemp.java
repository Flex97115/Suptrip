package com.supinfo.suptrip.util;


public class TripTemp {

	private int id;
	private String origin;
	private String destination;
	private float price;
	private String departureTime;
	private String arrivalTime;
	private String carrier;
	private String aircraft;
	
	public TripTemp(int id ,String origin, String destination, float price , String departureTime,
			String arrivalTime, String carrier , String aircraft) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.carrier = carrier;
		this.aircraft = aircraft;
	}
	
	public TripTemp(){
		
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destinantion) {
		this.destination = destinantion;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	
}
