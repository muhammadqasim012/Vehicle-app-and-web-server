package com.example.vehicleapp;

import java.io.Serializable;

/**
 * Getter and Setter methods for pop up menu varables
 * return vlaues for pop up menu
 *
 *
 * @author Muhammad Qasim
   * @version 5.3
 */

public class Vehicle implements Serializable {
    // declaring integers same as the Vehicles. java
    private int Vehicle_id;
    private String Make;
    private String Model;
    private int Year;
    private int Price;
    private String License_number;
    private String Colour;
    private int Number_doors;
    private String Transmission;
    private int Mileage;
    private String Fuel_type;
    private int Engine_size;
    private String Body_style;
    private String Condition;
    private String Notes;


    //Constructors

    public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number, String colour,
                   int number_doors, String transmission, int mileage, String fuel_type, int engine_size, String body_style,
                   String condition, String notes) {

        // The instance variables

        Vehicle_id = vehicle_id;
        Make = make;
        Model = model;
        Year = year;
        Price = price;
        License_number = license_number;
        Colour = colour;
        Number_doors = number_doors;
        Transmission = transmission;
        Mileage = mileage;
        Fuel_type = fuel_type;
        Engine_size = engine_size;
        Body_style = body_style;
        Condition = condition;
        Notes = notes;


    }
    //Setters and Getters

    public int getVehicle_id() {
        return Vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        Vehicle_id = vehicle_id; //

    }


    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }


    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }



    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }



    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }



    public String getLicense_number() {
        return License_number;
    }

    public void setLicense_number(String license_number) {
        License_number = license_number;
    }


    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }



    public int getNumber_doors() {
        return Number_doors;
    }

    public void setNumber_doors(int number_doors) {
        Number_doors = number_doors;
    }



    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }



    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int mileage) {
        Mileage = mileage;
    }



    public String getFuel_type() {
        return Fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        Fuel_type = fuel_type;
    }



    public int getEngine_size() {
        return Engine_size;
    }

    public void setEngine_size(int engine_size) {
        Engine_size = engine_size;
    }



    public String getBody_style() {
        return Body_style;
    }

    public void setBody_style(String body_style) {
        Body_style = body_style;
    }



    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }



    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

}