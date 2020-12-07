package com.example.vehicleapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;

import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 *This is the main part of the app
 * the data will be recived from json
 * also this is where the on clivk event takes place
 *
 * @author Muhammad Qasim
 * @version 5.3
 */

public class MainActivity extends AppCompatActivity {

    String[] vehiclesData; // New String where all the data of the vehicles are stored once retrieved
    ArrayList<Vehicle> allvehicles = new ArrayList<>(); // New ArrayList
    ListView vehicleList; // New Instance of an ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //run network on main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        vehicleList = (ListView) findViewById(R.id.vehicleListview); // Setting the ListView

        getData(); // Method for getting the Vehicles

        Button button = (Button) findViewById(R.id.addnewButton); // Setting new Instance of an Button

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) { // Button Listener

                Intent intentPost = new Intent(getApplicationContext(), Post.class); // pass the data to the Post.class
                startActivity(intentPost);// start the Intent


            }

        });



        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // creating a new instance of an Click Listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) { // ListView Listener
                Toast.makeText(MainActivity.this, "you pressed " + allvehicles.get(i).getMake() + " " + allvehicles.get(i).getModel(), Toast.LENGTH_SHORT).show();
                //Toast used to show the user their choice of vehicle and the make and model of that vehicle

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);// creating a new instance of a Intent and setting where the program will transfer to (DetailsActivity)
                intent.putExtra("Vehicle", allvehicles.get(i));// Data that is passed to the Intent
                startActivity(intent);// Start the new intent
            }
        });


        vehicleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) { // Setting a new instance of an Long Click Listener
                final HttpURLConnection urlConnection; // Instance of HttpURL Connection
                InputStream in = null;
                int vehicleID = -1;

                try{

                    for (int j = 0; j<allvehicles.size(); j++) // Loop through the data that has been stored in allvehicles
                    {
                        vehicleID = allvehicles.get(i).getVehicle_id(); // from this data pick out its vehicle ID number
                    }

                    URL url = new URL ("http://10.0.2.2:8005/VehiclesDB/api?&id="+vehicleID); //pass id through the URL

                    urlConnection = (HttpURLConnection) url.openConnection(); // Stating new urlConnection
                    urlConnection.setRequestMethod("DELETE"); // setting the request method
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    // covert the input stream to a string
                    String response = convertStreamToString(in); // get the response and stored it
                    System.out.println("Server response = " + response); // print the received response from the server
                    Toast.makeText(MainActivity.this,response ,Toast.LENGTH_SHORT).show(); // Toast to confirm the deletion of the vehicle
                    getData(); // Get the vehicles again


                } catch (JsonIOException e ){

                    e.printStackTrace();

                }

                catch (IOException e)
                {
                    e.printStackTrace();
                }

                return true;

            }
        });

    }


    @Override
    protected void onResume() { // On Resume Method for the program
        super.onResume();
        getData(); // calling the method to refresh everytime a change is made
    }

    void getData(){ // method created for getting the data from the server
        final HttpURLConnection urlConnection;
        InputStream in = null; // InputStream set to null
        try {
            // the url we wish to connect to
            URL url = new URL("http://10.0.2.2:8005/VehiclesDB/api"); // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // covert the input stream to a string
        String response = convertStreamToString(in);
        // print the response to android monitor/log cat
        System.out.println("Server response = " + response);

        try {
            // declare a new json array and pass it the string response from the server

            JSONArray jsonArray = new JSONArray(response);


            vehiclesData = new String[jsonArray.length()]; // the Json data will be stored in "vehiclesData"

            for (int i = 0; i < jsonArray.length(); i++) { // looping through the JSON

                int vehicle_id = Integer.valueOf(jsonArray.getJSONObject(i).get("Vehicle_id").toString()); // picking the vehicle id from the json and converting to string
                String make = jsonArray.getJSONObject(i).get("Make").toString(); // picking the make from the json and converting to string
                String model = jsonArray.getJSONObject(i).get("Model").toString(); // picking the model from the json and converting to string
                int year = Integer.valueOf(jsonArray.getJSONObject(i).get("Year").toString()); // picking the year from the json and converting to string
                int price = Integer.valueOf(jsonArray.getJSONObject(i).get("Price").toString()); // picking the price from the json and converting to string
                String license_number = jsonArray.getJSONObject(i).get("License_number").toString(); // picking the license number from the json and converting to string
                String colour = jsonArray.getJSONObject(i).get("Colour").toString(); // picking the colour from the json and converting to string
                int number_doors = Integer.valueOf(jsonArray.getJSONObject(i).get("Number_doors").toString()); // picking the number of doors from the json and converting to string
                String transmission = jsonArray.getJSONObject(i).get("Transmission").toString(); // picking the transmission from the json and converting to string
                int mileage = Integer.valueOf(jsonArray.getJSONObject(i).get("Mileage").toString()); // picking the mileage from the json and converting to string
                String fuel_type = jsonArray.getJSONObject(i).get("Fuel_type").toString(); // picking the fuel type from the json and converting to string
                int engine_size = Integer.valueOf(jsonArray.getJSONObject(i).get("Engine_size").toString()); // picking the engine size from the json and converting to string
                String body_style = jsonArray.getJSONObject(i).get("Body_style").toString(); // picking the body style from the json and converting to string
                String condition = jsonArray.getJSONObject(i).get("Condition").toString(); // picking the condition from the json and converting to string
                String notes = jsonArray.getJSONObject(i).get("Notes").toString(); // picking the notes from the json and converting to string



                System.out.println("Make = " + make); // print the make to log cat
                System.out.println("Model = " + model); // print the model to log cat
                System.out.println("Year = " + year); // print the year to log cat
                System.out.println("Price = " + price); // print the price to log cat
                System.out.println("License_number = " + license_number); // print the license number  to log cat
                System.out.println("Colour = " + colour); // print the colour to log cat
                System.out.println("Number_doors = " + number_doors); // print the number of doors to log cat
                System.out.println("Transmission = " + transmission); // print the transmission to log cat
                System.out.println("Mileage = " + mileage); // print the mileage to log cat
                System.out.println("Fuel_type = " + fuel_type); // print the fuel type to log cat
                System.out.println("Engine_size = " + engine_size); // print the engine size to log cat
                System.out.println("Body_style = " + body_style); // print the body style to log cat
                System.out.println("Condition = " + condition); // print the condition to log cat
                System.out.println("Notes = " + notes); // print the notes to log cat

                // Constructor used for vehicles
                Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);

                allvehicles.add(v); // adding the new vehicle instance to an arrayList

                // add the name of the current Vehicle to the vehiclesData array
                vehiclesData[i] = make + " " + model + " " + "(" + year + ")" + "\n" + license_number;
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehiclesData);
        vehicleList.setAdapter(arrayAdapter);

    }

    public String convertStreamToString (InputStream is){
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
