package com.supinfo.suptrip.util;

public class MyAirports {
	
	private String cityName;
	private String airportKey;
	private String airportName;
	
	public MyAirports(){
		
	}

	public MyAirports(String cityName, String airportName,  String airportKey) {
		// TODO Auto-generated constructor stub
		this.cityName = cityName;
		this.airportKey = airportKey;
		this.airportName = airportName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAirportKey() {
		return airportKey;
	}

	public void setAirportKey(String airportKey) {
		this.airportKey = airportKey;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

}
