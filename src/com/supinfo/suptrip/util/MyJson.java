package com.supinfo.suptrip.util;

import java.util.ArrayList;
import java.util.List;

public class MyJson {

	private Request request;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	
	public MyJson( String origin , String destination , String date, int maxStops, int adultCount){
		
		Slice mySlice = new Slice(origin , destination , date , maxStops);
		passengers myPassengers = new passengers(adultCount);
		List<Slice> mySliceList = new ArrayList<Slice>();
		mySliceList.add(mySlice);
		Request myRequest = new Request(mySliceList , myPassengers);
		this.request = myRequest;
	}
	public MyJson(){
		
	}

}
