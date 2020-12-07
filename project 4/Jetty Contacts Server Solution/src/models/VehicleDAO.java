package models;
import java.util.ArrayList;	
import java.sql.Connection;	
import java.sql.Statement;	
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;	


public class VehicleDAO {
	
//get DB connection method
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			
			String url = "jdbc:sqlite:vehicles.sqlite";
			dbConnection = DriverManager.getConnection(url);
			
			return dbConnection;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return dbConnection; 
	}
	
	//Get all vehicles method 
	
	public ArrayList<Vehicle> getallvehicles() throws SQLException {
		
		Statement statement = null;	
		Connection dbConnection = null; 
		ResultSet result = null;
		
		System.out.println("\nFetching all vehicles .....");
			
		String query = "SELECT * FROM vehicles;";
		ArrayList<Vehicle> vehiclesi = new ArrayList<>(); 

		try {
			
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); 
			
			while (result.next()) {

				
				int vehicle_id = result.getInt("vehicle_id"); 
				String make = result.getString("make");	
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");	
				String license_number = result.getString("license_number");	
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("notes");
			
				
		     vehiclesi.add(new Vehicle(vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes));
			}
		} catch(Exception e) {
			System.out.println("get all vehicle method: "+e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return vehiclesi;
	}
	
	//Get vehicle by specific ID method
	
	public Vehicle GetVehicle(int vehicleid) throws SQLException { 
		Vehicle temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM Vehicles WHERE Vehicle_id =" + vehicleid + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			result = statement.executeQuery(query);
			while (result.next()) {
			
				int vehicle_id = result.getInt("Vehicle_id");
				String make = result.getString("Make");
				String model = result.getString("Model");
				int year = result.getInt("Year");
				int price = result.getInt("Price");
				String license_number = result.getString("License_number");	
				String colour = result.getString("Colour");	
				int number_doors = result.getInt("Number_doors");
				String transmission = result.getString("Transmission");
				int mileage = result.getInt("Mileage");	
				String fuel_type = result.getString("Fuel_type");
				int engine_size = result.getInt("Engine_size");	
				String body_style = result.getString("Body_style");
				String condition = result.getString("Condition");
				String notes = result.getString("Notes");
			
				temp = new Vehicle(vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes);	
				
			}
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	//Insert vehicle method
	
	public boolean insertvehicleR(Vehicle c) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null; 
		String Ins = "INSERT INTO vehicles (Make, Model, Year, Price, License_number, Colour, Number_doors, Transmission, Mileage, Fuel_type, Engine_size, Body_style, Condition, Notes) VALUES " 
				+ "("+"'"+c.getMake()+"','"+c.getModel()+"',"+c.getYear()+","+c.getPrice()+",'"+c.getLicense_number()+"','"+c.getColour()+"',"+c.getNumber_doors()+",'"+c.getTransmission()+"',"+c.getMileage()+",'"+c.getFuel_type()+"',"+c.getEngine_size()+",'"+c.getBody_style()+"','"+c.getCondition()+"','"+c.getNotes()+"');";
	
		
		try {
			
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			
			System.out.println(Ins);
			statement.executeUpdate(Ins);
			
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false; 
	}
	
//Update vehicle method
	
	public boolean updatevehicleR(Vehicle vehicle , int vehicleid) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE Vehicles " + "SET Vehicle_id = '" + vehicleid  + "'," + "Make = '" + vehicle.getMake() + "'," + "Model= '" + vehicle.getModel() + "'," + "Year= '" + vehicle.getYear()+ "'," + "Price= '" + vehicle.getPrice() + "'," + "License_number= '" + vehicle.getLicense_number() 
				+ "'," + "Colour= '" + vehicle.getColour() + "'," + "Number_doors= '" + vehicle.getNumber_doors() +"'," + "Transmission= '" + vehicle.getTransmission() + "'," + "Mileage= '" + vehicle.getMileage() + "'," + "Fuel_type= '" + vehicle.getFuel_type() +"'," + "Engine_size= '" + vehicle.getEngine_size() + "'," 
				+ "Body_style= '" + vehicle.getBody_style() + "'," + "Condition= '" + vehicle.getCondition() + "'," + "Notes= '" + vehicle.getNotes()+ "'"  + " WHERE Vehicle_id = " + vehicleid + ";";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
	
	
}






