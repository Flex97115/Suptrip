package com.supinfo.suptrip.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QPXExpress {

	//50 request by key
	
	
	private String apiKey = "";	//API Key 
	

	
	private String url = "https://www.googleapis.com/qpxExpress/v1/trips/search?fields=trips&key=";
	public QPXExpress() {
		// TODO Auto-generated constructor stub
	}
	
	public List<TripTemp> executePost( MyJson myJson) throws ParseException{
		List<TripTemp> myList = new ArrayList<TripTemp>();
		try{
			URL url = new URL(this.url.toString()+apiKey.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			
			Gson gson = new Gson();
			String json = gson.toJson(myJson);
			
			wr.write(json);
			
			wr.flush();
			
			wr.close();
			

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 400){
            	return null;
            }
 
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close(); 
            
            //Parse Json to TripTemp Object
			myList = parseJson(response.toString());
			
		} catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return myList;
	}
	
	public List<TripTemp> parseJson(String json) throws ParseException{
		
		String origin = "";
		String destination = "";
		float price = 0;
		String departureTime = "";
		String arrivalTime = "";
		String carrier = "";
		String aircraft = "";
		int tripTempId = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat formatterFr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Map<String, String> carrierMap = new HashMap<String , String>();
		Map<String, String> aircraftMap = new HashMap<String , String>();
		List<TripTemp> myTripList = new ArrayList<TripTemp>();
		
		
		
		JsonElement jelement = new JsonParser().parse(json);
		JsonObject  jobject = jelement.getAsJsonObject();
		jobject = jobject.getAsJsonObject("trips");
		JsonObject dataObj = jobject.getAsJsonObject("data");
		
		//If there are trips in json response
		if ( dataObj.getAsJsonArray("carrier") != null){
			
			//Get carrier
			JsonArray carrierArr = dataObj.getAsJsonArray("carrier");
			for ( int h = 0; h < carrierArr.size(); h++){
				JsonObject carrierObj = carrierArr.get(h).getAsJsonObject();
				//Set array key value with carrier code and carrier name
				carrierMap.put(carrierObj.get("code").toString(), carrierObj.get("name").toString());
			}
			
			//Get aircraft
			JsonArray aircraftArr = dataObj.getAsJsonArray("aircraft");
			for (int v = 0; v < aircraftArr.size(); v++){
				JsonObject aircraftObj = aircraftArr.get(v).getAsJsonObject();
				//Set array key value with aircraft code and aircraft name
				aircraftMap.put(aircraftObj.get("code").toString(), aircraftObj.get("name").toString());
			}
			
			//Get trips
			JsonArray tripArr = jobject.getAsJsonArray("tripOption");
			for ( int i = 0; i < tripArr.size(); i++){
				JsonObject tripObj = tripArr.get(i).getAsJsonObject();
				
				//Get trip price
				String priceStr = tripObj.get("saleTotal").toString();
				price = Float.parseFloat(priceStr.substring(4,priceStr.length()-1));
				
				JsonArray sliceArr = tripObj.getAsJsonArray("slice");
				for ( int a = 0; a < sliceArr.size() ; a++){
					JsonObject sliceObj = sliceArr.get(a).getAsJsonObject();
					JsonArray segmentArr = sliceObj.getAsJsonArray("segment");
					for ( int b = 0; b < segmentArr.size(); b++){
						JsonObject segmentObj = segmentArr.get(b).getAsJsonObject();
						JsonObject flightObj = segmentObj.getAsJsonObject("flight");
						
						//Get carrier name with carrier code
						carrier = flightObj.get("carrier").toString();
						for ( Map.Entry<String,String> entry : carrierMap.entrySet() ){
							if ( entry.getKey().contentEquals(carrier)){
								carrier = entry.getValue();
								carrier = carrier.replace('\"',' ');
							}
						}
						JsonArray legArr = segmentObj.getAsJsonArray("leg");
						for ( int c = 0; c < legArr.size(); c++){
							
							//Get and format arrival/departure time 
							JsonObject legObj = legArr.get(c).getAsJsonObject();
							arrivalTime = legObj.get("arrivalTime").toString();
							arrivalTime = arrivalTime.substring(1 , 17);
							arrivalTime = arrivalTime.replace('T', ' ');
							Date arrivalDate = formatter.parse(arrivalTime);
							arrivalTime = formatterFr.format(arrivalDate);
							departureTime = legObj.get("departureTime").toString();
							departureTime = departureTime.substring(1 , 17);
							departureTime = departureTime.replace('T', ' ');
							Date departureDate = formatter.parse(departureTime);
							departureTime = formatterFr.format(departureDate);
							
							//Get departure and destination
							origin = legObj.get("origin").toString();
							destination = legObj.get("destination").toString();
							
							//Get aircraft with aircraft code
							aircraft = legObj.get("aircraft").toString();
							for ( Map.Entry<String,String> entry : aircraftMap.entrySet() ){
								if ( entry.getKey().contentEquals(aircraft)){
									aircraft = entry.getValue();
									aircraft = aircraft.replace('\"',' ');
								}
							}
						}
					}
				}
				
				//Create TripTemp
				TripTemp myTrip = new TripTemp(tripTempId, origin, destination, price, departureTime, arrivalTime,carrier , aircraft);
				myTripList.add(myTrip);
				
				//Increment TripTemp id
				tripTempId++;
			}
		} else {
			return null;
		}
		return myTripList;
		
	}
	

}
