package com.proyecto.mvc.models;

public class Trip {

    private Flight flight;
    private Passenger passenger;

    public Trip(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return passenger.getIdP() + "," +
               passenger.getNameP() + "," +
               passenger.getAgeP() + "," +
               flight.getFlightNumber() + "," +
               flight.getRoute();
    }
    
}