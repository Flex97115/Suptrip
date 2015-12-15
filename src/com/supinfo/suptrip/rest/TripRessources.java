package com.supinfo.suptrip.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.util.MyJson;
import com.supinfo.suptrip.util.QPXExpress;
import com.supinfo.suptrip.util.TripTemp;

@Path("/trips")
public class TripRessources {
	
	@Path("/city/{departure}/{arrival}")
	@GET @Produces(MediaType.APPLICATION_JSON)
	public String getTripsAsJson( @PathParam("departure") String departure, @PathParam("arrival") String arrival){
		
		Gson gson = new Gson();
		
		//Get Campus object
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();	
		Campus depCampus = campusDao.findByName(departure);
		
		CampusDao campusDao1 = DaoFactory.getInstance().getCampusDao();
		Campus arrCampus = campusDao1.findByName(arrival);
		
		//Get and format today date 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = formatter.format(date);
		
		
		List<MyJson> myJsons = new ArrayList<MyJson>();
		
		//Get all airports for campus
		Collection<Airport> depAirportsColl = depCampus.getAirports();
		Collection<Airport> arrAirportsColl = arrCampus.getAirports();
		
		//Cast Collection to List
		List<Airport> depAirports = new ArrayList<Airport>(depAirportsColl);
		List<Airport> arrAirports = new ArrayList<Airport>(arrAirportsColl);

			
		//Create MyJson object for each airport and add it to array
		int i = 0;
		int x = -1;
		if (depAirports.size() >= arrAirports.size()){
			while ( i <= depAirports.size()-1 ){
				while (x < arrAirports.size()-1 ){
					x++;
					break;
				}
				MyJson myJson = new MyJson(depAirports.get(i).getAirportKey(), arrAirports.get(x).getAirportKey(), dateStr, 0, 1);
				myJsons.add(myJson);
				i++;
			}
		} else {
			while ( i <= arrAirports.size()-1){
				while (x < depAirports.size()-1 ){
					x++;
					break;
				}
				System.out.print(arrAirports.get(i).getAirportKey());
				MyJson myJson = new MyJson(depAirports.get(x).getAirportKey(), arrAirports.get(i).getAirportKey(), dateStr, 0, 1);
				myJsons.add(myJson);
				i++;
			}
		}
		
		QPXExpress api = new QPXExpress();
		List<List<TripTemp>> listInList  = new ArrayList<List<TripTemp>>();
		
		List<TripTemp> myTripList = new ArrayList<TripTemp>();
		try {
			for ( MyJson j : myJsons ){
				//Get trips from API
				myTripList = api.executePost(j);
				listInList.add(myTripList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Make one list with all TripTemp object
		List<TripTemp> finalList = new ArrayList<TripTemp>();
		if (finalList != null){
			for (List<TripTemp> lt : listInList){
				if(lt != null){
					for ( TripTemp t : lt){
						finalList.add(t);
					}
				}
			}
		}
		
		ListTripTemp myList = new ListTripTemp();
		myList.setTrips(finalList);
		
		//Parse ListTripTemp object to Json
		String json = gson.toJson(myList);
		
		return json;
		
	}
	
	@Path("/airport/{departure}/{arrival}")
	@GET @Produces(MediaType.APPLICATION_JSON)
	public String getTripsByAirportAsJson( @PathParam("departure") String departure, @PathParam("arrival") String arrival){
		
		Gson gson = new Gson();
		
		//Get and format  today date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = formatter.format(date);
		
		//Create MyJson object
		MyJson myJson = new MyJson(departure, arrival, dateStr, 0, 1);
		
		QPXExpress api = new QPXExpress();
		List<TripTemp> myTripList = new ArrayList<TripTemp>();
		try {
			//Get trip from API
			myTripList = api.executePost(myJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListTripTemp myList = new ListTripTemp();
		myList.setTrips(myTripList);
		
		//Parse ListTripTemp object to Json
		String json = gson.toJson(myList);
		return json;
		
	}
}

