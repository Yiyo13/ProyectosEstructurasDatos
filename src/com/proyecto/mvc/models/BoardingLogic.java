package com.proyecto.mvc.models;

public class BoardingLogic {

    public boolean addInQueue(BoardingQueue queue, Flight flight, Passenger passenger) {

        if (findReservation(flight, passenger.getIdP()) == null) {
            return false;
        }

        if (passenger.isBoarded()) {
            return false;
        }

        NodeBoarding node = new NodeBoarding(passenger);

        if (queue.isEmpty()) {
            queue.setFirst(node);
            queue.setLast(node);
            return true;
        }

        queue.getLast().setNext(node);
        queue.setLast(node);

        return true;
    }

    public Passenger boardNext(BoardingQueue queue) {

        if (queue.isEmpty()) {
            return null;
        }

        Passenger passenger = queue.getFirst().getPassenger();
        queue.setFirst(queue.getFirst().getNext());

        if (queue.getFirst() == null) {
            queue.setLast(null);
        }

        passenger.setBoarded(true);

        return passenger;
    }

    private Passenger findReservation(Flight flight, int id) {

        NodePassenger aux = flight.getSeats().getFirst();

        while (aux != null) {

            if (aux.getPassenger().getIdP() == id) {
                return aux.getPassenger();
            }

            aux = aux.getNext();
        }

        return null;
    }
}