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
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
		List<Campus> campusList = campusDao.findAll();
		
		request.setAttribute("campus", campusList);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idBooster = 0;
		try {
		   idBooster = Integer.parseInt(request.getParameter("idBooster"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("invalide", true);
			doGet(request , response);
		}
		if (idBooster != 0){
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			int campusId = Integer.parseInt(request.getParameter("campus"));
			
			CampusDao campusDao = DaoFactory.getInstance().getCampusDao();
			
			Campus myCampus = campusDao.findById(campusId);
			User myUser = new User(idBooster , password , firstName, lastName, email, myCampus);
			UserDao userDao = DaoFactory.getInstance().getUserDao();
			try {
			userDao.addUser(myUser);
			} catch (Exception e){
				e.printStackTrace();
				request.setAttribute("invalide", true);
				doGet(request , response);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("login", idBooster);
			response.sendRedirect(request.getServletContext().getContextPath() + "/index");
		} else {
			request.setAttribute("invalide", true);
			doGet(request , response);
		}
	}

}
