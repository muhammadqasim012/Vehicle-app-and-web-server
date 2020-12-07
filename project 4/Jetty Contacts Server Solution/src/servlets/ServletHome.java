package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Contact;
import models.ContactsDAO;

public class ServletHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ContactsDAO dao = new ContactsDAO();
		ArrayList<Contact> allCons = dao.selectAllContacts();
		
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		req.setAttribute("allCons", allCons);
		view.forward(req, resp);
	}
}

