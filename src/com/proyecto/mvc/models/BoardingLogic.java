package com.proyecto.mvc.models;

public class BoardingLogic {

    public boolean addInQueue(Queue<Passenger> queue, Flight flight, Passenger passenger) {

        if (findReservation(flight, passenger.getIdP()) == null) {
            return false;
        }

        if (passenger.isBoarded()) {
            return false;
        }

        queue.enqueue(passenger);

        return true;
    }

    public Passenger boardNext(Queue<Passenger> queue) {

        Passenger passenger = queue.dequeue();

        if (passenger == null) {
            return null;
        }

        passenger.setBoarded(true);

        return passenger;
    }

    private Passenger findReservation(Flight flight, int id) {
        return flight.getSeats().findById(id);
    }
}
