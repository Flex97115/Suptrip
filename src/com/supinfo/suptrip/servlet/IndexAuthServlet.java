package com.supinfo.suptrip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;
import com.supinfo.suptrip.dao.AirportDao;
import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Airport;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.User;
import com.supinfo.suptrip.util.MyJson;
import com.supinfo.suptrip.util.QPXExpress;
import com.supinfo.suptrip.util.TripTemp;

/**
 * Servlet implementation class IndexAuthServlet
 */
@WebServlet("/auth/index")
public class IndexAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		List<Campus> campus = campusDao.findAll();
		request.setAttribute("campus", campus);
		
		AirportDao airportDao = DaoFactory.getInstance().getAirportDao();
		List<Airport> airports = airportDao.findAll();
		request.setAttribute("airports", airports);
		
		HttpSession session = request.getSession();
		int idBooster = (int)session.getAttribute("login");
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User loggedUser = userDao.findById(idBooster);
		session.setAttribute("loggedUser", loggedUser);
		
		request.getRequestDispatcher("/auth/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if (type.equals("search")){
			Gson gson = new Gson();
			int campusDId = 0;
			int campusAId = 0;
			int airportDId = 0;
			int airportAId = 0;
			Airport airportA = new Airport();
			Airport airportD = new Airport();
			Campus campusA = new Campus();
			Campus campusD = new Campus();
			String myDate = "";
			int passengers = 0;
			HttpSession session = request.getSession();
			try{
			campusDId = Integer.parseInt(request.getParameter("campusD"));
			campusAId = Integer.parseInt(request.getParameter("campusA"));
			airportDId = Integer.parseInt(request.getParameter("airportD"));
			airportAId = Integer.parseInt(request.getParameter("airportA"));
			
			myDate = request.getParameter("date");
			passengers = Integer.parseInt(request.getParameter("passengers"));
			
			//Get airports
			AirportDao airportDaoA = DaoFactory.getInstance().getAirportDao();
			airportA = airportDaoA.findById(airportAId);
			AirportDao airportDaoD = DaoFactory.getInstance().getAirportDao();
			airportD = airportDaoD.findById(airportDId);
			
			//Get campus
			CampusDao campusDaoA = DaoFactory.getInstance().getCampusDao();
			campusA = campusDaoA.findById(campusAId);
			CampusDao campusDaoD = DaoFactory.getInstance().getCampusDao();
			campusD = campusDaoD.findById(campusDId);
			
			}catch(Exception e){
				e.printStackTrace();
			} 
						
			//Create MyJson object		
			MyJson myJson = new MyJson(airportD.getAirportKey(), airportA.getAirportKey(), myDate, 0, passengers);
			String json = gson.toJson(myJson);
			System.out.print(json+"\n");
			
			QPXExpress api = new QPXExpress();
			List<TripTemp> myTripList = new ArrayList<TripTemp>();
			try {
				//Get trip from api
				myTripList = api.executePost(myJson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(myTripList);
			//Set tripList in session attribute
			
			if ( myTripList != null){
								
				session.setAttribute("tripList", myTripList);
				session.setAttribute("campusA", campusA);
				session.setAttribute("campusD", campusD);
				session.setAttribute("airportA", airportA);
				session.setAttribute("airportD", airportD);
				session.setAttribute("passengers", passengers);
			} else {
				session.setAttribute("tripList", null);
			}
			response.sendRedirect(request.getServletContext().getContextPath() + "/auth/index");
		}
	}

}
