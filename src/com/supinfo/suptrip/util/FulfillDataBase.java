package com.supinfo.suptrip.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.supinfo.suptrip.dao.AirportDao;
import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Campus;

public class FulfillDataBase {
	
	//Add all airport and campus to database
	
	private List<String> campusList;
	private List<MyAirports> airportsList = new ArrayList<MyAirports>();

	public FulfillDataBase() {
		this.campusList = Arrays.asList("Montreal","San Francisco","Les Abymes","Lamentin","Rio De Janeiro","Londres","Luxembourg","Genève","Rome","Trapani","Rabat",
				"Casablanca","Dakar","Saint Denis","Pierrefonds","Beijing","Tianjing","Zhenjiang","Hong Kong","Paris", "Toulouse", "Bordeaux", "Caen", "Clermont-Ferrand", 
				"Grenoble", "Lille", "Lyon", "Marseille", "Metz", "Montpellier", "Nantes", "Nice", "Orléans", "Reims", "Rennes", "Strasbourg", "Tours", "Troyes", "Valenciennes");
		
		this.airportsList = Arrays.asList(new MyAirports("Montreal" , "Pierre-Elliott-Trudeau", "YUL"), new MyAirports("San Francisco" , "San Francisco", "SFO"), new MyAirports("Les Abymes" , "Guadeloupe - Pôle Caraïbes", "PTP"),
				new MyAirports("Lamentin" , "Martinique - Aimé Césaire", "FDF"),new MyAirports("Rio De Janeiro" , "Rio de Janeiro-Galeão", "GIG"),
				new MyAirports("Londres" , "Londres - Gatwick", "LGW"), new MyAirports("Bruxelles" , "Bruxelles-National", "BRU"),
				new MyAirports("Genève" , "Genève", "GVA"),new MyAirports("Rome" , "Léonard-de-Vinci de Rome Fiumicino", "FCO"),
				new MyAirports("Trapani" , "Trapani", "TPS"),new MyAirports("Rabat" , "Rabat-Salé", "RBA"),new MyAirports("Casablanca" , "Mohammed V", "CMN"),
				new MyAirports("Dakar" , "Dakar", "DKR"),new MyAirports("Saint Denis" , "La Réunion - Roland-Garros", "RUN"),new MyAirports("Pierrefonds" , "Sir Seewoosagur Ramgoolam", "MRU"),
				new MyAirports("Beijing" , "Pékin", "PEK"),new MyAirports("Tianjing" , "Tianjin Binhai", "TSN"),new MyAirports("Zhenjiang" , "Hangzhou Xiaoshan", "HGH"),new MyAirports("Hong Kong" , "Hong Kong", "HKG"),
				new MyAirports("Paris" , "Orly", "ORY"), new MyAirports("Paris" , "Charles De Gaulles", "CDG"), new MyAirports("Toulouse", "Blagnac", "TLS"), new MyAirports("Bordeaux" , "Bordeaux Airport", "BOD"), 
				new MyAirports("Caen", "Carpiquet", "CFR"), new MyAirports("Clermont-Ferrand", "Carpiquet", "CFE"), new MyAirports("Grenoble", "Isère", "GNB"), new MyAirports("Lille", "Lesquin", "LIL"), 
				new MyAirports("Lyon", "Bron", "LYS"), new MyAirports("Marseille", "Provence ", "MRS"), new MyAirports("Metz", "Lorraine", "ETZ"), new MyAirports("Montpellier", "Montpellier-Méditerrané", "MPL"), 
				new MyAirports("Nantes", "Nantes-Atlantique", "MPL"), new MyAirports("Nice", "Côte d’Azur", "NCE"), new MyAirports("Orléans", "Orleans", "ORE"), new MyAirports("Reims", "Reims", "RHE"), 
				new MyAirports("Rennes", "St Jacques", "RNS"), new MyAirports("Strasbourg", "Entzheim", "SXB"), new MyAirports("Tours", "Val de Loire", "TUF"), new MyAirports("Troyes", "Barberey", "QYR"),
				new MyAirports("Valenciennes", "Charles-Nungesser", "XVS"));
	}
	
	public void AddListToDataBase(){
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		List<Campus> campus = campusDao.findAll();
		AirportDao airportDao = DaoFactory.getInstance().getAirportDao();
		List<Airport> airports = airportDao.findAll();
		if (campus.size() == 0 && airports.size() == 0){
			for ( String campusName : campusList){
				CampusDao newCampusDao = DaoFactory.getInstance().getCampusDao();
				Campus newCampus = new Campus();
				newCampus.setName(campusName);
				newCampusDao.addCampus(newCampus);
				for ( MyAirports a : airportsList  ){
					if( a.getCityName().equals(newCampus.getName())){
						AirportDao newAirportDao = DaoFactory.getInstance().getAirportDao();
						Airport newAirport = new Airport(a.getAirportName() , a.getAirportKey() , newCampus);
						newAirportDao.addAirport(newAirport);
						
					}
				}
			}
			
		}	
	}

}
