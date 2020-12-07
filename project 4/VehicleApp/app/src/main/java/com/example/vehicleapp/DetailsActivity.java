package com.example.vehicleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** handaling the data recieved from json
 * setting the lsit view
 *
 * @author Muhammad Qasim
 * @version 5.3
 */



public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        Bundle extras = getIntent().getExtras();
        // create a Vehicle object


            final Vehicle theVehicle = (Vehicle) extras.get("Vehicle");
        Button button = (Button) findViewById(R.id.updateButton); // button to change activites

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) { // button listener on click

                Intent intentPut = new Intent(getApplicationContext(), Put.class); // change to the put class
                intentPut.putExtra("Vehicle", theVehicle);// data that is entered
                startActivity(intentPut); //  update the activity


            }

        });


        // making the data recived from the json into a list format

        System.out.println("received from the intent: "+ theVehicle.getMake()); // Print out the intent of the vehicle that we clicked on to logcat


        // text view
        TextView heading = findViewById(R.id.heading); // Setup heading
        TextView license = findViewById(R.id.licenset); // Setup licenset
        TextView price = findViewById(R.id.pricet); // Setup price
        TextView colour = findViewById(R.id.colourt); // Setup colourt
        TextView transmission = findViewById(R.id.transmissiont); // Setup transmissiont
        TextView mileage = findViewById(R.id.mileaget); // Setup mileaget
        TextView fuel_type = findViewById(R.id.fueltypet); // Setupfueltypet
        TextView body_style = findViewById(R.id.bodyt); // Setup bodyt
        TextView engine_size = findViewById(R.id.enginesizet); // Setup fueltypet
        TextView number_doors = findViewById(R.id.doorst); // Setup doorst
        TextView condition = findViewById(R.id.conditiont); // Setup conditiont
        TextView notes = findViewById(R.id.notest); // Setup notest


        // textview setup for pop up menu
        heading.setText(theVehicle.getMake() + " " + theVehicle.getModel() + " " + "(" +theVehicle.getYear() + ")");
        license.setText(" "+theVehicle.getLicense_number());
        price.setText(" "+theVehicle.getPrice());
        colour.setText(" "+theVehicle.getColour());
        transmission.setText(" "+theVehicle.getTransmission());
        mileage.setText(" "+theVehicle.getMileage());
        fuel_type.setText(" "+theVehicle.getFuel_type());
        body_style.setText(" "+theVehicle.getBody_style());
        engine_size.setText(" "+theVehicle.getEngine_size());
        number_doors.setText(" "+theVehicle.getNumber_doors());
        condition.setText(" "+theVehicle.getCondition());
        notes.setText(" "+theVehicle.getNotes());




    }
}
