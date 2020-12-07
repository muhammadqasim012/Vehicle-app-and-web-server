package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Vehicle;
import models.VehicleDAO;

public class ServletApi extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	VehicleDAO dao = new VehicleDAO();
	Gson gson = new Gson();
	PrintWriter writer;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	ArrayList<Vehicle> allveh;
	try {
		allveh = dao.getallvehicles();
	
	resp.setContentType("application/json");
	writer = resp.getWriter();
	String conJSON = gson.toJson(allveh);
	writer.write(conJSON);
	writer.close();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
