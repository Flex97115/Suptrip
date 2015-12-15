package com.supinfo.suptrip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptrip.dao.BagDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.TripDao;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Bag;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.Trip;
import com.supinfo.suptrip.entity.User;
import com.supinfo.suptrip.util.TripTemp;

/**
 * Servlet implementation class AddToBagServlet
 */
@WebServlet("/auth/addtobag")
public class AddToBagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToBagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Get attributes
		HttpSession session = request.getSession();
		int tripId = Integer.parseInt(request.getParameter("tripToAdd"));
		Campus campusA = (Campus)session.getAttribute("campusA");
		Campus campusD = (Campus)session.getAttribute("campusD");
		Airport airportA = (Airport)session.getAttribute("airportA");
		Airport airportD = (Airport)session.getAttribute("airportD");
		int userId = (int)session.getAttribute("login");
		int passengers = (int)session.getAttribute("passengers");
		
		//Find TripTemp to add
		TripTemp searchTrip = new TripTemp();
		List<TripTemp> myTripList = (List<TripTemp>)session.getAttribute("tripList");
		for(TripTemp t : myTripList){
			if (t.getId() == tripId){
				searchTrip = t;
			}
		}
		
		//Add TripTemp in database as Trip
		Trip newTrip = new Trip(campusD , campusA , searchTrip.getPrice(), passengers, airportD , airportA , searchTrip.getDepartureTime() , searchTrip.getArrivalTime() , searchTrip.getCarrier() , searchTrip.getAircraft());
		TripDao tripDao = DaoFactory.getInstance().getTripDao();
		tripDao.addTrip(newTrip);
		
		
		//Add trip to bag
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User user = userDao.findById(userId);
		Bag newBag = new Bag();
		newBag.setTrip(newTrip);
		newBag.setUser(user);
		BagDao bagDao = DaoFactory.getInstance().getBagDao();
		bagDao.addBag(newBag);
		
		
		response.sendRedirect(request.getServletContext().getContextPath() + "/auth/bag");
		
	}

}
