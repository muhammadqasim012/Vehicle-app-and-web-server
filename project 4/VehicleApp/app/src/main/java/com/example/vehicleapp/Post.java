package com.example.vehicleapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 *insert an new vehicle to the server
 * stroe the new vehicle
 *
 * @author Muhammad Qasim
 * @version 53
 */

public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // on create method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //store data from the user to  the app
        final EditText make = (EditText) findViewById(R.id.makeText);
        final EditText model = (EditText) findViewById(R.id.modelText);
        final EditText year = (EditText) findViewById(R.id.yearText);
        final EditText price = (EditText) findViewById(R.id.priceText);
        final EditText license_number = (EditText) findViewById(R.id.licenseText);
        final EditText colour = (EditText) findViewById(R.id.colourText);
        final EditText number_doors = (EditText) findViewById(R.id.doorsText);
        final EditText transmission = (EditText) findViewById(R.id.transmissionText);
        final EditText mileage = (EditText) findViewById(R.id.mileageText);
        final EditText fuel_type = (EditText) findViewById(R.id.fuelText);
        final EditText engine_size = (EditText) findViewById(R.id.engineText);
        final EditText body_style = (EditText) findViewById(R.id.bodyText);
        final EditText condition = (EditText) findViewById(R.id.conditionText);
        final EditText notes = (EditText) findViewById(R.id.notesText);

        Button button = (Button) findViewById(R.id.saveButton); //pressing the button will store the data

        final HashMap<String, String> params = new HashMap<>();

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Gson gson = new Gson();
                int vehicle_idS = 0;
                String makeS = make.getText().toString();
                String modelS = model.getText().toString();
                int yearS = Integer.valueOf(year.getText().toString());
                int priceS = Integer.valueOf(price.getText().toString());
                String license_numberS = license_number.getText().toString();
                String colourS = colour.getText().toString();
                int number_doorsS = Integer.valueOf(number_doors.getText().toString());
                String transmissionS = transmission.getText().toString();
                int mileageS = Integer.valueOf(mileage.getText().toString());
                String fuel_typeS = fuel_type.getText().toString();
                int engine_sizeS = Integer.valueOf(engine_size.getText().toString());
                String body_styleS = body_style.getText().toString();
                String conditionS = condition.getText().toString();
                String notesS = notes.getText().toString(); //

                Vehicle v = new Vehicle(vehicle_idS,makeS, modelS, yearS, priceS, license_numberS, colourS, number_doorsS, transmissionS,
                        mileageS, fuel_typeS, engine_sizeS, body_styleS, conditionS, notesS);

                String vehicleJson = gson.toJson(v); // converting into jason
                params.put("Vehicle", vehicleJson);
                String url = "http://10.0.2.2:8005/VehiclesDB/api";
                performPostCall(url, params);

            }

        });


    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(16000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoInput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();


            int responseCode = conn.getResponseCode();
            System.out.println("Response Code = " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                Toast.makeText(this, " Saved", Toast.LENGTH_LONG).show(); // to let the user know it was saved
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();// let the user know it wasnt saved
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response = " + response);

        return response;

    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

        }

        return result.toString();
    }

}

