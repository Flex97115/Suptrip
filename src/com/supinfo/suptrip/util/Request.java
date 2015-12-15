package com.supinfo.suptrip.util;

import java.util.List;

public class Request {

	private List<Slice> slice;
	private passengers passengers;
	private String saleCountry = "FR";
	
	public Request( List<Slice> slice , passengers passengers){
		this.passengers = passengers;
		this.slice = slice;
		this.setSaleCountry("FR");
	}
	
	public passengers getPassengers() {
		return passengers;
	}
	public void setPassengers(passengers passengers) {
		this.passengers = passengers;
	}
	public List<Slice> getSlice() {
		return slice;
	}
	public void setSlice(List<Slice> slice) {
		this.slice = slice;
	}

	public String getSaleCountry() {
		return saleCountry;
	}

	public void setSaleCountry(String saleCountry) {
		this.saleCountry = saleCountry;
	}

}
