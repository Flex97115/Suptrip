package com.supinfo.suptrip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int idBooster = 0;
		User myUser = null;
		try {
			 idBooster = Integer.parseInt(request.getParameter("idBooster"));
			 String password = request.getParameter("password");
			 UserDao userDao = DaoFactory.getInstance().getUserDao();
			 myUser = userDao.findByAuth(idBooster, password);
		} catch(NumberFormatException e){
			e.printStackTrace();
		} 
		
		Boolean isLogged = false;
		if (  myUser != null ){
			if ( myUser.getIdBooster() == idBooster){
				isLogged = true;
				session.setAttribute("isLogged", isLogged);
				session.setAttribute("login", idBooster);
				response.sendRedirect(request.getServletContext().getContextPath() + "/index");
			} else {
				session.setAttribute("isLogged", isLogged);
				response.sendRedirect(request.getServletContext().getContextPath() + "/login");
			}
		} else {
			session.setAttribute("isLogged", isLogged);
			response.sendRedirect(request.getServletContext().getContextPath() + "/login");
		}
		
	}

}
