package com.supinfo.suptrip.util;



public class Slice {

	private String origin;
	private String destination;
	private String date;
	private int maxStops = 0;
	
	public Slice( String origin, String destination , String date, int maxStops){
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.maxStops = maxStops;
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
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMaxStops() {
		return maxStops;
	}
	public void setMaxStops(int maxStops) {
		this.maxStops = maxStops;
	}
	
}
