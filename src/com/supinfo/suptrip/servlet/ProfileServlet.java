package com.supinfo.suptrip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptrip.dao.CampusDao;
import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Campus;
import com.supinfo.suptrip.entity.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/auth/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int idBooster = (int) session.getAttribute("login");
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User myUser = userDao.findById(idBooster);
		request.setAttribute("idBooster", idBooster);
		request.setAttribute("lastName", myUser.getLastName());
		request.setAttribute("firstName", myUser.getFirstName());
		request.setAttribute("email", myUser.getEmail());
		request.setAttribute("password", myUser.getPassword());
		request.setAttribute("campusId", myUser.getCampus().getId());
		
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		List<Campus> campusList = campusDao.findAll();
		
		request.setAttribute("campus", campusList);

		getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int idBooster = (int) session.getAttribute("login");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int campusId = Integer.parseInt(request.getParameter("campus"));
		
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		
		Campus myCampus = campusDao.findById(campusId);
		User myUser = new User(idBooster , password , firstName, lastName, email, myCampus);
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		userDao.updateUser(myUser);
		
		response.sendRedirect(request.getServletContext().getContextPath() + "/index");
	}

}
