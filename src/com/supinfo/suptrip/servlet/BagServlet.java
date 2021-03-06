package com.supinfo.suptrip.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.suptrip.dao.DaoFactory;
import com.supinfo.suptrip.dao.UserDao;
import com.supinfo.suptrip.entity.Bag;
import com.supinfo.suptrip.entity.User;

/**
 * Servlet implementation class BagServlet
 */
@WebServlet("/auth/bag")
public class BagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("login");
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		User user = userDao.findById(userId);
		session.setAttribute("loggedUser", user);
		float totalPrice = 0;
		Collection<Bag> bags = user.getBags();
		DecimalFormat df = new DecimalFormat("#.##");
		
		if(bags.size() >= 1){
			for( Bag b : bags) {
				totalPrice += b.getTrip().getPrice();
			}
			request.setAttribute("totalPrice", df.format(totalPrice));
			request.setAttribute("bags", bags);
		}
		
		request.getRequestDispatcher("/auth/bag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getServletContext().getContextPath() + "/auth/validateorder");
	}

}
