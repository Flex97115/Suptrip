package com.supinfo.suptrip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptrip.dao.AirportDao;
import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.User;
import com.supinfo.suptrip.util.FulfillDataBase;


/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/index")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		List<Campus> campus = campusDao.findAll();
		if ( campus.size() == 0){
			FulfillDataBase fulfill = new FulfillDataBase();
			fulfill.AddListToDataBase();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("isLogged", null);
		if ( session.getAttribute("login") != null ){
			response.sendRedirect(request.getServletContext().getContextPath() + "/auth/index");
		} else {
			UserDao userDao = DaoFactory.getInstance().getUserDao();
			List<User> myUsers = userDao.findAll();
			int numUser = myUsers.size();
			request.setAttribute("users", numUser);
			CampusDao newCampusDao = DaoFactory.getInstance().getCampusDao();
			request.setAttribute("destinations", newCampusDao.findAll().size());
			AirportDao airportDao = DaoFactory.getInstance().getAirportDao();
			request.setAttribute("airports", airportDao.findAll().size());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
